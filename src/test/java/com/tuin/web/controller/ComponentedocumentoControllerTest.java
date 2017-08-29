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
import com.tuin.bean.Componentedocumento;
import com.tuin.bean.Componentefinanciero;
import com.tuin.bean.Documento;
import com.tuin.test.ComponentedocumentoFactoryForTest;
import com.tuin.test.ComponentefinancieroFactoryForTest;
import com.tuin.test.DocumentoFactoryForTest;

//--- Services 
import com.tuin.business.service.ComponentedocumentoService;
import com.tuin.business.service.ComponentefinancieroService;
import com.tuin.business.service.DocumentoService;

//--- List Items 
import com.tuin.web.listitem.ComponentefinancieroListItem;
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
public class ComponentedocumentoControllerTest {
	
	@InjectMocks
	private ComponentedocumentoController componentedocumentoController;
	@Mock
	private ComponentedocumentoService componentedocumentoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ComponentefinancieroService componentefinancieroService; // Injected by Spring
	@Mock
	private DocumentoService documentoService; // Injected by Spring

	private ComponentedocumentoFactoryForTest componentedocumentoFactoryForTest = new ComponentedocumentoFactoryForTest();
	private ComponentefinancieroFactoryForTest componentefinancieroFactoryForTest = new ComponentefinancieroFactoryForTest();
	private DocumentoFactoryForTest documentoFactoryForTest = new DocumentoFactoryForTest();

	List<Componentefinanciero> componentefinancieros = new ArrayList<Componentefinanciero>();
	List<Documento> documentos = new ArrayList<Documento>();

	private void givenPopulateModel() {
		Componentefinanciero componentefinanciero1 = componentefinancieroFactoryForTest.newComponentefinanciero();
		Componentefinanciero componentefinanciero2 = componentefinancieroFactoryForTest.newComponentefinanciero();
		List<Componentefinanciero> componentefinancieros = new ArrayList<Componentefinanciero>();
		componentefinancieros.add(componentefinanciero1);
		componentefinancieros.add(componentefinanciero2);
		when(componentefinancieroService.findAll()).thenReturn(componentefinancieros);

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
		
		List<Componentedocumento> list = new ArrayList<Componentedocumento>();
		when(componentedocumentoService.findAll()).thenReturn(list);
		
		// When
		String viewName = componentedocumentoController.list(model);
		
		// Then
		assertEquals("componentedocumento/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = componentedocumentoController.formForCreate(model);
		
		// Then
		assertEquals("componentedocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Componentedocumento)modelMap.get("componentedocumento")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentedocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		Long id = componentedocumento.getId();
		when(componentedocumentoService.findById(id)).thenReturn(componentedocumento);
		
		// When
		String viewName = componentedocumentoController.formForUpdate(model, id);
		
		// Then
		assertEquals("componentedocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumento, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentedocumento/update", modelMap.get("saveAction"));
		
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Componentedocumento componentedocumentoCreated = new Componentedocumento();
		when(componentedocumentoService.create(componentedocumento)).thenReturn(componentedocumentoCreated); 
		
		// When
		String viewName = componentedocumentoController.create(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/componentedocumento/form/"+componentedocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumentoCreated, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = componentedocumentoController.create(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentedocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumento, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentedocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
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

		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(componentedocumentoService.create(componentedocumento)).thenThrow(exception);
		
		// When
		String viewName = componentedocumentoController.create(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentedocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumento, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/componentedocumento/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "componentedocumento.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		Long id = componentedocumento.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Componentedocumento componentedocumentoSaved = new Componentedocumento();
		componentedocumentoSaved.setId(id);
		when(componentedocumentoService.update(componentedocumento)).thenReturn(componentedocumentoSaved); 
		
		// When
		String viewName = componentedocumentoController.update(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/componentedocumento/form/"+componentedocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumentoSaved, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = componentedocumentoController.update(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentedocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumento, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentedocumento/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
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

		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(componentedocumentoService.update(componentedocumento)).thenThrow(exception);
		
		// When
		String viewName = componentedocumentoController.update(componentedocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("componentedocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(componentedocumento, (Componentedocumento) modelMap.get("componentedocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/componentedocumento/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "componentedocumento.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ComponentefinancieroListItem> componentefinancieroListItems = (List<ComponentefinancieroListItem>) modelMap.get("listOfComponentefinancieroItems");
		assertEquals(2, componentefinancieroListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		Long id = componentedocumento.getId();
		
		// When
		String viewName = componentedocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(componentedocumentoService).delete(id);
		assertEquals("redirect:/componentedocumento", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Componentedocumento componentedocumento = componentedocumentoFactoryForTest.newComponentedocumento();
		Long id = componentedocumento.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(componentedocumentoService).delete(id);
		
		// When
		String viewName = componentedocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(componentedocumentoService).delete(id);
		assertEquals("redirect:/componentedocumento", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "componentedocumento.error.delete", exception);
	}
	
	
}
