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
import com.tuin.bean.Tipocomponentefinanciero;
import com.tuin.test.TipocomponentefinancieroFactoryForTest;

//--- Services 
import com.tuin.business.service.TipocomponentefinancieroService;


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
public class TipocomponentefinancieroControllerTest {
	
	@InjectMocks
	private TipocomponentefinancieroController tipocomponentefinancieroController;
	@Mock
	private TipocomponentefinancieroService tipocomponentefinancieroService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TipocomponentefinancieroFactoryForTest tipocomponentefinancieroFactoryForTest = new TipocomponentefinancieroFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Tipocomponentefinanciero> list = new ArrayList<Tipocomponentefinanciero>();
		when(tipocomponentefinancieroService.findAll()).thenReturn(list);
		
		// When
		String viewName = tipocomponentefinancieroController.list(model);
		
		// Then
		assertEquals("tipocomponentefinanciero/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tipocomponentefinancieroController.formForCreate(model);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Tipocomponentefinanciero)modelMap.get("tipocomponentefinanciero")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		Long id = tipocomponentefinanciero.getId();
		when(tipocomponentefinancieroService.findById(id)).thenReturn(tipocomponentefinanciero);
		
		// When
		String viewName = tipocomponentefinancieroController.formForUpdate(model, id);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinanciero, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipocomponentefinanciero tipocomponentefinancieroCreated = new Tipocomponentefinanciero();
		when(tipocomponentefinancieroService.create(tipocomponentefinanciero)).thenReturn(tipocomponentefinancieroCreated); 
		
		// When
		String viewName = tipocomponentefinancieroController.create(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipocomponentefinanciero/form/"+tipocomponentefinanciero.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinancieroCreated, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipocomponentefinancieroController.create(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinanciero, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/create", modelMap.get("saveAction"));
		
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

		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		
		Exception exception = new RuntimeException("test exception");
		when(tipocomponentefinancieroService.create(tipocomponentefinanciero)).thenThrow(exception);
		
		// When
		String viewName = tipocomponentefinancieroController.create(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinanciero, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipocomponentefinanciero.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		Long id = tipocomponentefinanciero.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipocomponentefinanciero tipocomponentefinancieroSaved = new Tipocomponentefinanciero();
		tipocomponentefinancieroSaved.setId(id);
		when(tipocomponentefinancieroService.update(tipocomponentefinanciero)).thenReturn(tipocomponentefinancieroSaved); 
		
		// When
		String viewName = tipocomponentefinancieroController.update(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipocomponentefinanciero/form/"+tipocomponentefinanciero.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinancieroSaved, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipocomponentefinancieroController.update(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinanciero, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/update", modelMap.get("saveAction"));
		
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

		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		
		Exception exception = new RuntimeException("test exception");
		when(tipocomponentefinancieroService.update(tipocomponentefinanciero)).thenThrow(exception);
		
		// When
		String viewName = tipocomponentefinancieroController.update(tipocomponentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocomponentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocomponentefinanciero, (Tipocomponentefinanciero) modelMap.get("tipocomponentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocomponentefinanciero/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipocomponentefinanciero.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		Long id = tipocomponentefinanciero.getId();
		
		// When
		String viewName = tipocomponentefinancieroController.delete(redirectAttributes, id);
		
		// Then
		verify(tipocomponentefinancieroService).delete(id);
		assertEquals("redirect:/tipocomponentefinanciero", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipocomponentefinanciero tipocomponentefinanciero = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		Long id = tipocomponentefinanciero.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tipocomponentefinancieroService).delete(id);
		
		// When
		String viewName = tipocomponentefinancieroController.delete(redirectAttributes, id);
		
		// Then
		verify(tipocomponentefinancieroService).delete(id);
		assertEquals("redirect:/tipocomponentefinanciero", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tipocomponentefinanciero.error.delete", exception);
	}
	
	
}
