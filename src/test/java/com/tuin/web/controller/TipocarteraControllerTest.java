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
import com.tuin.bean.Tipocartera;
import com.tuin.test.TipocarteraFactoryForTest;

//--- Services 
import com.tuin.business.service.TipocarteraService;


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
public class TipocarteraControllerTest {
	
	@InjectMocks
	private TipocarteraController tipocarteraController;
	@Mock
	private TipocarteraService tipocarteraService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TipocarteraFactoryForTest tipocarteraFactoryForTest = new TipocarteraFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Tipocartera> list = new ArrayList<Tipocartera>();
		when(tipocarteraService.findAll()).thenReturn(list);
		
		// When
		String viewName = tipocarteraController.list(model);
		
		// Then
		assertEquals("tipocartera/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tipocarteraController.formForCreate(model);
		
		// Then
		assertEquals("tipocartera/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Tipocartera)modelMap.get("tipocartera")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocartera/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		Long id = tipocartera.getId();
		when(tipocarteraService.findById(id)).thenReturn(tipocartera);
		
		// When
		String viewName = tipocarteraController.formForUpdate(model, id);
		
		// Then
		assertEquals("tipocartera/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocartera, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocartera/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipocartera tipocarteraCreated = new Tipocartera();
		when(tipocarteraService.create(tipocartera)).thenReturn(tipocarteraCreated); 
		
		// When
		String viewName = tipocarteraController.create(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipocartera/form/"+tipocartera.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocarteraCreated, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipocarteraController.create(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocartera/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocartera, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocartera/create", modelMap.get("saveAction"));
		
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

		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		
		Exception exception = new RuntimeException("test exception");
		when(tipocarteraService.create(tipocartera)).thenThrow(exception);
		
		// When
		String viewName = tipocarteraController.create(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocartera/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocartera, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipocartera/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipocartera.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		Long id = tipocartera.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipocartera tipocarteraSaved = new Tipocartera();
		tipocarteraSaved.setId(id);
		when(tipocarteraService.update(tipocartera)).thenReturn(tipocarteraSaved); 
		
		// When
		String viewName = tipocarteraController.update(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipocartera/form/"+tipocartera.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocarteraSaved, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipocarteraController.update(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocartera/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocartera, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocartera/update", modelMap.get("saveAction"));
		
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

		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		
		Exception exception = new RuntimeException("test exception");
		when(tipocarteraService.update(tipocartera)).thenThrow(exception);
		
		// When
		String viewName = tipocarteraController.update(tipocartera, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipocartera/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipocartera, (Tipocartera) modelMap.get("tipocartera"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipocartera/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipocartera.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		Long id = tipocartera.getId();
		
		// When
		String viewName = tipocarteraController.delete(redirectAttributes, id);
		
		// Then
		verify(tipocarteraService).delete(id);
		assertEquals("redirect:/tipocartera", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipocartera tipocartera = tipocarteraFactoryForTest.newTipocartera();
		Long id = tipocartera.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tipocarteraService).delete(id);
		
		// When
		String viewName = tipocarteraController.delete(redirectAttributes, id);
		
		// Then
		verify(tipocarteraService).delete(id);
		assertEquals("redirect:/tipocartera", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tipocartera.error.delete", exception);
	}
	
	
}
