/*
 * Created on 28 ago 2017 ( Time 17:51:45 )
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
import com.tuin.bean.Rolitemmenu;
import com.tuin.bean.Itemmenu;
import com.tuin.bean.Rol;

//--- Services 
import com.tuin.business.service.RolitemmenuService;
import com.tuin.business.service.ItemmenuService;
import com.tuin.business.service.RolService;

//--- List Items 
import com.tuin.web.listitem.ItemmenuListItem;
import com.tuin.web.listitem.RolListItem;

/**
 * Spring MVC controller for 'Rolitemmenu' management.
 */
@Controller
@RequestMapping("/rolitemmenu")
public class RolitemmenuController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "rolitemmenu";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "rolitemmenu/form";
	private static final String JSP_LIST   = "rolitemmenu/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/rolitemmenu/create";
	private static final String SAVE_ACTION_UPDATE   = "/rolitemmenu/update";

	//--- Main entity service
	@Resource
    private RolitemmenuService rolitemmenuService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ItemmenuService itemmenuService; // Injected by Spring
	@Resource
    private RolService rolService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public RolitemmenuController() {
		super(RolitemmenuController.class, MAIN_ENTITY_NAME );
		log("RolitemmenuController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "Itemmenu"
	 * @param model
	 */
	private void populateListOfItemmenuItems(Model model) {
		List<Itemmenu> list = itemmenuService.findAll();
		List<ItemmenuListItem> items = new LinkedList<ItemmenuListItem>();
		for ( Itemmenu itemmenu : list ) {
			items.add(new ItemmenuListItem( itemmenu ) );
		}
		model.addAttribute("listOfItemmenuItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "Rol"
	 * @param model
	 */
	private void populateListOfRolItems(Model model) {
		List<Rol> list = rolService.findAll();
		List<RolListItem> items = new LinkedList<RolListItem>();
		for ( Rol rol : list ) {
			items.add(new RolListItem( rol ) );
		}
		model.addAttribute("listOfRolItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param rolitemmenu
	 */
	private void populateModel(Model model, Rolitemmenu rolitemmenu, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, rolitemmenu);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfItemmenuItems(model);
			populateListOfRolItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfRolItems(model);
			populateListOfItemmenuItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Rolitemmenu found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<Rolitemmenu> list = rolitemmenuService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Rolitemmenu
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		Rolitemmenu rolitemmenu = new Rolitemmenu();	
		populateModel( model, rolitemmenu, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Rolitemmenu
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Rolitemmenu rolitemmenu = rolitemmenuService.findById(id);
		populateModel( model, rolitemmenu, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param rolitemmenu  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid Rolitemmenu rolitemmenu, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				Rolitemmenu rolitemmenuCreated = rolitemmenuService.create(rolitemmenu); 
				model.addAttribute(MAIN_ENTITY_NAME, rolitemmenuCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, rolitemmenu.getId() );
			} else {
				populateModel( model, rolitemmenu, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "rolitemmenu.error.create", e);
			populateModel( model, rolitemmenu, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param rolitemmenu  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid Rolitemmenu rolitemmenu, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				Rolitemmenu rolitemmenuSaved = rolitemmenuService.update(rolitemmenu);
				model.addAttribute(MAIN_ENTITY_NAME, rolitemmenuSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, rolitemmenu.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, rolitemmenu, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "rolitemmenu.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, rolitemmenu, FormMode.UPDATE);
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
			rolitemmenuService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "rolitemmenu.error.delete", e);
		}
		return redirectToList();
	}

}
