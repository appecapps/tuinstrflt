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
import com.tuin.bean.Documento;
import com.tuin.bean.Estado;
import com.tuin.bean.Tipodocumento;
import com.tuin.test.DocumentoFactoryForTest;
import com.tuin.test.EstadoFactoryForTest;
import com.tuin.test.TipodocumentoFactoryForTest;

//--- Services 
import com.tuin.business.service.DocumentoService;
import com.tuin.business.service.EstadoService;
import com.tuin.business.service.TipodocumentoService;

//--- List Items 
import com.tuin.web.listitem.EstadoListItem;
import com.tuin.web.listitem.TipodocumentoListItem;

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
public class DocumentoControllerTest {
	
	@InjectMocks
	private DocumentoController documentoController;
	@Mock
	private DocumentoService documentoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private EstadoService estadoService; // Injected by Spring
	@Mock
	private TipodocumentoService tipodocumentoService; // Injected by Spring

	private DocumentoFactoryForTest documentoFactoryForTest = new DocumentoFactoryForTest();
	private EstadoFactoryForTest estadoFactoryForTest = new EstadoFactoryForTest();
	private TipodocumentoFactoryForTest tipodocumentoFactoryForTest = new TipodocumentoFactoryForTest();

	List<Estado> estados = new ArrayList<Estado>();
	List<Tipodocumento> tipodocumentos = new ArrayList<Tipodocumento>();

	private void givenPopulateModel() {
		Estado estado1 = estadoFactoryForTest.newEstado();
		Estado estado2 = estadoFactoryForTest.newEstado();
		List<Estado> estados = new ArrayList<Estado>();
		estados.add(estado1);
		estados.add(estado2);
		when(estadoService.findAll()).thenReturn(estados);

		Tipodocumento tipodocumento1 = tipodocumentoFactoryForTest.newTipodocumento();
		Tipodocumento tipodocumento2 = tipodocumentoFactoryForTest.newTipodocumento();
		List<Tipodocumento> tipodocumentos = new ArrayList<Tipodocumento>();
		tipodocumentos.add(tipodocumento1);
		tipodocumentos.add(tipodocumento2);
		when(tipodocumentoService.findAll()).thenReturn(tipodocumentos);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Documento> list = new ArrayList<Documento>();
		when(documentoService.findAll()).thenReturn(list);
		
		// When
		String viewName = documentoController.list(model);
		
		// Then
		assertEquals("documento/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = documentoController.formForCreate(model);
		
		// Then
		assertEquals("documento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Documento)modelMap.get("documento")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/documento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Documento documento = documentoFactoryForTest.newDocumento();
		Long id = documento.getId();
		when(documentoService.findById(id)).thenReturn(documento);
		
		// When
		String viewName = documentoController.formForUpdate(model, id);
		
		// Then
		assertEquals("documento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documento, (Documento) modelMap.get("documento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/documento/update", modelMap.get("saveAction"));
		
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Documento documento = documentoFactoryForTest.newDocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Documento documentoCreated = new Documento();
		when(documentoService.create(documento)).thenReturn(documentoCreated); 
		
		// When
		String viewName = documentoController.create(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/documento/form/"+documento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documentoCreated, (Documento) modelMap.get("documento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Documento documento = documentoFactoryForTest.newDocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = documentoController.create(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("documento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documento, (Documento) modelMap.get("documento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/documento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
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

		Documento documento = documentoFactoryForTest.newDocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(documentoService.create(documento)).thenThrow(exception);
		
		// When
		String viewName = documentoController.create(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("documento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documento, (Documento) modelMap.get("documento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/documento/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "documento.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Documento documento = documentoFactoryForTest.newDocumento();
		Long id = documento.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Documento documentoSaved = new Documento();
		documentoSaved.setId(id);
		when(documentoService.update(documento)).thenReturn(documentoSaved); 
		
		// When
		String viewName = documentoController.update(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/documento/form/"+documento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documentoSaved, (Documento) modelMap.get("documento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Documento documento = documentoFactoryForTest.newDocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = documentoController.update(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("documento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documento, (Documento) modelMap.get("documento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/documento/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
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

		Documento documento = documentoFactoryForTest.newDocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(documentoService.update(documento)).thenThrow(exception);
		
		// When
		String viewName = documentoController.update(documento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("documento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(documento, (Documento) modelMap.get("documento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/documento/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "documento.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Documento documento = documentoFactoryForTest.newDocumento();
		Long id = documento.getId();
		
		// When
		String viewName = documentoController.delete(redirectAttributes, id);
		
		// Then
		verify(documentoService).delete(id);
		assertEquals("redirect:/documento", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Documento documento = documentoFactoryForTest.newDocumento();
		Long id = documento.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(documentoService).delete(id);
		
		// When
		String viewName = documentoController.delete(redirectAttributes, id);
		
		// Then
		verify(documentoService).delete(id);
		assertEquals("redirect:/documento", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "documento.error.delete", exception);
	}
	
	
}
