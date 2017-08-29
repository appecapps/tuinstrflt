package com.tuin.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Entities
import com.tuin.bean.Itemmenu;
import com.tuin.bean.Menu;
import com.tuin.test.ItemmenuFactoryForTest;
import com.tuin.test.MenuFactoryForTest;

//--- Services 
import com.tuin.business.service.ItemmenuService;
import com.tuin.business.service.MenuService;

//--- List Items 
import com.tuin.web.listitem.MenuListItem;

import com.tuin.web.common.Message;
import com.tuin.web.common.MessageHelper;
import com.tuin.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class ItemmenuControllerTest {
	
	@InjectMocks
	private ItemmenuController itemmenuController;
	@Mock
	private ItemmenuService itemmenuService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private MenuService menuService; // Injected by Spring

	private ItemmenuFactoryForTest itemmenuFactoryForTest = new ItemmenuFactoryForTest();
	private MenuFactoryForTest menuFactoryForTest = new MenuFactoryForTest();

	List<Menu> menus = new ArrayList<Menu>();

	private void givenPopulateModel() {
		Menu menu1 = menuFactoryForTest.newMenu();
		Menu menu2 = menuFactoryForTest.newMenu();
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(menu1);
		menus.add(menu2);
		when(menuService.findAll()).thenReturn(menus);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Itemmenu> list = new ArrayList<Itemmenu>();
		when(itemmenuService.findAll()).thenReturn(list);
		
		// When
		String viewName = itemmenuController.list(model);
		
		// Then
		assertEquals("itemmenu/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = itemmenuController.formForCreate(model);
		
		// Then
		assertEquals("itemmenu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Itemmenu)modelMap.get("itemmenu")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/itemmenu/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		Long id = itemmenu.getId();
		when(itemmenuService.findById(id)).thenReturn(itemmenu);
		
		// When
		String viewName = itemmenuController.formForUpdate(model, id);
		
		// Then
		assertEquals("itemmenu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenu, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/itemmenu/update", modelMap.get("saveAction"));
		
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Itemmenu itemmenuCreated = new Itemmenu();
		when(itemmenuService.create(itemmenu)).thenReturn(itemmenuCreated); 
		
		// When
		String viewName = itemmenuController.create(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/itemmenu/form/"+itemmenu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenuCreated, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = itemmenuController.create(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("itemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenu, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/itemmenu/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		
		Exception exception = new RuntimeException("test exception");
		when(itemmenuService.create(itemmenu)).thenThrow(exception);
		
		// When
		String viewName = itemmenuController.create(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("itemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenu, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/itemmenu/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "itemmenu.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		Long id = itemmenu.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Itemmenu itemmenuSaved = new Itemmenu();
		itemmenuSaved.setId(id);
		when(itemmenuService.update(itemmenu)).thenReturn(itemmenuSaved); 
		
		// When
		String viewName = itemmenuController.update(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/itemmenu/form/"+itemmenu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenuSaved, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = itemmenuController.update(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("itemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenu, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/itemmenu/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		
		Exception exception = new RuntimeException("test exception");
		when(itemmenuService.update(itemmenu)).thenThrow(exception);
		
		// When
		String viewName = itemmenuController.update(itemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("itemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(itemmenu, (Itemmenu) modelMap.get("itemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/itemmenu/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "itemmenu.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<MenuListItem> menuListItems = (List<MenuListItem>) modelMap.get("listOfMenuItems");
		assertEquals(2, menuListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		Long id = itemmenu.getId();
		
		// When
		String viewName = itemmenuController.delete(redirectAttributes, id);
		
		// Then
		verify(itemmenuService).delete(id);
		assertEquals("redirect:/itemmenu", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		Long id = itemmenu.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(itemmenuService).delete(id);
		
		// When
		String viewName = itemmenuController.delete(redirectAttributes, id);
		
		// Then
		verify(itemmenuService).delete(id);
		assertEquals("redirect:/itemmenu", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "itemmenu.error.delete", exception);
	}
	
	
}
