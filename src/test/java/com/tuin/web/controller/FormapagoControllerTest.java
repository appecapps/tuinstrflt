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
import com.tuin.bean.Formapago;
import com.tuin.bean.Tipodocumento;
import com.tuin.test.FormapagoFactoryForTest;
import com.tuin.test.TipodocumentoFactoryForTest;

//--- Services 
import com.tuin.business.service.FormapagoService;
import com.tuin.business.service.TipodocumentoService;

//--- List Items 
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
public class FormapagoControllerTest {
	
	@InjectMocks
	private FormapagoController formapagoController;
	@Mock
	private FormapagoService formapagoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TipodocumentoService tipodocumentoService; // Injected by Spring

	private FormapagoFactoryForTest formapagoFactoryForTest = new FormapagoFactoryForTest();
	private TipodocumentoFactoryForTest tipodocumentoFactoryForTest = new TipodocumentoFactoryForTest();

	List<Tipodocumento> tipodocumentos = new ArrayList<Tipodocumento>();

	private void givenPopulateModel() {
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
		
		List<Formapago> list = new ArrayList<Formapago>();
		when(formapagoService.findAll()).thenReturn(list);
		
		// When
		String viewName = formapagoController.list(model);
		
		// Then
		assertEquals("formapago/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = formapagoController.formForCreate(model);
		
		// Then
		assertEquals("formapago/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Formapago)modelMap.get("formapago")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapago/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		Long id = formapago.getId();
		when(formapagoService.findById(id)).thenReturn(formapago);
		
		// When
		String viewName = formapagoController.formForUpdate(model, id);
		
		// Then
		assertEquals("formapago/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapago, (Formapago) modelMap.get("formapago"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapago/update", modelMap.get("saveAction"));
		
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Formapago formapagoCreated = new Formapago();
		when(formapagoService.create(formapago)).thenReturn(formapagoCreated); 
		
		// When
		String viewName = formapagoController.create(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/formapago/form/"+formapago.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagoCreated, (Formapago) modelMap.get("formapago"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = formapagoController.create(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapago/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapago, (Formapago) modelMap.get("formapago"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapago/create", modelMap.get("saveAction"));
		
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

		Formapago formapago = formapagoFactoryForTest.newFormapago();
		
		Exception exception = new RuntimeException("test exception");
		when(formapagoService.create(formapago)).thenThrow(exception);
		
		// When
		String viewName = formapagoController.create(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapago/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapago, (Formapago) modelMap.get("formapago"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/formapago/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "formapago.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		Long id = formapago.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Formapago formapagoSaved = new Formapago();
		formapagoSaved.setId(id);
		when(formapagoService.update(formapago)).thenReturn(formapagoSaved); 
		
		// When
		String viewName = formapagoController.update(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/formapago/form/"+formapago.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapagoSaved, (Formapago) modelMap.get("formapago"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = formapagoController.update(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapago/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapago, (Formapago) modelMap.get("formapago"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapago/update", modelMap.get("saveAction"));
		
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

		Formapago formapago = formapagoFactoryForTest.newFormapago();
		
		Exception exception = new RuntimeException("test exception");
		when(formapagoService.update(formapago)).thenThrow(exception);
		
		// When
		String viewName = formapagoController.update(formapago, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("formapago/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(formapago, (Formapago) modelMap.get("formapago"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/formapago/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "formapago.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TipodocumentoListItem> tipodocumentoListItems = (List<TipodocumentoListItem>) modelMap.get("listOfTipodocumentoItems");
		assertEquals(2, tipodocumentoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		Long id = formapago.getId();
		
		// When
		String viewName = formapagoController.delete(redirectAttributes, id);
		
		// Then
		verify(formapagoService).delete(id);
		assertEquals("redirect:/formapago", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Formapago formapago = formapagoFactoryForTest.newFormapago();
		Long id = formapago.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(formapagoService).delete(id);
		
		// When
		String viewName = formapagoController.delete(redirectAttributes, id);
		
		// Then
		verify(formapagoService).delete(id);
		assertEquals("redirect:/formapago", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "formapago.error.delete", exception);
	}
	
	
}
