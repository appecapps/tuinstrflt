/*
 * Created on 28 ago 2017 ( Time 17:51:43 )
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
import com.tuin.bean.Itemmenu;
import com.tuin.bean.Menu;

//--- Services 
import com.tuin.business.service.ItemmenuService;
import com.tuin.business.service.MenuService;

//--- List Items 
import com.tuin.web.listitem.MenuListItem;

/**
 * Spring MVC controller for 'Itemmenu' management.
 */
@Controller
@RequestMapping("/itemmenu")
public class ItemmenuController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "itemmenu";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "itemmenu/form";
	private static final String JSP_LIST   = "itemmenu/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/itemmenu/create";
	private static final String SAVE_ACTION_UPDATE   = "/itemmenu/update";

	//--- Main entity service
	@Resource
    private ItemmenuService itemmenuService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private MenuService menuService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ItemmenuController() {
		super(ItemmenuController.class, MAIN_ENTITY_NAME );
		log("ItemmenuController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "Menu"
	 * @param model
	 */
	private void populateListOfMenuItems(Model model) {
		List<Menu> list = menuService.findAll();
		List<MenuListItem> items = new LinkedList<MenuListItem>();
		for ( Menu menu : list ) {
			items.add(new MenuListItem( menu ) );
		}
		model.addAttribute("listOfMenuItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param itemmenu
	 */
	private void populateModel(Model model, Itemmenu itemmenu, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, itemmenu);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfMenuItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfMenuItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Itemmenu found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<Itemmenu> list = itemmenuService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Itemmenu
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		Itemmenu itemmenu = new Itemmenu();	
		populateModel( model, itemmenu, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Itemmenu
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Itemmenu itemmenu = itemmenuService.findById(id);
		populateModel( model, itemmenu, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param itemmenu  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid Itemmenu itemmenu, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				Itemmenu itemmenuCreated = itemmenuService.create(itemmenu); 
				model.addAttribute(MAIN_ENTITY_NAME, itemmenuCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, itemmenu.getId() );
			} else {
				populateModel( model, itemmenu, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "itemmenu.error.create", e);
			populateModel( model, itemmenu, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param itemmenu  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid Itemmenu itemmenu, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				Itemmenu itemmenuSaved = itemmenuService.update(itemmenu);
				model.addAttribute(MAIN_ENTITY_NAME, itemmenuSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, itemmenu.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, itemmenu, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "itemmenu.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, itemmenu, FormMode.UPDATE);
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
			itemmenuService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "itemmenu.error.delete", e);
		}
		return redirectToList();
	}

}