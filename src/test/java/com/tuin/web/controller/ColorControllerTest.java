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
import com.tuin.bean.Color;
import com.tuin.test.ColorFactoryForTest;

//--- Services 
import com.tuin.business.service.ColorService;


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
public class ColorControllerTest {
	
	@InjectMocks
	private ColorController colorController;
	@Mock
	private ColorService colorService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ColorFactoryForTest colorFactoryForTest = new ColorFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Color> list = new ArrayList<Color>();
		when(colorService.findAll()).thenReturn(list);
		
		// When
		String viewName = colorController.list(model);
		
		// Then
		assertEquals("color/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = colorController.formForCreate(model);
		
		// Then
		assertEquals("color/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Color)modelMap.get("color")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/color/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Color color = colorFactoryForTest.newColor();
		Long id = color.getId();
		when(colorService.findById(id)).thenReturn(color);
		
		// When
		String viewName = colorController.formForUpdate(model, id);
		
		// Then
		assertEquals("color/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(color, (Color) modelMap.get("color"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/color/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Color color = colorFactoryForTest.newColor();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Color colorCreated = new Color();
		when(colorService.create(color)).thenReturn(colorCreated); 
		
		// When
		String viewName = colorController.create(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/color/form/"+color.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(colorCreated, (Color) modelMap.get("color"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Color color = colorFactoryForTest.newColor();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = colorController.create(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("color/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(color, (Color) modelMap.get("color"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/color/create", modelMap.get("saveAction"));
		
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

		Color color = colorFactoryForTest.newColor();
		
		Exception exception = new RuntimeException("test exception");
		when(colorService.create(color)).thenThrow(exception);
		
		// When
		String viewName = colorController.create(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("color/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(color, (Color) modelMap.get("color"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/color/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "color.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Color color = colorFactoryForTest.newColor();
		Long id = color.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Color colorSaved = new Color();
		colorSaved.setId(id);
		when(colorService.update(color)).thenReturn(colorSaved); 
		
		// When
		String viewName = colorController.update(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/color/form/"+color.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(colorSaved, (Color) modelMap.get("color"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Color color = colorFactoryForTest.newColor();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = colorController.update(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("color/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(color, (Color) modelMap.get("color"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/color/update", modelMap.get("saveAction"));
		
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

		Color color = colorFactoryForTest.newColor();
		
		Exception exception = new RuntimeException("test exception");
		when(colorService.update(color)).thenThrow(exception);
		
		// When
		String viewName = colorController.update(color, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("color/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(color, (Color) modelMap.get("color"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/color/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "color.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Color color = colorFactoryForTest.newColor();
		Long id = color.getId();
		
		// When
		String viewName = colorController.delete(redirectAttributes, id);
		
		// Then
		verify(colorService).delete(id);
		assertEquals("redirect:/color", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Color color = colorFactoryForTest.newColor();
		Long id = color.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(colorService).delete(id);
		
		// When
		String viewName = colorController.delete(redirectAttributes, id);
		
		// Then
		verify(colorService).delete(id);
		assertEquals("redirect:/color", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "color.error.delete", exception);
	}
	
	
}
