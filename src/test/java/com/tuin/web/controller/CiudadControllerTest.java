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
import com.tuin.bean.Ciudad;
import com.tuin.bean.Provincia;
import com.tuin.test.CiudadFactoryForTest;
import com.tuin.test.ProvinciaFactoryForTest;

//--- Services 
import com.tuin.business.service.CiudadService;
import com.tuin.business.service.ProvinciaService;

//--- List Items 
import com.tuin.web.listitem.ProvinciaListItem;

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
public class CiudadControllerTest {
	
	@InjectMocks
	private CiudadController ciudadController;
	@Mock
	private CiudadService ciudadService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ProvinciaService provinciaService; // Injected by Spring

	private CiudadFactoryForTest ciudadFactoryForTest = new CiudadFactoryForTest();
	private ProvinciaFactoryForTest provinciaFactoryForTest = new ProvinciaFactoryForTest();

	List<Provincia> provincias = new ArrayList<Provincia>();

	private void givenPopulateModel() {
		Provincia provincia1 = provinciaFactoryForTest.newProvincia();
		Provincia provincia2 = provinciaFactoryForTest.newProvincia();
		List<Provincia> provincias = new ArrayList<Provincia>();
		provincias.add(provincia1);
		provincias.add(provincia2);
		when(provinciaService.findAll()).thenReturn(provincias);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Ciudad> list = new ArrayList<Ciudad>();
		when(ciudadService.findAll()).thenReturn(list);
		
		// When
		String viewName = ciudadController.list(model);
		
		// Then
		assertEquals("ciudad/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = ciudadController.formForCreate(model);
		
		// Then
		assertEquals("ciudad/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Ciudad)modelMap.get("ciudad")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/ciudad/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		Long id = ciudad.getId();
		when(ciudadService.findById(id)).thenReturn(ciudad);
		
		// When
		String viewName = ciudadController.formForUpdate(model, id);
		
		// Then
		assertEquals("ciudad/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudad, (Ciudad) modelMap.get("ciudad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ciudad/update", modelMap.get("saveAction"));
		
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Ciudad ciudadCreated = new Ciudad();
		when(ciudadService.create(ciudad)).thenReturn(ciudadCreated); 
		
		// When
		String viewName = ciudadController.create(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/ciudad/form/"+ciudad.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudadCreated, (Ciudad) modelMap.get("ciudad"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = ciudadController.create(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ciudad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudad, (Ciudad) modelMap.get("ciudad"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/ciudad/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
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

		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		
		Exception exception = new RuntimeException("test exception");
		when(ciudadService.create(ciudad)).thenThrow(exception);
		
		// When
		String viewName = ciudadController.create(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ciudad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudad, (Ciudad) modelMap.get("ciudad"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/ciudad/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "ciudad.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		Long id = ciudad.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Ciudad ciudadSaved = new Ciudad();
		ciudadSaved.setId(id);
		when(ciudadService.update(ciudad)).thenReturn(ciudadSaved); 
		
		// When
		String viewName = ciudadController.update(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/ciudad/form/"+ciudad.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudadSaved, (Ciudad) modelMap.get("ciudad"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = ciudadController.update(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ciudad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudad, (Ciudad) modelMap.get("ciudad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ciudad/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
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

		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		
		Exception exception = new RuntimeException("test exception");
		when(ciudadService.update(ciudad)).thenThrow(exception);
		
		// When
		String viewName = ciudadController.update(ciudad, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("ciudad/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(ciudad, (Ciudad) modelMap.get("ciudad"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/ciudad/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "ciudad.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<ProvinciaListItem> provinciaListItems = (List<ProvinciaListItem>) modelMap.get("listOfProvinciaItems");
		assertEquals(2, provinciaListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		Long id = ciudad.getId();
		
		// When
		String viewName = ciudadController.delete(redirectAttributes, id);
		
		// Then
		verify(ciudadService).delete(id);
		assertEquals("redirect:/ciudad", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		Long id = ciudad.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(ciudadService).delete(id);
		
		// When
		String viewName = ciudadController.delete(redirectAttributes, id);
		
		// Then
		verify(ciudadService).delete(id);
		assertEquals("redirect:/ciudad", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "ciudad.error.delete", exception);
	}
	
	
}
