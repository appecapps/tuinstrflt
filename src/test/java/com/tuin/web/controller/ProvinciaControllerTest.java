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
import com.tuin.bean.Provincia;
import com.tuin.test.ProvinciaFactoryForTest;

//--- Services 
import com.tuin.business.service.ProvinciaService;


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
public class ProvinciaControllerTest {
	
	@InjectMocks
	private ProvinciaController provinciaController;
	@Mock
	private ProvinciaService provinciaService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ProvinciaFactoryForTest provinciaFactoryForTest = new ProvinciaFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Provincia> list = new ArrayList<Provincia>();
		when(provinciaService.findAll()).thenReturn(list);
		
		// When
		String viewName = provinciaController.list(model);
		
		// Then
		assertEquals("provincia/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = provinciaController.formForCreate(model);
		
		// Then
		assertEquals("provincia/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Provincia)modelMap.get("provincia")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/provincia/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		Long id = provincia.getId();
		when(provinciaService.findById(id)).thenReturn(provincia);
		
		// When
		String viewName = provinciaController.formForUpdate(model, id);
		
		// Then
		assertEquals("provincia/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provincia, (Provincia) modelMap.get("provincia"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/provincia/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Provincia provinciaCreated = new Provincia();
		when(provinciaService.create(provincia)).thenReturn(provinciaCreated); 
		
		// When
		String viewName = provinciaController.create(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/provincia/form/"+provincia.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provinciaCreated, (Provincia) modelMap.get("provincia"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = provinciaController.create(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("provincia/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provincia, (Provincia) modelMap.get("provincia"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/provincia/create", modelMap.get("saveAction"));
		
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

		Provincia provincia = provinciaFactoryForTest.newProvincia();
		
		Exception exception = new RuntimeException("test exception");
		when(provinciaService.create(provincia)).thenThrow(exception);
		
		// When
		String viewName = provinciaController.create(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("provincia/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provincia, (Provincia) modelMap.get("provincia"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/provincia/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "provincia.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		Long id = provincia.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Provincia provinciaSaved = new Provincia();
		provinciaSaved.setId(id);
		when(provinciaService.update(provincia)).thenReturn(provinciaSaved); 
		
		// When
		String viewName = provinciaController.update(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/provincia/form/"+provincia.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provinciaSaved, (Provincia) modelMap.get("provincia"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = provinciaController.update(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("provincia/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provincia, (Provincia) modelMap.get("provincia"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/provincia/update", modelMap.get("saveAction"));
		
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

		Provincia provincia = provinciaFactoryForTest.newProvincia();
		
		Exception exception = new RuntimeException("test exception");
		when(provinciaService.update(provincia)).thenThrow(exception);
		
		// When
		String viewName = provinciaController.update(provincia, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("provincia/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(provincia, (Provincia) modelMap.get("provincia"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/provincia/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "provincia.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		Long id = provincia.getId();
		
		// When
		String viewName = provinciaController.delete(redirectAttributes, id);
		
		// Then
		verify(provinciaService).delete(id);
		assertEquals("redirect:/provincia", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Provincia provincia = provinciaFactoryForTest.newProvincia();
		Long id = provincia.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(provinciaService).delete(id);
		
		// When
		String viewName = provinciaController.delete(redirectAttributes, id);
		
		// Then
		verify(provinciaService).delete(id);
		assertEquals("redirect:/provincia", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "provincia.error.delete", exception);
	}
	
	
}
