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
import com.tuin.bean.Entidad;
import com.tuin.test.EntidadFactoryForTest;

//--- Services 
import com.tuin.business.service.EntidadService;


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
public class EntidadControllerTest {
	
	@InjectMocks
	private EntidadController entidadController;
	@Mock
	private EntidadService entidadService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private EntidadFactoryForTest entidadFactoryForTest = new EntidadFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Entidad> list = new ArrayList<Entidad>();
		when(entidadService.findAll()).thenReturn(list);
		
		// When
		String viewName = entidadController.list(model);
		
		// Then
		assertEquals("entidad/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = entidadController.formForCreate(model);
		
		// Then
		assertEquals("entidad/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Entidad)modelMap.get("entidad")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidad/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		Long id = entidad.getId();
		when(entidadService.findById(id)).thenReturn(entidad);
		
		// When
		String viewName = entidadController.formForUpdate(model, id);
		
		// Then
		assertEquals("entidad/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidad, (Entidad) modelMap.get("entidad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidad/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Entidad entidadCreated = new Entidad();
		when(entidadService.create(entidad)).thenReturn(entidadCreated); 
		
		// When
		String viewName = entidadController.create(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/entidad/form/"+entidad.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadCreated, (Entidad) modelMap.get("entidad"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = entidadController.create(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidad, (Entidad) modelMap.get("entidad"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidad/create", modelMap.get("saveAction"));
		
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

		Entidad entidad = entidadFactoryForTest.newEntidad();
		
		Exception exception = new RuntimeException("test exception");
		when(entidadService.create(entidad)).thenThrow(exception);
		
		// When
		String viewName = entidadController.create(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidad, (Entidad) modelMap.get("entidad"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidad/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "entidad.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		Long id = entidad.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Entidad entidadSaved = new Entidad();
		entidadSaved.setId(id);
		when(entidadService.update(entidad)).thenReturn(entidadSaved); 
		
		// When
		String viewName = entidadController.update(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/entidad/form/"+entidad.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadSaved, (Entidad) modelMap.get("entidad"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = entidadController.update(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidad, (Entidad) modelMap.get("entidad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidad/update", modelMap.get("saveAction"));
		
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

		Entidad entidad = entidadFactoryForTest.newEntidad();
		
		Exception exception = new RuntimeException("test exception");
		when(entidadService.update(entidad)).thenThrow(exception);
		
		// When
		String viewName = entidadController.update(entidad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidad, (Entidad) modelMap.get("entidad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidad/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "entidad.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		Long id = entidad.getId();
		
		// When
		String viewName = entidadController.delete(redirectAttributes, id);
		
		// Then
		verify(entidadService).delete(id);
		assertEquals("redirect:/entidad", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Entidad entidad = entidadFactoryForTest.newEntidad();
		Long id = entidad.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(entidadService).delete(id);
		
		// When
		String viewName = entidadController.delete(redirectAttributes, id);
		
		// Then
		verify(entidadService).delete(id);
		assertEquals("redirect:/entidad", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "entidad.error.delete", exception);
	}
	
	
}
