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
import com.tuin.bean.Clienteviajeservicio;
import com.tuin.bean.Clienteviaje;
import com.tuin.bean.Vehiculoservicio;
import com.tuin.test.ClienteviajeservicioFactoryForTest;
import com.tuin.test.ClienteviajeFactoryForTest;
import com.tuin.test.VehiculoservicioFactoryForTest;

//--- Services 
import com.tuin.business.service.ClienteviajeservicioService;
import com.tuin.business.service.ClienteviajeService;
import com.tuin.business.service.VehiculoservicioService;

//--- List Items 
import com.tuin.web.listitem.ClienteviajeListItem;
import com.tuin.web.listitem.VehiculoservicioListItem;

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
public class ClienteviajeservicioControllerTest {
	
	@InjectMocks
	private ClienteviajeservicioController clienteviajeservicioController;
	@Mock
	private ClienteviajeservicioService clienteviajeservicioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ClienteviajeService clienteviajeService; // Injected by Spring
	@Mock
	private VehiculoservicioService vehiculoservicioService; // Injected by Spring

	private ClienteviajeservicioFactoryForTest clienteviajeservicioFactoryForTest = new ClienteviajeservicioFactoryForTest();
	private ClienteviajeFactoryForTest clienteviajeFactoryForTest = new ClienteviajeFactoryForTest();
	private VehiculoservicioFactoryForTest vehiculoservicioFactoryForTest = new VehiculoservicioFactoryForTest();

	List<Clienteviaje> clienteviajes = new ArrayList<Clienteviaje>();
	List<Vehiculoservicio> vehiculoservicios = new ArrayList<Vehiculoservicio>();

	private void givenPopulateModel() {
		Clienteviaje clienteviaje1 = clienteviajeFactoryForTest.newClienteviaje();
		Clienteviaje clienteviaje2 = clienteviajeFactoryForTest.newClienteviaje();
		List<Clienteviaje> clienteviajes = new ArrayList<Clienteviaje>();
		clienteviajes.add(clienteviaje1);
		clienteviajes.add(clienteviaje2);
		when(clienteviajeService.findAll()).thenReturn(clienteviajes);

		Vehiculoservicio vehiculoservicio1 = vehiculoservicioFactoryForTest.newVehiculoservicio();
		Vehiculoservicio vehiculoservicio2 = vehiculoservicioFactoryForTest.newVehiculoservicio();
		List<Vehiculoservicio> vehiculoservicios = new ArrayList<Vehiculoservicio>();
		vehiculoservicios.add(vehiculoservicio1);
		vehiculoservicios.add(vehiculoservicio2);
		when(vehiculoservicioService.findAll()).thenReturn(vehiculoservicios);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Clienteviajeservicio> list = new ArrayList<Clienteviajeservicio>();
		when(clienteviajeservicioService.findAll()).thenReturn(list);
		
		// When
		String viewName = clienteviajeservicioController.list(model);
		
		// Then
		assertEquals("clienteviajeservicio/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = clienteviajeservicioController.formForCreate(model);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Clienteviajeservicio)modelMap.get("clienteviajeservicio")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		Long id = clienteviajeservicio.getId();
		when(clienteviajeservicioService.findById(id)).thenReturn(clienteviajeservicio);
		
		// When
		String viewName = clienteviajeservicioController.formForUpdate(model, id);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicio, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/update", modelMap.get("saveAction"));
		
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Clienteviajeservicio clienteviajeservicioCreated = new Clienteviajeservicio();
		when(clienteviajeservicioService.create(clienteviajeservicio)).thenReturn(clienteviajeservicioCreated); 
		
		// When
		String viewName = clienteviajeservicioController.create(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/clienteviajeservicio/form/"+clienteviajeservicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicioCreated, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = clienteviajeservicioController.create(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicio, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
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

		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		
		Exception exception = new RuntimeException("test exception");
		when(clienteviajeservicioService.create(clienteviajeservicio)).thenThrow(exception);
		
		// When
		String viewName = clienteviajeservicioController.create(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicio, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "clienteviajeservicio.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		Long id = clienteviajeservicio.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Clienteviajeservicio clienteviajeservicioSaved = new Clienteviajeservicio();
		clienteviajeservicioSaved.setId(id);
		when(clienteviajeservicioService.update(clienteviajeservicio)).thenReturn(clienteviajeservicioSaved); 
		
		// When
		String viewName = clienteviajeservicioController.update(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/clienteviajeservicio/form/"+clienteviajeservicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicioSaved, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = clienteviajeservicioController.update(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicio, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
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

		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		
		Exception exception = new RuntimeException("test exception");
		when(clienteviajeservicioService.update(clienteviajeservicio)).thenThrow(exception);
		
		// When
		String viewName = clienteviajeservicioController.update(clienteviajeservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviajeservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeservicio, (Clienteviajeservicio) modelMap.get("clienteviajeservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviajeservicio/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "clienteviajeservicio.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<ClienteviajeListItem> clienteviajeListItems = (List<ClienteviajeListItem>) modelMap.get("listOfClienteviajeItems");
		assertEquals(2, clienteviajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoservicioListItem> vehiculoservicioListItems = (List<VehiculoservicioListItem>) modelMap.get("listOfVehiculoservicioItems");
		assertEquals(2, vehiculoservicioListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		Long id = clienteviajeservicio.getId();
		
		// When
		String viewName = clienteviajeservicioController.delete(redirectAttributes, id);
		
		// Then
		verify(clienteviajeservicioService).delete(id);
		assertEquals("redirect:/clienteviajeservicio", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioFactoryForTest.newClienteviajeservicio();
		Long id = clienteviajeservicio.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(clienteviajeservicioService).delete(id);
		
		// When
		String viewName = clienteviajeservicioController.delete(redirectAttributes, id);
		
		// Then
		verify(clienteviajeservicioService).delete(id);
		assertEquals("redirect:/clienteviajeservicio", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "clienteviajeservicio.error.delete", exception);
	}
	
	
}
