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
import com.tuin.bean.Viaje;
import com.tuin.bean.Chofervehiculo;
import com.tuin.bean.Destinohorario;
import com.tuin.test.ViajeFactoryForTest;
import com.tuin.test.ChofervehiculoFactoryForTest;
import com.tuin.test.DestinohorarioFactoryForTest;

//--- Services 
import com.tuin.business.service.ViajeService;
import com.tuin.business.service.ChofervehiculoService;
import com.tuin.business.service.DestinohorarioService;

//--- List Items 
import com.tuin.web.listitem.ChofervehiculoListItem;
import com.tuin.web.listitem.DestinohorarioListItem;

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
public class ViajeControllerTest {
	
	@InjectMocks
	private ViajeController viajeController;
	@Mock
	private ViajeService viajeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ChofervehiculoService chofervehiculoService; // Injected by Spring
	@Mock
	private DestinohorarioService destinohorarioService; // Injected by Spring

	private ViajeFactoryForTest viajeFactoryForTest = new ViajeFactoryForTest();
	private ChofervehiculoFactoryForTest chofervehiculoFactoryForTest = new ChofervehiculoFactoryForTest();
	private DestinohorarioFactoryForTest destinohorarioFactoryForTest = new DestinohorarioFactoryForTest();

	List<Chofervehiculo> chofervehiculos = new ArrayList<Chofervehiculo>();
	List<Destinohorario> destinohorarios = new ArrayList<Destinohorario>();

	private void givenPopulateModel() {
		Chofervehiculo chofervehiculo1 = chofervehiculoFactoryForTest.newChofervehiculo();
		Chofervehiculo chofervehiculo2 = chofervehiculoFactoryForTest.newChofervehiculo();
		List<Chofervehiculo> chofervehiculos = new ArrayList<Chofervehiculo>();
		chofervehiculos.add(chofervehiculo1);
		chofervehiculos.add(chofervehiculo2);
		when(chofervehiculoService.findAll()).thenReturn(chofervehiculos);

		Destinohorario destinohorario1 = destinohorarioFactoryForTest.newDestinohorario();
		Destinohorario destinohorario2 = destinohorarioFactoryForTest.newDestinohorario();
		List<Destinohorario> destinohorarios = new ArrayList<Destinohorario>();
		destinohorarios.add(destinohorario1);
		destinohorarios.add(destinohorario2);
		when(destinohorarioService.findAll()).thenReturn(destinohorarios);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Viaje> list = new ArrayList<Viaje>();
		when(viajeService.findAll()).thenReturn(list);
		
		// When
		String viewName = viajeController.list(model);
		
		// Then
		assertEquals("viaje/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = viajeController.formForCreate(model);
		
		// Then
		assertEquals("viaje/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Viaje)modelMap.get("viaje")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/viaje/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		Long id = viaje.getId();
		when(viajeService.findById(id)).thenReturn(viaje);
		
		// When
		String viewName = viajeController.formForUpdate(model, id);
		
		// Then
		assertEquals("viaje/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viaje, (Viaje) modelMap.get("viaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/viaje/update", modelMap.get("saveAction"));
		
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Viaje viajeCreated = new Viaje();
		when(viajeService.create(viaje)).thenReturn(viajeCreated); 
		
		// When
		String viewName = viajeController.create(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/viaje/form/"+viaje.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viajeCreated, (Viaje) modelMap.get("viaje"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = viajeController.create(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("viaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viaje, (Viaje) modelMap.get("viaje"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/viaje/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
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

		Viaje viaje = viajeFactoryForTest.newViaje();
		
		Exception exception = new RuntimeException("test exception");
		when(viajeService.create(viaje)).thenThrow(exception);
		
		// When
		String viewName = viajeController.create(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("viaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viaje, (Viaje) modelMap.get("viaje"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/viaje/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "viaje.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		Long id = viaje.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Viaje viajeSaved = new Viaje();
		viajeSaved.setId(id);
		when(viajeService.update(viaje)).thenReturn(viajeSaved); 
		
		// When
		String viewName = viajeController.update(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/viaje/form/"+viaje.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viajeSaved, (Viaje) modelMap.get("viaje"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = viajeController.update(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("viaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viaje, (Viaje) modelMap.get("viaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/viaje/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
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

		Viaje viaje = viajeFactoryForTest.newViaje();
		
		Exception exception = new RuntimeException("test exception");
		when(viajeService.update(viaje)).thenThrow(exception);
		
		// When
		String viewName = viajeController.update(viaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("viaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(viaje, (Viaje) modelMap.get("viaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/viaje/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "viaje.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<DestinohorarioListItem> destinohorarioListItems = (List<DestinohorarioListItem>) modelMap.get("listOfDestinohorarioItems");
		assertEquals(2, destinohorarioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ChofervehiculoListItem> chofervehiculoListItems = (List<ChofervehiculoListItem>) modelMap.get("listOfChofervehiculoItems");
		assertEquals(2, chofervehiculoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		Long id = viaje.getId();
		
		// When
		String viewName = viajeController.delete(redirectAttributes, id);
		
		// Then
		verify(viajeService).delete(id);
		assertEquals("redirect:/viaje", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Viaje viaje = viajeFactoryForTest.newViaje();
		Long id = viaje.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(viajeService).delete(id);
		
		// When
		String viewName = viajeController.delete(redirectAttributes, id);
		
		// Then
		verify(viajeService).delete(id);
		assertEquals("redirect:/viaje", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "viaje.error.delete", exception);
	}
	
	
}
