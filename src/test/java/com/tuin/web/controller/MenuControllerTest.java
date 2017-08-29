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
import com.tuin.bean.Menu;
import com.tuin.test.MenuFactoryForTest;

//--- Services 
import com.tuin.business.service.MenuService;


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
public class MenuControllerTest {
	
	@InjectMocks
	private MenuController menuController;
	@Mock
	private MenuService menuService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private MenuFactoryForTest menuFactoryForTest = new MenuFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Menu> list = new ArrayList<Menu>();
		when(menuService.findAll()).thenReturn(list);
		
		// When
		String viewName = menuController.list(model);
		
		// Then
		assertEquals("menu/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = menuController.formForCreate(model);
		
		// Then
		assertEquals("menu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Menu)modelMap.get("menu")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/menu/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Menu menu = menuFactoryForTest.newMenu();
		Long id = menu.getId();
		when(menuService.findById(id)).thenReturn(menu);
		
		// When
		String viewName = menuController.formForUpdate(model, id);
		
		// Then
		assertEquals("menu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menu, (Menu) modelMap.get("menu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/menu/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Menu menu = menuFactoryForTest.newMenu();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Menu menuCreated = new Menu();
		when(menuService.create(menu)).thenReturn(menuCreated); 
		
		// When
		String viewName = menuController.create(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/menu/form/"+menu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menuCreated, (Menu) modelMap.get("menu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Menu menu = menuFactoryForTest.newMenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = menuController.create(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("menu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menu, (Menu) modelMap.get("menu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/menu/create", modelMap.get("saveAction"));
		
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

		Menu menu = menuFactoryForTest.newMenu();
		
		Exception exception = new RuntimeException("test exception");
		when(menuService.create(menu)).thenThrow(exception);
		
		// When
		String viewName = menuController.create(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("menu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menu, (Menu) modelMap.get("menu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/menu/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "menu.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Menu menu = menuFactoryForTest.newMenu();
		Long id = menu.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Menu menuSaved = new Menu();
		menuSaved.setId(id);
		when(menuService.update(menu)).thenReturn(menuSaved); 
		
		// When
		String viewName = menuController.update(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/menu/form/"+menu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menuSaved, (Menu) modelMap.get("menu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Menu menu = menuFactoryForTest.newMenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = menuController.update(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("menu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menu, (Menu) modelMap.get("menu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/menu/update", modelMap.get("saveAction"));
		
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

		Menu menu = menuFactoryForTest.newMenu();
		
		Exception exception = new RuntimeException("test exception");
		when(menuService.update(menu)).thenThrow(exception);
		
		// When
		String viewName = menuController.update(menu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("menu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(menu, (Menu) modelMap.get("menu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/menu/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "menu.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Menu menu = menuFactoryForTest.newMenu();
		Long id = menu.getId();
		
		// When
		String viewName = menuController.delete(redirectAttributes, id);
		
		// Then
		verify(menuService).delete(id);
		assertEquals("redirect:/menu", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Menu menu = menuFactoryForTest.newMenu();
		Long id = menu.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(menuService).delete(id);
		
		// When
		String viewName = menuController.delete(redirectAttributes, id);
		
		// Then
		verify(menuService).delete(id);
		assertEquals("redirect:/menu", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "menu.error.delete", exception);
	}
	
	
}
