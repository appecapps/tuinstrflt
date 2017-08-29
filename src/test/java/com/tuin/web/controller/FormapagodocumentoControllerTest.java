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
import com.tuin.bean.Formapagodocumento;
import com.tuin.bean.Documento;
import com.tuin.bean.Formapago;
import com.tuin.test.FormapagodocumentoFactoryForTest;
import com.tuin.test.DocumentoFactoryForTest;
import com.tuin.test.FormapagoFactoryForTest;

//--- Services 
import com.tuin.business.service.FormapagodocumentoService;
import com.tuin.business.service.DocumentoService;
import com.tuin.business.service.FormapagoService;

//--- List Items 
import com.tuin.web.listitem.DocumentoListItem;
import com.tuin.web.listitem.FormapagoListItem;

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
public class FormapagodocumentoControllerTest {
	
	@InjectMocks
	private FormapagodocumentoController formapagodocumentoController;
	@Mock
	private FormapagodocumentoService formapagodocumentoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private DocumentoService documentoService; // Injected by Spring
	@Mock
	private FormapagoService formapagoService; // Injected by Spring

	private FormapagodocumentoFactoryForTest formapagodocumentoFactoryForTest = new FormapagodocumentoFactoryForTest();
	private DocumentoFactoryForTest documentoFactoryForTest = new DocumentoFactoryForTest();
	private FormapagoFactoryForTest formapagoFactoryForTest = new FormapagoFactoryForTest();

	List<Documento> documentos = new ArrayList<Documento>();
	List<Formapago> formapagos = new ArrayList<Formapago>();

	private void givenPopulateModel() {
		Documento documento1 = documentoFactoryForTest.newDocumento();
		Documento documento2 = documentoFactoryForTest.newDocumento();
		List<Documento> documentos = new ArrayList<Documento>();
		documentos.add(documento1);
		documentos.add(documento2);
		when(documentoService.findAll()).thenReturn(documentos);

		Formapago formapago1 = formapagoFactoryForTest.newFormapago();
		Formapago formapago2 = formapagoFactoryForTest.newFormapago();
		List<Formapago> formapagos = new ArrayList<Formapago>();
		formapagos.add(formapago1);
		formapagos.add(formapago2);
		when(formapagoService.findAll()).thenReturn(formapagos);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Formapagodocumento> list = new ArrayList<Formapagodocumento>();
		when(formapagodocumentoService.findAll()).thenReturn(list);
		
		// When
		String viewName = formapagodocumentoController.list(model);
		
		// Then
		assertEquals("formapagodocumento/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = formapagodocumentoController.formForCreate(model);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Formapagodocumento)modelMap.get("formapagodocumento")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapagodocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		Long id = formapagodocumento.getId();
		when(formapagodocumentoService.findById(id)).thenReturn(formapagodocumento);
		
		// When
		String viewName = formapagodocumentoController.formForUpdate(model, id);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumento, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapagodocumento/update", modelMap.get("saveAction"));
		
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Formapagodocumento formapagodocumentoCreated = new Formapagodocumento();
		when(formapagodocumentoService.create(formapagodocumento)).thenReturn(formapagodocumentoCreated); 
		
		// When
		String viewName = formapagodocumentoController.create(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/formapagodocumento/form/"+formapagodocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumentoCreated, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = formapagodocumentoController.create(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumento, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapagodocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
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

		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(formapagodocumentoService.create(formapagodocumento)).thenThrow(exception);
		
		// When
		String viewName = formapagodocumentoController.create(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumento, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapagodocumento/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "formapagodocumento.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		Long id = formapagodocumento.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Formapagodocumento formapagodocumentoSaved = new Formapagodocumento();
		formapagodocumentoSaved.setId(id);
		when(formapagodocumentoService.update(formapagodocumento)).thenReturn(formapagodocumentoSaved); 
		
		// When
		String viewName = formapagodocumentoController.update(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/formapagodocumento/form/"+formapagodocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumentoSaved, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = formapagodocumentoController.update(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumento, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapagodocumento/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
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

		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(formapagodocumentoService.update(formapagodocumento)).thenThrow(exception);
		
		// When
		String viewName = formapagodocumentoController.update(formapagodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapagodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagodocumento, (Formapagodocumento) modelMap.get("formapagodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapagodocumento/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "formapagodocumento.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<DocumentoListItem> documentoListItems = (List<DocumentoListItem>) modelMap.get("listOfDocumentoItems");
		assertEquals(2, documentoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<FormapagoListItem> formapagoListItems = (List<FormapagoListItem>) modelMap.get("listOfFormapagoItems");
		assertEquals(2, formapagoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		Long id = formapagodocumento.getId();
		
		// When
		String viewName = formapagodocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(formapagodocumentoService).delete(id);
		assertEquals("redirect:/formapagodocumento", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Formapagodocumento formapagodocumento = formapagodocumentoFactoryForTest.newFormapagodocumento();
		Long id = formapagodocumento.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(formapagodocumentoService).delete(id);
		
		// When
		String viewName = formapagodocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(formapagodocumentoService).delete(id);
		assertEquals("redirect:/formapagodocumento", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "formapagodocumento.error.delete", exception);
	}
	
	
}
