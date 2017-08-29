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
import com.tuin.bean.Marca;
import com.tuin.test.MarcaFactoryForTest;

//--- Services 
import com.tuin.business.service.MarcaService;


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
public class MarcaControllerTest {
	
	@InjectMocks
	private MarcaController marcaController;
	@Mock
	private MarcaService marcaService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private MarcaFactoryForTest marcaFactoryForTest = new MarcaFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Marca> list = new ArrayList<Marca>();
		when(marcaService.findAll()).thenReturn(list);
		
		// When
		String viewName = marcaController.list(model);
		
		// Then
		assertEquals("marca/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = marcaController.formForCreate(model);
		
		// Then
		assertEquals("marca/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Marca)modelMap.get("marca")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/marca/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Marca marca = marcaFactoryForTest.newMarca();
		Long id = marca.getId();
		when(marcaService.findById(id)).thenReturn(marca);
		
		// When
		String viewName = marcaController.formForUpdate(model, id);
		
		// Then
		assertEquals("marca/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marca, (Marca) modelMap.get("marca"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/marca/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Marca marca = marcaFactoryForTest.newMarca();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Marca marcaCreated = new Marca();
		when(marcaService.create(marca)).thenReturn(marcaCreated); 
		
		// When
		String viewName = marcaController.create(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/marca/form/"+marca.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marcaCreated, (Marca) modelMap.get("marca"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Marca marca = marcaFactoryForTest.newMarca();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = marcaController.create(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("marca/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marca, (Marca) modelMap.get("marca"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/marca/create", modelMap.get("saveAction"));
		
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

		Marca marca = marcaFactoryForTest.newMarca();
		
		Exception exception = new RuntimeException("test exception");
		when(marcaService.create(marca)).thenThrow(exception);
		
		// When
		String viewName = marcaController.create(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("marca/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marca, (Marca) modelMap.get("marca"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/marca/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "marca.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Marca marca = marcaFactoryForTest.newMarca();
		Long id = marca.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Marca marcaSaved = new Marca();
		marcaSaved.setId(id);
		when(marcaService.update(marca)).thenReturn(marcaSaved); 
		
		// When
		String viewName = marcaController.update(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/marca/form/"+marca.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marcaSaved, (Marca) modelMap.get("marca"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Marca marca = marcaFactoryForTest.newMarca();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = marcaController.update(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("marca/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marca, (Marca) modelMap.get("marca"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/marca/update", modelMap.get("saveAction"));
		
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

		Marca marca = marcaFactoryForTest.newMarca();
		
		Exception exception = new RuntimeException("test exception");
		when(marcaService.update(marca)).thenThrow(exception);
		
		// When
		String viewName = marcaController.update(marca, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("marca/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(marca, (Marca) modelMap.get("marca"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/marca/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "marca.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Marca marca = marcaFactoryForTest.newMarca();
		Long id = marca.getId();
		
		// When
		String viewName = marcaController.delete(redirectAttributes, id);
		
		// Then
		verify(marcaService).delete(id);
		assertEquals("redirect:/marca", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Marca marca = marcaFactoryForTest.newMarca();
		Long id = marca.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(marcaService).delete(id);
		
		// When
		String viewName = marcaController.delete(redirectAttributes, id);
		
		// Then
		verify(marcaService).delete(id);
		assertEquals("redirect:/marca", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "marca.error.delete", exception);
	}
	
	
}
