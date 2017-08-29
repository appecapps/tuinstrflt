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
import com.tuin.bean.Facturaelectronica;
import com.tuin.bean.Estado;
import com.tuin.bean.Documento;
import com.tuin.test.FacturaelectronicaFactoryForTest;
import com.tuin.test.EstadoFactoryForTest;
import com.tuin.test.DocumentoFactoryForTest;

//--- Services 
import com.tuin.business.service.FacturaelectronicaService;
import com.tuin.business.service.EstadoService;
import com.tuin.business.service.DocumentoService;

//--- List Items 
import com.tuin.web.listitem.EstadoListItem;
import com.tuin.web.listitem.DocumentoListItem;

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
public class FacturaelectronicaControllerTest {
	
	@InjectMocks
	private FacturaelectronicaController facturaelectronicaController;
	@Mock
	private FacturaelectronicaService facturaelectronicaService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private EstadoService estadoService; // Injected by Spring
	@Mock
	private DocumentoService documentoService; // Injected by Spring

	private FacturaelectronicaFactoryForTest facturaelectronicaFactoryForTest = new FacturaelectronicaFactoryForTest();
	private EstadoFactoryForTest estadoFactoryForTest = new EstadoFactoryForTest();
	private DocumentoFactoryForTest documentoFactoryForTest = new DocumentoFactoryForTest();

	List<Estado> estados = new ArrayList<Estado>();
	List<Documento> documentos = new ArrayList<Documento>();

	private void givenPopulateModel() {
		Estado estado1 = estadoFactoryForTest.newEstado();
		Estado estado2 = estadoFactoryForTest.newEstado();
		List<Estado> estados = new ArrayList<Estado>();
		estados.add(estado1);
		estados.add(estado2);
		when(estadoService.findAll()).thenReturn(estados);

		Documento documento1 = documentoFactoryForTest.newDocumento();
		Documento documento2 = documentoFactoryForTest.newDocumento();
		List<Documento> documentos = new ArrayList<Documento>();
		documentos.add(documento1);
		documentos.add(documento2);
		when(documentoService.findAll()).thenReturn(documentos);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Facturaelectronica> list = new ArrayList<Facturaelectronica>();
		when(facturaelectronicaService.findAll()).thenReturn(list);
		
		// When
		String viewName = facturaelectronicaController.list(model);
		
		// Then
		assertEquals("facturaelectronica/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = facturaelectronicaController.formForCreate(model);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Facturaelectronica)modelMap.get("facturaelectronica")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/facturaelectronica/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		Long id = facturaelectronica.getId();
		when(facturaelectronicaService.findById(id)).thenReturn(facturaelectronica);
		
		// When
		String viewName = facturaelectronicaController.formForUpdate(model, id);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronica, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/facturaelectronica/update", modelMap.get("saveAction"));
		
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Facturaelectronica facturaelectronicaCreated = new Facturaelectronica();
		when(facturaelectronicaService.create(facturaelectronica)).thenReturn(facturaelectronicaCreated); 
		
		// When
		String viewName = facturaelectronicaController.create(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/facturaelectronica/form/"+facturaelectronica.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronicaCreated, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = facturaelectronicaController.create(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronica, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/facturaelectronica/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
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

		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		
		Exception exception = new RuntimeException("test exception");
		when(facturaelectronicaService.create(facturaelectronica)).thenThrow(exception);
		
		// When
		String viewName = facturaelectronicaController.create(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronica, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/facturaelectronica/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "facturaelectronica.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		Long id = facturaelectronica.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Facturaelectronica facturaelectronicaSaved = new Facturaelectronica();
		facturaelectronicaSaved.setId(id);
		when(facturaelectronicaService.update(facturaelectronica)).thenReturn(facturaelectronicaSaved); 
		
		// When
		String viewName = facturaelectronicaController.update(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/facturaelectronica/form/"+facturaelectronica.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronicaSaved, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = facturaelectronicaController.update(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronica, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/facturaelectronica/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
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

		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		
		Exception exception = new RuntimeException("test exception");
		when(facturaelectronicaService.update(facturaelectronica)).thenThrow(exception);
		
		// When
		String viewName = facturaelectronicaController.update(facturaelectronica, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("facturaelectronica/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(facturaelectronica, (Facturaelectronica) modelMap.get("facturaelectronica"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/facturaelectronica/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "facturaelectronica.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		Long id = facturaelectronica.getId();
		
		// When
		String viewName = facturaelectronicaController.delete(redirectAttributes, id);
		
		// Then
		verify(facturaelectronicaService).delete(id);
		assertEquals("redirect:/facturaelectronica", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		Long id = facturaelectronica.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(facturaelectronicaService).delete(id);
		
		// When
		String viewName = facturaelectronicaController.delete(redirectAttributes, id);
		
		// Then
		verify(facturaelectronicaService).delete(id);
		assertEquals("redirect:/facturaelectronica", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "facturaelectronica.error.delete", exception);
	}
	
	
}
