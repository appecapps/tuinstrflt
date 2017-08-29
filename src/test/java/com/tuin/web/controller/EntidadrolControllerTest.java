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
import com.tuin.bean.Entidadrol;
import com.tuin.bean.Rol;
import com.tuin.bean.Entidad;
import com.tuin.test.EntidadrolFactoryForTest;
import com.tuin.test.RolFactoryForTest;
import com.tuin.test.EntidadFactoryForTest;

//--- Services 
import com.tuin.business.service.EntidadrolService;
import com.tuin.business.service.RolService;
import com.tuin.business.service.EntidadService;

//--- List Items 
import com.tuin.web.listitem.RolListItem;
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
public class EntidadrolControllerTest {
	
	@InjectMocks
	private EntidadrolController entidadrolController;
	@Mock
	private EntidadrolService entidadrolService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private RolService rolService; // Injected by Spring
	@Mock
	private EntidadService entidadService; // Injected by Spring

	private EntidadrolFactoryForTest entidadrolFactoryForTest = new EntidadrolFactoryForTest();
	private RolFactoryForTest rolFactoryForTest = new RolFactoryForTest();
	private EntidadFactoryForTest entidadFactoryForTest = new EntidadFactoryForTest();

	List<Rol> rols = new ArrayList<Rol>();
	List<Entidad> entidads = new ArrayList<Entidad>();

	private void givenPopulateModel() {
		Rol rol1 = rolFactoryForTest.newRol();
		Rol rol2 = rolFactoryForTest.newRol();
		List<Rol> rols = new ArrayList<Rol>();
		rols.add(rol1);
		rols.add(rol2);
		when(rolService.findAll()).thenReturn(rols);

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
		
		List<Entidadrol> list = new ArrayList<Entidadrol>();
		when(entidadrolService.findAll()).thenReturn(list);
		
		// When
		String viewName = entidadrolController.list(model);
		
		// Then
		assertEquals("entidadrol/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = entidadrolController.formForCreate(model);
		
		// Then
		assertEquals("entidadrol/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Entidadrol)modelMap.get("entidadrol")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidadrol/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		Long id = entidadrol.getId();
		when(entidadrolService.findById(id)).thenReturn(entidadrol);
		
		// When
		String viewName = entidadrolController.formForUpdate(model, id);
		
		// Then
		assertEquals("entidadrol/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrol, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidadrol/update", modelMap.get("saveAction"));
		
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Entidadrol entidadrolCreated = new Entidadrol();
		when(entidadrolService.create(entidadrol)).thenReturn(entidadrolCreated); 
		
		// When
		String viewName = entidadrolController.create(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/entidadrol/form/"+entidadrol.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrolCreated, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = entidadrolController.create(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidadrol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrol, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidadrol/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
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

		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		
		Exception exception = new RuntimeException("test exception");
		when(entidadrolService.create(entidadrol)).thenThrow(exception);
		
		// When
		String viewName = entidadrolController.create(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidadrol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrol, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/entidadrol/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "entidadrol.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		Long id = entidadrol.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Entidadrol entidadrolSaved = new Entidadrol();
		entidadrolSaved.setId(id);
		when(entidadrolService.update(entidadrol)).thenReturn(entidadrolSaved); 
		
		// When
		String viewName = entidadrolController.update(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/entidadrol/form/"+entidadrol.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrolSaved, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = entidadrolController.update(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidadrol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrol, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidadrol/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
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

		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		
		Exception exception = new RuntimeException("test exception");
		when(entidadrolService.update(entidadrol)).thenThrow(exception);
		
		// When
		String viewName = entidadrolController.update(entidadrol, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("entidadrol/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(entidadrol, (Entidadrol) modelMap.get("entidadrol"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/entidadrol/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "entidadrol.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EntidadListItem> entidadListItems = (List<EntidadListItem>) modelMap.get("listOfEntidadItems");
		assertEquals(2, entidadListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		Long id = entidadrol.getId();
		
		// When
		String viewName = entidadrolController.delete(redirectAttributes, id);
		
		// Then
		verify(entidadrolService).delete(id);
		assertEquals("redirect:/entidadrol", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Entidadrol entidadrol = entidadrolFactoryForTest.newEntidadrol();
		Long id = entidadrol.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(entidadrolService).delete(id);
		
		// When
		String viewName = entidadrolController.delete(redirectAttributes, id);
		
		// Then
		verify(entidadrolService).delete(id);
		assertEquals("redirect:/entidadrol", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "entidadrol.error.delete", exception);
	}
	
	
}
