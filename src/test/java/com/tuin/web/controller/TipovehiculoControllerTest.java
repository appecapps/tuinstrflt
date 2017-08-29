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
import com.tuin.bean.Tipovehiculo;
import com.tuin.test.TipovehiculoFactoryForTest;

//--- Services 
import com.tuin.business.service.TipovehiculoService;


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
public class TipovehiculoControllerTest {
	
	@InjectMocks
	private TipovehiculoController tipovehiculoController;
	@Mock
	private TipovehiculoService tipovehiculoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TipovehiculoFactoryForTest tipovehiculoFactoryForTest = new TipovehiculoFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Tipovehiculo> list = new ArrayList<Tipovehiculo>();
		when(tipovehiculoService.findAll()).thenReturn(list);
		
		// When
		String viewName = tipovehiculoController.list(model);
		
		// Then
		assertEquals("tipovehiculo/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tipovehiculoController.formForCreate(model);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Tipovehiculo)modelMap.get("tipovehiculo")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipovehiculo/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		Long id = tipovehiculo.getId();
		when(tipovehiculoService.findById(id)).thenReturn(tipovehiculo);
		
		// When
		String viewName = tipovehiculoController.formForUpdate(model, id);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculo, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipovehiculo/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipovehiculo tipovehiculoCreated = new Tipovehiculo();
		when(tipovehiculoService.create(tipovehiculo)).thenReturn(tipovehiculoCreated); 
		
		// When
		String viewName = tipovehiculoController.create(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipovehiculo/form/"+tipovehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculoCreated, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipovehiculoController.create(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculo, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipovehiculo/create", modelMap.get("saveAction"));
		
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

		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(tipovehiculoService.create(tipovehiculo)).thenThrow(exception);
		
		// When
		String viewName = tipovehiculoController.create(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculo, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipovehiculo/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipovehiculo.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		Long id = tipovehiculo.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipovehiculo tipovehiculoSaved = new Tipovehiculo();
		tipovehiculoSaved.setId(id);
		when(tipovehiculoService.update(tipovehiculo)).thenReturn(tipovehiculoSaved); 
		
		// When
		String viewName = tipovehiculoController.update(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipovehiculo/form/"+tipovehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculoSaved, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipovehiculoController.update(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculo, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipovehiculo/update", modelMap.get("saveAction"));
		
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

		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(tipovehiculoService.update(tipovehiculo)).thenThrow(exception);
		
		// When
		String viewName = tipovehiculoController.update(tipovehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipovehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipovehiculo, (Tipovehiculo) modelMap.get("tipovehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipovehiculo/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipovehiculo.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		Long id = tipovehiculo.getId();
		
		// When
		String viewName = tipovehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(tipovehiculoService).delete(id);
		assertEquals("redirect:/tipovehiculo", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipovehiculo tipovehiculo = tipovehiculoFactoryForTest.newTipovehiculo();
		Long id = tipovehiculo.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tipovehiculoService).delete(id);
		
		// When
		String viewName = tipovehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(tipovehiculoService).delete(id);
		assertEquals("redirect:/tipovehiculo", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tipovehiculo.error.delete", exception);
	}
	
	
}
