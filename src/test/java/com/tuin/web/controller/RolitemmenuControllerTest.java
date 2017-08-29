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
import com.tuin.bean.Rolitemmenu;
import com.tuin.bean.Itemmenu;
import com.tuin.bean.Rol;
import com.tuin.test.RolitemmenuFactoryForTest;
import com.tuin.test.ItemmenuFactoryForTest;
import com.tuin.test.RolFactoryForTest;

//--- Services 
import com.tuin.business.service.RolitemmenuService;
import com.tuin.business.service.ItemmenuService;
import com.tuin.business.service.RolService;

//--- List Items 
import com.tuin.web.listitem.ItemmenuListItem;
import com.tuin.web.listitem.RolListItem;

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
public class RolitemmenuControllerTest {
	
	@InjectMocks
	private RolitemmenuController rolitemmenuController;
	@Mock
	private RolitemmenuService rolitemmenuService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ItemmenuService itemmenuService; // Injected by Spring
	@Mock
	private RolService rolService; // Injected by Spring

	private RolitemmenuFactoryForTest rolitemmenuFactoryForTest = new RolitemmenuFactoryForTest();
	private ItemmenuFactoryForTest itemmenuFactoryForTest = new ItemmenuFactoryForTest();
	private RolFactoryForTest rolFactoryForTest = new RolFactoryForTest();

	List<Itemmenu> itemmenus = new ArrayList<Itemmenu>();
	List<Rol> rols = new ArrayList<Rol>();

	private void givenPopulateModel() {
		Itemmenu itemmenu1 = itemmenuFactoryForTest.newItemmenu();
		Itemmenu itemmenu2 = itemmenuFactoryForTest.newItemmenu();
		List<Itemmenu> itemmenus = new ArrayList<Itemmenu>();
		itemmenus.add(itemmenu1);
		itemmenus.add(itemmenu2);
		when(itemmenuService.findAll()).thenReturn(itemmenus);

		Rol rol1 = rolFactoryForTest.newRol();
		Rol rol2 = rolFactoryForTest.newRol();
		List<Rol> rols = new ArrayList<Rol>();
		rols.add(rol1);
		rols.add(rol2);
		when(rolService.findAll()).thenReturn(rols);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Rolitemmenu> list = new ArrayList<Rolitemmenu>();
		when(rolitemmenuService.findAll()).thenReturn(list);
		
		// When
		String viewName = rolitemmenuController.list(model);
		
		// Then
		assertEquals("rolitemmenu/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = rolitemmenuController.formForCreate(model);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Rolitemmenu)modelMap.get("rolitemmenu")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rolitemmenu/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		Long id = rolitemmenu.getId();
		when(rolitemmenuService.findById(id)).thenReturn(rolitemmenu);
		
		// When
		String viewName = rolitemmenuController.formForUpdate(model, id);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenu, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rolitemmenu/update", modelMap.get("saveAction"));
		
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Rolitemmenu rolitemmenuCreated = new Rolitemmenu();
		when(rolitemmenuService.create(rolitemmenu)).thenReturn(rolitemmenuCreated); 
		
		// When
		String viewName = rolitemmenuController.create(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/rolitemmenu/form/"+rolitemmenu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenuCreated, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = rolitemmenuController.create(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenu, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rolitemmenu/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
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

		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		
		Exception exception = new RuntimeException("test exception");
		when(rolitemmenuService.create(rolitemmenu)).thenThrow(exception);
		
		// When
		String viewName = rolitemmenuController.create(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenu, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/rolitemmenu/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "rolitemmenu.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		Long id = rolitemmenu.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Rolitemmenu rolitemmenuSaved = new Rolitemmenu();
		rolitemmenuSaved.setId(id);
		when(rolitemmenuService.update(rolitemmenu)).thenReturn(rolitemmenuSaved); 
		
		// When
		String viewName = rolitemmenuController.update(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/rolitemmenu/form/"+rolitemmenu.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenuSaved, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = rolitemmenuController.update(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenu, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rolitemmenu/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
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

		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		
		Exception exception = new RuntimeException("test exception");
		when(rolitemmenuService.update(rolitemmenu)).thenThrow(exception);
		
		// When
		String viewName = rolitemmenuController.update(rolitemmenu, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("rolitemmenu/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(rolitemmenu, (Rolitemmenu) modelMap.get("rolitemmenu"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/rolitemmenu/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "rolitemmenu.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<RolListItem> rolListItems = (List<RolListItem>) modelMap.get("listOfRolItems");
		assertEquals(2, rolListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ItemmenuListItem> itemmenuListItems = (List<ItemmenuListItem>) modelMap.get("listOfItemmenuItems");
		assertEquals(2, itemmenuListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		Long id = rolitemmenu.getId();
		
		// When
		String viewName = rolitemmenuController.delete(redirectAttributes, id);
		
		// Then
		verify(rolitemmenuService).delete(id);
		assertEquals("redirect:/rolitemmenu", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Rolitemmenu rolitemmenu = rolitemmenuFactoryForTest.newRolitemmenu();
		Long id = rolitemmenu.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(rolitemmenuService).delete(id);
		
		// When
		String viewName = rolitemmenuController.delete(redirectAttributes, id);
		
		// Then
		verify(rolitemmenuService).delete(id);
		assertEquals("redirect:/rolitemmenu", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "rolitemmenu.error.delete", exception);
	}
	
	
}
