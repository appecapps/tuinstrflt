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
import com.tuin.bean.Chofer;
import com.tuin.bean.Entidad;
import com.tuin.test.ChoferFactoryForTest;
import com.tuin.test.EntidadFactoryForTest;

//--- Services 
import com.tuin.business.service.ChoferService;
import com.tuin.business.service.EntidadService;

//--- List Items 
import com.tuin.web.listitem.EntidadListItem;

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
public class ChoferControllerTest {
	
	@InjectMocks
	private ChoferController choferController;
	@Mock
	private ChoferService choferService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private EntidadService entidadService; // Injected by Spring

	private ChoferFactoryForTest choferFactoryForTest = new ChoferFactoryForTest();
	private EntidadFactoryForTest entidadFactoryForTest = new EntidadFactoryForTest();

	List<Entidad> entidads = new ArrayList<Entidad>();

	private void givenPopulateModel() {
		Entidad entidad1 = entidadFactoryForTest.newEntidad();
		Entidad entidad2 = entidadFactoryForTest.newEntidad();
		List<Entidad> entidads = new ArrayList<Entidad>();
		entidads.add(entidad1);
		entidads.add(entidad2);
		when(entidadService.findAll()).thenReturn(entidads);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Chofer> list = new ArrayList<Chofer>();
		when(choferService.findAll()).thenReturn(list);
		
		// When
		String viewName = choferController.list(model);
		
		// Then
		assertEquals("chofer/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = choferController.formForCreate(model);
		
		// Then
		assertEquals("chofer/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Chofer)modelMap.get("chofer")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofer/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofer chofer = choferFactoryForTest.newChofer();
		Long id = chofer.getId();
		when(choferService.findById(id)).thenReturn(chofer);
		
		// When
		String viewName = choferController.formForUpdate(model, id);
		
		// Then
		assertEquals("chofer/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofer, (Chofer) modelMap.get("chofer"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofer/update", modelMap.get("saveAction"));
		
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Chofer chofer = choferFactoryForTest.newChofer();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Chofer choferCreated = new Chofer();
		when(choferService.create(chofer)).thenReturn(choferCreated); 
		
		// When
		String viewName = choferController.create(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/chofer/form/"+chofer.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(choferCreated, (Chofer) modelMap.get("chofer"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofer chofer = choferFactoryForTest.newChofer();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = choferController.create(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofer/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofer, (Chofer) modelMap.get("chofer"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofer/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
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

		Chofer chofer = choferFactoryForTest.newChofer();
		
		Exception exception = new RuntimeException("test exception");
		when(choferService.create(chofer)).thenThrow(exception);
		
		// When
		String viewName = choferController.create(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofer/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofer, (Chofer) modelMap.get("chofer"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofer/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "chofer.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Chofer chofer = choferFactoryForTest.newChofer();
		Long id = chofer.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Chofer choferSaved = new Chofer();
		choferSaved.setId(id);
		when(choferService.update(chofer)).thenReturn(choferSaved); 
		
		// When
		String viewName = choferController.update(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/chofer/form/"+chofer.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(choferSaved, (Chofer) modelMap.get("chofer"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofer chofer = choferFactoryForTest.newChofer();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = choferController.update(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofer/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofer, (Chofer) modelMap.get("chofer"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofer/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
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

		Chofer chofer = choferFactoryForTest.newChofer();
		
		Exception exception = new RuntimeException("test exception");
		when(choferService.update(chofer)).thenThrow(exception);
		
		// When
		String viewName = choferController.update(chofer, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofer/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofer, (Chofer) modelMap.get("chofer"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofer/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "chofer.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Chofer chofer = choferFactoryForTest.newChofer();
		Long id = chofer.getId();
		
		// When
		String viewName = choferController.delete(redirectAttributes, id);
		
		// Then
		verify(choferService).delete(id);
		assertEquals("redirect:/chofer", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Chofer chofer = choferFactoryForTest.newChofer();
		Long id = chofer.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(choferService).delete(id);
		
		// When
		String viewName = choferController.delete(redirectAttributes, id);
		
		// Then
		verify(choferService).delete(id);
		assertEquals("redirect:/chofer", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "chofer.error.delete", exception);
	}
	
	
}
