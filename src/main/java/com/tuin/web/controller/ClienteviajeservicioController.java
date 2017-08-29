/*
 * Created on 28 ago 2017 ( Time 17:51:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//--- Common classes
import com.tuin.web.common.AbstractController;
import com.tuin.web.common.FormMode;
import com.tuin.web.common.Message;
import com.tuin.web.common.MessageType;

//--- Entities
import com.tuin.bean.Clienteviajeservicio;
import com.tuin.bean.Clienteviaje;
import com.tuin.bean.Vehiculoservicio;

//--- Services 
import com.tuin.business.service.ClienteviajeservicioService;
import com.tuin.business.service.ClienteviajeService;
import com.tuin.business.service.VehiculoservicioService;

//--- List Items 
import com.tuin.web.listitem.ClienteviajeListItem;
import com.tuin.web.listitem.VehiculoservicioListItem;

/**
 * Spring MVC controller for 'Clienteviajeservicio' management.
 */
@Controller
@RequestMapping("/clienteviajeservicio")
public class ClienteviajeservicioController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "clienteviajeservicio";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "clienteviajeservicio/form";
	private static final String JSP_LIST   = "clienteviajeservicio/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/clienteviajeservicio/create";
	private static final String SAVE_ACTION_UPDATE   = "/clienteviajeservicio/update";

	//--- Main entity service
	@Resource
    private ClienteviajeservicioService clienteviajeservicioService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ClienteviajeService clienteviajeService; // Injected by Spring
	@Resource
    private VehiculoservicioService vehiculoservicioService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ClienteviajeservicioController() {
		super(ClienteviajeservicioController.class, MAIN_ENTITY_NAME );
		log("ClienteviajeservicioController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "Clienteviaje"
	 * @param model
	 */
	private void populateListOfClienteviajeItems(Model model) {
		List<Clienteviaje> list = clienteviajeService.findAll();
		List<ClienteviajeListItem> items = new LinkedList<ClienteviajeListItem>();
		for ( Clienteviaje clienteviaje : list ) {
			items.add(new ClienteviajeListItem( clienteviaje ) );
		}
		model.addAttribute("listOfClienteviajeItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "Vehiculoservicio"
	 * @param model
	 */
	private void populateListOfVehiculoservicioItems(Model model) {
		List<Vehiculoservicio> list = vehiculoservicioService.findAll();
		List<VehiculoservicioListItem> items = new LinkedList<VehiculoservicioListItem>();
		for ( Vehiculoservicio vehiculoservicio : list ) {
			items.add(new VehiculoservicioListItem( vehiculoservicio ) );
		}
		model.addAttribute("listOfVehiculoservicioItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param clienteviajeservicio
	 */
	private void populateModel(Model model, Clienteviajeservicio clienteviajeservicio, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, clienteviajeservicio);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfClienteviajeItems(model);
			populateListOfVehiculoservicioItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfClienteviajeItems(model);
			populateListOfVehiculoservicioItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Clienteviajeservicio found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<Clienteviajeservicio> list = clienteviajeservicioService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Clienteviajeservicio
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		Clienteviajeservicio clienteviajeservicio = new Clienteviajeservicio();	
		populateModel( model, clienteviajeservicio, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Clienteviajeservicio
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioService.findById(id);
		populateModel( model, clienteviajeservicio, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param clienteviajeservicio  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid Clienteviajeservicio clienteviajeservicio, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				Clienteviajeservicio clienteviajeservicioCreated = clienteviajeservicioService.create(clienteviajeservicio); 
				model.addAttribute(MAIN_ENTITY_NAME, clienteviajeservicioCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, clienteviajeservicio.getId() );
			} else {
				populateModel( model, clienteviajeservicio, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "clienteviajeservicio.error.create", e);
			populateModel( model, clienteviajeservicio, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param clienteviajeservicio  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid Clienteviajeservicio clienteviajeservicio, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				Clienteviajeservicio clienteviajeservicioSaved = clienteviajeservicioService.update(clienteviajeservicio);
				model.addAttribute(MAIN_ENTITY_NAME, clienteviajeservicioSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, clienteviajeservicio.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, clienteviajeservicio, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "clienteviajeservicio.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, clienteviajeservicio, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
		log("Action 'delete'" );
		try {
			clienteviajeservicioService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "clienteviajeservicio.error.delete", e);
		}
		return redirectToList();
	}

}