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
import com.tuin.bean.Tipodocumento;
import com.tuin.bean.Tipocartera;
import com.tuin.test.TipodocumentoFactoryForTest;
import com.tuin.test.TipocarteraFactoryForTest;

//--- Services 
import com.tuin.business.service.TipodocumentoService;
import com.tuin.business.service.TipocarteraService;

//--- List Items 
import com.tuin.web.listitem.TipocarteraListItem;

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
public class TipodocumentoControllerTest {
	
	@InjectMocks
	private TipodocumentoController tipodocumentoController;
	@Mock
	private TipodocumentoService tipodocumentoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TipocarteraService tipocarteraService; // Injected by Spring

	private TipodocumentoFactoryForTest tipodocumentoFactoryForTest = new TipodocumentoFactoryForTest();
	private TipocarteraFactoryForTest tipocarteraFactoryForTest = new TipocarteraFactoryForTest();

	List<Tipocartera> tipocarteras = new ArrayList<Tipocartera>();

	private void givenPopulateModel() {
		Tipocartera tipocartera1 = tipocarteraFactoryForTest.newTipocartera();
		Tipocartera tipocartera2 = tipocarteraFactoryForTest.newTipocartera();
		List<Tipocartera> tipocarteras = new ArrayList<Tipocartera>();
		tipocarteras.add(tipocartera1);
		tipocarteras.add(tipocartera2);
		when(tipocarteraService.findAll()).thenReturn(tipocarteras);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Tipodocumento> list = new ArrayList<Tipodocumento>();
		when(tipodocumentoService.findAll()).thenReturn(list);
		
		// When
		String viewName = tipodocumentoController.list(model);
		
		// Then
		assertEquals("tipodocumento/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tipodocumentoController.formForCreate(model);
		
		// Then
		assertEquals("tipodocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Tipodocumento)modelMap.get("tipodocumento")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipodocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		Long id = tipodocumento.getId();
		when(tipodocumentoService.findById(id)).thenReturn(tipodocumento);
		
		// When
		String viewName = tipodocumentoController.formForUpdate(model, id);
		
		// Then
		assertEquals("tipodocumento/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumento, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipodocumento/update", modelMap.get("saveAction"));
		
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipodocumento tipodocumentoCreated = new Tipodocumento();
		when(tipodocumentoService.create(tipodocumento)).thenReturn(tipodocumentoCreated); 
		
		// When
		String viewName = tipodocumentoController.create(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipodocumento/form/"+tipodocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumentoCreated, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipodocumentoController.create(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumento, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipodocumento/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
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

		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(tipodocumentoService.create(tipodocumento)).thenThrow(exception);
		
		// When
		String viewName = tipodocumentoController.create(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumento, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tipodocumento/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipodocumento.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		Long id = tipodocumento.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Tipodocumento tipodocumentoSaved = new Tipodocumento();
		tipodocumentoSaved.setId(id);
		when(tipodocumentoService.update(tipodocumento)).thenReturn(tipodocumentoSaved); 
		
		// When
		String viewName = tipodocumentoController.update(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tipodocumento/form/"+tipodocumento.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumentoSaved, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tipodocumentoController.update(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumento, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipodocumento/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
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

		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		
		Exception exception = new RuntimeException("test exception");
		when(tipodocumentoService.update(tipodocumento)).thenThrow(exception);
		
		// When
		String viewName = tipodocumentoController.update(tipodocumento, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tipodocumento/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tipodocumento, (Tipodocumento) modelMap.get("tipodocumento"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tipodocumento/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tipodocumento.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TipocarteraListItem> tipocarteraListItems = (List<TipocarteraListItem>) modelMap.get("listOfTipocarteraItems");
		assertEquals(2, tipocarteraListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		Long id = tipodocumento.getId();
		
		// When
		String viewName = tipodocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(tipodocumentoService).delete(id);
		assertEquals("redirect:/tipodocumento", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Tipodocumento tipodocumento = tipodocumentoFactoryForTest.newTipodocumento();
		Long id = tipodocumento.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tipodocumentoService).delete(id);
		
		// When
		String viewName = tipodocumentoController.delete(redirectAttributes, id);
		
		// Then
		verify(tipodocumentoService).delete(id);
		assertEquals("redirect:/tipodocumento", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tipodocumento.error.delete", exception);
	}
	
	
}
