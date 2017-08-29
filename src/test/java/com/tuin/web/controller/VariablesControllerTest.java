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
import com.tuin.bean.Variables;
import com.tuin.test.VariablesFactoryForTest;

//--- Services 
import com.tuin.business.service.VariablesService;


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
public class VariablesControllerTest {
	
	@InjectMocks
	private VariablesController variablesController;
	@Mock
	private VariablesService variablesService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private VariablesFactoryForTest variablesFactoryForTest = new VariablesFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Variables> list = new ArrayList<Variables>();
		when(variablesService.findAll()).thenReturn(list);
		
		// When
		String viewName = variablesController.list(model);
		
		// Then
		assertEquals("variables/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = variablesController.formForCreate(model);
		
		// Then
		assertEquals("variables/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Variables)modelMap.get("variables")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/variables/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Variables variables = variablesFactoryForTest.newVariables();
		Long id = variables.getId();
		when(variablesService.findById(id)).thenReturn(variables);
		
		// When
		String viewName = variablesController.formForUpdate(model, id);
		
		// Then
		assertEquals("variables/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variables, (Variables) modelMap.get("variables"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/variables/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Variables variables = variablesFactoryForTest.newVariables();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Variables variablesCreated = new Variables();
		when(variablesService.create(variables)).thenReturn(variablesCreated); 
		
		// When
		String viewName = variablesController.create(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/variables/form/"+variables.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variablesCreated, (Variables) modelMap.get("variables"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Variables variables = variablesFactoryForTest.newVariables();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = variablesController.create(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("variables/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variables, (Variables) modelMap.get("variables"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/variables/create", modelMap.get("saveAction"));
		
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

		Variables variables = variablesFactoryForTest.newVariables();
		
		Exception exception = new RuntimeException("test exception");
		when(variablesService.create(variables)).thenThrow(exception);
		
		// When
		String viewName = variablesController.create(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("variables/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variables, (Variables) modelMap.get("variables"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/variables/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "variables.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Variables variables = variablesFactoryForTest.newVariables();
		Long id = variables.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Variables variablesSaved = new Variables();
		variablesSaved.setId(id);
		when(variablesService.update(variables)).thenReturn(variablesSaved); 
		
		// When
		String viewName = variablesController.update(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/variables/form/"+variables.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variablesSaved, (Variables) modelMap.get("variables"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Variables variables = variablesFactoryForTest.newVariables();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = variablesController.update(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("variables/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variables, (Variables) modelMap.get("variables"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/variables/update", modelMap.get("saveAction"));
		
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

		Variables variables = variablesFactoryForTest.newVariables();
		
		Exception exception = new RuntimeException("test exception");
		when(variablesService.update(variables)).thenThrow(exception);
		
		// When
		String viewName = variablesController.update(variables, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("variables/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(variables, (Variables) modelMap.get("variables"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/variables/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "variables.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Variables variables = variablesFactoryForTest.newVariables();
		Long id = variables.getId();
		
		// When
		String viewName = variablesController.delete(redirectAttributes, id);
		
		// Then
		verify(variablesService).delete(id);
		assertEquals("redirect:/variables", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Variables variables = variablesFactoryForTest.newVariables();
		Long id = variables.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(variablesService).delete(id);
		
		// When
		String viewName = variablesController.delete(redirectAttributes, id);
		
		// Then
		verify(variablesService).delete(id);
		assertEquals("redirect:/variables", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "variables.error.delete", exception);
	}
	
	
}
