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
import com.tuin.bean.Rol;
import com.tuin.test.RolFactoryForTest;

//--- Services 
import com.tuin.business.service.RolService;


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
public class RolControllerTest {
	
	@InjectMocks
	private RolController rolController;
	@Mock
	private RolService rolService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private RolFactoryForTest rolFactoryForTest = new RolFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Rol> list = new ArrayList<Rol>();
		when(rolService.findAll()).thenReturn(list);
		
		// When
		String viewName = rolController.list(model);
		
		// Then
		assertEquals("rol/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = rolController.formForCreate(model);
		
		// Then
		assertEquals("rol/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Rol)modelMap.get("rol")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rol/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rol rol = rolFactoryForTest.newRol();
		Long id = rol.getId();
		when(rolService.findById(id)).thenReturn(rol);
		
		// When
		String viewName = rolController.formForUpdate(model, id);
		
		// Then
		assertEquals("rol/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rol, (Rol) modelMap.get("rol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rol/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Rol rol = rolFactoryForTest.newRol();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Rol rolCreated = new Rol();
		when(rolService.create(rol)).thenReturn(rolCreated); 
		
		// When
		String viewName = rolController.create(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/rol/form/"+rol.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolCreated, (Rol) modelMap.get("rol"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rol rol = rolFactoryForTest.newRol();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = rolController.create(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rol, (Rol) modelMap.get("rol"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rol/create", modelMap.get("saveAction"));
		
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

		Rol rol = rolFactoryForTest.newRol();
		
		Exception exception = new RuntimeException("test exception");
		when(rolService.create(rol)).thenThrow(exception);
		
		// When
		String viewName = rolController.create(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rol, (Rol) modelMap.get("rol"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rol/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "rol.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Rol rol = rolFactoryForTest.newRol();
		Long id = rol.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Rol rolSaved = new Rol();
		rolSaved.setId(id);
		when(rolService.update(rol)).thenReturn(rolSaved); 
		
		// When
		String viewName = rolController.update(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/rol/form/"+rol.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolSaved, (Rol) modelMap.get("rol"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rol rol = rolFactoryForTest.newRol();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = rolController.update(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rol, (Rol) modelMap.get("rol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rol/update", modelMap.get("saveAction"));
		
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

		Rol rol = rolFactoryForTest.newRol();
		
		Exception exception = new RuntimeException("test exception");
		when(rolService.update(rol)).thenThrow(exception);
		
		// When
		String viewName = rolController.update(rol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rol, (Rol) modelMap.get("rol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rol/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "rol.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Rol rol = rolFactoryForTest.newRol();
		Long id = rol.getId();
		
		// When
		String viewName = rolController.delete(redirectAttributes, id);
		
		// Then
		verify(rolService).delete(id);
		assertEquals("redirect:/rol", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Rol rol = rolFactoryForTest.newRol();
		Long id = rol.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(rolService).delete(id);
		
		// When
		String viewName = rolController.delete(redirectAttributes, id);
		
		// Then
		verify(rolService).delete(id);
		assertEquals("redirect:/rol", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "rol.error.delete", exception);
	}
	
	
}
