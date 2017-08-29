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
import com.tuin.bean.Componentefinanciero;
import com.tuin.bean.Tipodocumento;
import com.tuin.bean.Tipocomponentefinanciero;
import com.tuin.test.ComponentefinancieroFactoryForTest;
import com.tuin.test.TipodocumentoFactoryForTest;
import com.tuin.test.TipocomponentefinancieroFactoryForTest;

//--- Services 
import com.tuin.business.service.ComponentefinancieroService;
import com.tuin.business.service.TipodocumentoService;
import com.tuin.business.service.TipocomponentefinancieroService;

//--- List Items 
import com.tuin.web.listitem.TipodocumentoListItem;
import com.tuin.web.listitem.TipocomponentefinancieroListItem;

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
public class ComponentefinancieroControllerTest {
	
	@InjectMocks
	private ComponentefinancieroController componentefinancieroController;
	@Mock
	private ComponentefinancieroService componentefinancieroService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TipodocumentoService tipodocumentoService; // Injected by Spring
	@Mock
	private TipocomponentefinancieroService tipocomponentefinancieroService; // Injected by Spring

	private ComponentefinancieroFactoryForTest componentefinancieroFactoryForTest = new ComponentefinancieroFactoryForTest();
	private TipodocumentoFactoryForTest tipodocumentoFactoryForTest = new TipodocumentoFactoryForTest();
	private TipocomponentefinancieroFactoryForTest tipocomponentefinancieroFactoryForTest = new TipocomponentefinancieroFactoryForTest();

	List<Tipodocumento> tipodocumentos = new ArrayList<Tipodocumento>();
	List<Tipocomponentefinanciero> tipocomponentefinancieros = new ArrayList<Tipocomponentefinanciero>();

	private void givenPopulateModel() {
		Tipodocumento tipodocumento1 = tipodocumentoFactoryForTest.newTipodocumento();
		Tipodocumento tipodocumento2 = tipodocumentoFactoryForTest.newTipodocumento();
		List<Tipodocumento> tipodocumentos = new ArrayList<Tipodocumento>();
		tipodocumentos.add(tipodocumento1);
		tipodocumentos.add(tipodocumento2);
		when(tipodocumentoService.findAll()).thenReturn(tipodocumentos);

		Tipocomponentefinanciero tipocomponentefinanciero1 = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		Tipocomponentefinanciero tipocomponentefinanciero2 = tipocomponentefinancieroFactoryForTest.newTipocomponentefinanciero();
		List<Tipocomponentefinanciero> tipocomponentefinancieros = new ArrayList<Tipocomponentefinanciero>();
		tipocomponentefinancieros.add(tipocomponentefinanciero1);
		tipocomponentefinancieros.add(tipocomponentefinanciero2);
		when(tipocomponentefinancieroService.findAll()).thenReturn(tipocomponentefinancieros);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Componentefinanciero> list = new ArrayList<Componentefinanciero>();
		when(componentefinancieroService.findAll()).thenReturn(list);
		
		// When
		String viewName = componentefinancieroController.list(model);
		
		// Then
		assertEquals("componentefinanciero/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = componentefinancieroController.formForCreate(model);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Componentefinanciero)modelMap.get("componentefinanciero")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentefinanciero/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		Long id = componentefinanciero.getId();
		when(componentefinancieroService.findById(id)).thenReturn(componentefinanciero);
		
		// When
		String viewName = componentefinancieroController.formForUpdate(model, id);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinanciero, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentefinanciero/update", modelMap.get("saveAction"));
		
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Componentefinanciero componentefinancieroCreated = new Componentefinanciero();
		when(componentefinancieroService.create(componentefinanciero)).thenReturn(componentefinancieroCreated); 
		
		// When
		String viewName = componentefinancieroController.create(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/componentefinanciero/form/"+componentefinanciero.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinancieroCreated, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = componentefinancieroController.create(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinanciero, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentefinanciero/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
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

		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		
		Exception exception = new RuntimeException("test exception");
		when(componentefinancieroService.create(componentefinanciero)).thenThrow(exception);
		
		// When
		String viewName = componentefinancieroController.create(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinanciero, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentefinanciero/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "componentefinanciero.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		Long id = componentefinanciero.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Componentefinanciero componentefinancieroSaved = new Componentefinanciero();
		componentefinancieroSaved.setId(id);
		when(componentefinancieroService.update(componentefinanciero)).thenReturn(componentefinancieroSaved); 
		
		// When
		String viewName = componentefinancieroController.update(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/componentefinanciero/form/"+componentefinanciero.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinancieroSaved, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = componentefinancieroController.update(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinanciero, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentefinanciero/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
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

		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		
		Exception exception = new RuntimeException("test exception");
		when(componentefinancieroService.update(componentefinanciero)).thenThrow(exception);
		
		// When
		String viewName = componentefinancieroController.update(componentefinanciero, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentefinanciero/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentefinanciero, (Componentefinanciero) modelMap.get("componentefinanciero"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentefinanciero/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "componentefinanciero.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipocomponentefinancieroListItem> tipocomponentefinancieroListItems = (List<TipocomponentefinancieroListItem>) modelMap.get("listOfTipocomponentefinancieroItems");
		assertEquals(2, tipocomponentefinancieroListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		Long id = componentefinanciero.getId();
		
		// When
		String viewName = componentefinancieroController.delete(redirectAttributes, id);
		
		// Then
		verify(componentefinancieroService).delete(id);
		assertEquals("redirect:/componentefinanciero", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Componentefinanciero componentefinanciero = componentefinancieroFactoryForTest.newComponentefinanciero();
		Long id = componentefinanciero.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(componentefinancieroService).delete(id);
		
		// When
		String viewName = componentefinancieroController.delete(redirectAttributes, id);
		
		// Then
		verify(componentefinancieroService).delete(id);
		assertEquals("redirect:/componentefinanciero", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "componentefinanciero.error.delete", exception);
	}
	
	
}
