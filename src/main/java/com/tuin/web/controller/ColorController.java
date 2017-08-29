/*
 * Created on 28 ago 2017 ( Time 17:51:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.web.controller;

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
import com.tuin.bean.Color;

//--- Services 
import com.tuin.business.service.ColorService;


/**
 * Spring MVC controller for 'Color' management.
 */
@Controller
@RequestMapping("/color")
public class ColorController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "color";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "color/form";
	private static final String JSP_LIST   = "color/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/color/create";
	private static final String SAVE_ACTION_UPDATE   = "/color/update";

	//--- Main entity service
	@Resource
    private ColorService colorService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ColorController() {
		super(ColorController.class, MAIN_ENTITY_NAME );
		log("ColorController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param color
	 */
	private void populateModel(Model model, Color color, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, color);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of Color found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<Color> list = colorService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new Color
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		Color color = new Color();	
		populateModel( model, color, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing Color
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		Color color = colorService.findById(id);
		populateModel( model, color, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param color  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid Color color, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				Color colorCreated = colorService.create(color); 
				model.addAttribute(MAIN_ENTITY_NAME, colorCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, color.getId() );
			} else {
				populateModel( model, color, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "color.error.create", e);
			populateModel( model, color, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param color  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid Color color, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				Color colorSaved = colorService.update(color);
				model.addAttribute(MAIN_ENTITY_NAME, colorSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, color.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, color, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "color.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, color, FormMode.UPDATE);
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
			colorService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "color.error.delete", e);
		}
		return redirectToList();
	}

}
