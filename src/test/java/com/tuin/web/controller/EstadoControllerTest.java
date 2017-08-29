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
import com.tuin.bean.Estado;
import com.tuin.test.EstadoFactoryForTest;

//--- Services 
import com.tuin.business.service.EstadoService;


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
public class EstadoControllerTest {
	
	@InjectMocks
	private EstadoController estadoController;
	@Mock
	private EstadoService estadoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private EstadoFactoryForTest estadoFactoryForTest = new EstadoFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Estado> list = new ArrayList<Estado>();
		when(estadoService.findAll()).thenReturn(list);
		
		// When
		String viewName = estadoController.list(model);
		
		// Then
		assertEquals("estado/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = estadoController.formForCreate(model);
		
		// Then
		assertEquals("estado/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Estado)modelMap.get("estado")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/estado/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Estado estado = estadoFactoryForTest.newEstado();
		Long id = estado.getId();
		when(estadoService.findById(id)).thenReturn(estado);
		
		// When
		String viewName = estadoController.formForUpdate(model, id);
		
		// Then
		assertEquals("estado/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estado, (Estado) modelMap.get("estado"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/estado/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Estado estado = estadoFactoryForTest.newEstado();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Estado estadoCreated = new Estado();
		when(estadoService.create(estado)).thenReturn(estadoCreated); 
		
		// When
		String viewName = estadoController.create(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/estado/form/"+estado.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estadoCreated, (Estado) modelMap.get("estado"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Estado estado = estadoFactoryForTest.newEstado();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = estadoController.create(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("estado/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estado, (Estado) modelMap.get("estado"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/estado/create", modelMap.get("saveAction"));
		
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

		Estado estado = estadoFactoryForTest.newEstado();
		
		Exception exception = new RuntimeException("test exception");
		when(estadoService.create(estado)).thenThrow(exception);
		
		// When
		String viewName = estadoController.create(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("estado/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estado, (Estado) modelMap.get("estado"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/estado/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "estado.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Estado estado = estadoFactoryForTest.newEstado();
		Long id = estado.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Estado estadoSaved = new Estado();
		estadoSaved.setId(id);
		when(estadoService.update(estado)).thenReturn(estadoSaved); 
		
		// When
		String viewName = estadoController.update(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/estado/form/"+estado.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estadoSaved, (Estado) modelMap.get("estado"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Estado estado = estadoFactoryForTest.newEstado();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = estadoController.update(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("estado/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estado, (Estado) modelMap.get("estado"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/estado/update", modelMap.get("saveAction"));
		
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

		Estado estado = estadoFactoryForTest.newEstado();
		
		Exception exception = new RuntimeException("test exception");
		when(estadoService.update(estado)).thenThrow(exception);
		
		// When
		String viewName = estadoController.update(estado, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("estado/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(estado, (Estado) modelMap.get("estado"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/estado/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "estado.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Estado estado = estadoFactoryForTest.newEstado();
		Long id = estado.getId();
		
		// When
		String viewName = estadoController.delete(redirectAttributes, id);
		
		// Then
		verify(estadoService).delete(id);
		assertEquals("redirect:/estado", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Estado estado = estadoFactoryForTest.newEstado();
		Long id = estado.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(estadoService).delete(id);
		
		// When
		String viewName = estadoController.delete(redirectAttributes, id);
		
		// Then
		verify(estadoService).delete(id);
		assertEquals("redirect:/estado", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "estado.error.delete", exception);
	}
	
	
}
