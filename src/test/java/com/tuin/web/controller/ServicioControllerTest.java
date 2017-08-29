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
import com.tuin.bean.Servicio;
import com.tuin.test.ServicioFactoryForTest;

//--- Services 
import com.tuin.business.service.ServicioService;


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
public class ServicioControllerTest {
	
	@InjectMocks
	private ServicioController servicioController;
	@Mock
	private ServicioService servicioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ServicioFactoryForTest servicioFactoryForTest = new ServicioFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Servicio> list = new ArrayList<Servicio>();
		when(servicioService.findAll()).thenReturn(list);
		
		// When
		String viewName = servicioController.list(model);
		
		// Then
		assertEquals("servicio/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = servicioController.formForCreate(model);
		
		// Then
		assertEquals("servicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Servicio)modelMap.get("servicio")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/servicio/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		Long id = servicio.getId();
		when(servicioService.findById(id)).thenReturn(servicio);
		
		// When
		String viewName = servicioController.formForUpdate(model, id);
		
		// Then
		assertEquals("servicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicio, (Servicio) modelMap.get("servicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/servicio/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Servicio servicioCreated = new Servicio();
		when(servicioService.create(servicio)).thenReturn(servicioCreated); 
		
		// When
		String viewName = servicioController.create(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/servicio/form/"+servicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicioCreated, (Servicio) modelMap.get("servicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = servicioController.create(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("servicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicio, (Servicio) modelMap.get("servicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/servicio/create", modelMap.get("saveAction"));
		
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

		Servicio servicio = servicioFactoryForTest.newServicio();
		
		Exception exception = new RuntimeException("test exception");
		when(servicioService.create(servicio)).thenThrow(exception);
		
		// When
		String viewName = servicioController.create(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("servicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicio, (Servicio) modelMap.get("servicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/servicio/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "servicio.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		Long id = servicio.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Servicio servicioSaved = new Servicio();
		servicioSaved.setId(id);
		when(servicioService.update(servicio)).thenReturn(servicioSaved); 
		
		// When
		String viewName = servicioController.update(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/servicio/form/"+servicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicioSaved, (Servicio) modelMap.get("servicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = servicioController.update(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("servicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicio, (Servicio) modelMap.get("servicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/servicio/update", modelMap.get("saveAction"));
		
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

		Servicio servicio = servicioFactoryForTest.newServicio();
		
		Exception exception = new RuntimeException("test exception");
		when(servicioService.update(servicio)).thenThrow(exception);
		
		// When
		String viewName = servicioController.update(servicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("servicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(servicio, (Servicio) modelMap.get("servicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/servicio/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "servicio.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		Long id = servicio.getId();
		
		// When
		String viewName = servicioController.delete(redirectAttributes, id);
		
		// Then
		verify(servicioService).delete(id);
		assertEquals("redirect:/servicio", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Servicio servicio = servicioFactoryForTest.newServicio();
		Long id = servicio.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(servicioService).delete(id);
		
		// When
		String viewName = servicioController.delete(redirectAttributes, id);
		
		// Then
		verify(servicioService).delete(id);
		assertEquals("redirect:/servicio", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "servicio.error.delete", exception);
	}
	
	
}
