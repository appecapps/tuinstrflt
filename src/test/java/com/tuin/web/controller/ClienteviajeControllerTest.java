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
import com.tuin.bean.Clienteviaje;
import com.tuin.bean.Viaje;
import com.tuin.bean.Cliente;
import com.tuin.bean.Estado;
import com.tuin.test.ClienteviajeFactoryForTest;
import com.tuin.test.ViajeFactoryForTest;
import com.tuin.test.ClienteFactoryForTest;
import com.tuin.test.EstadoFactoryForTest;

//--- Services 
import com.tuin.business.service.ClienteviajeService;
import com.tuin.business.service.ViajeService;
import com.tuin.business.service.ClienteService;
import com.tuin.business.service.EstadoService;

//--- List Items 
import com.tuin.web.listitem.ViajeListItem;
import com.tuin.web.listitem.ClienteListItem;
import com.tuin.web.listitem.EstadoListItem;

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
public class ClienteviajeControllerTest {
	
	@InjectMocks
	private ClienteviajeController clienteviajeController;
	@Mock
	private ClienteviajeService clienteviajeService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ViajeService viajeService; // Injected by Spring
	@Mock
	private ClienteService clienteService; // Injected by Spring
	@Mock
	private EstadoService estadoService; // Injected by Spring

	private ClienteviajeFactoryForTest clienteviajeFactoryForTest = new ClienteviajeFactoryForTest();
	private ViajeFactoryForTest viajeFactoryForTest = new ViajeFactoryForTest();
	private ClienteFactoryForTest clienteFactoryForTest = new ClienteFactoryForTest();
	private EstadoFactoryForTest estadoFactoryForTest = new EstadoFactoryForTest();

	List<Viaje> viajes = new ArrayList<Viaje>();
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<Estado> estados = new ArrayList<Estado>();

	private void givenPopulateModel() {
		Viaje viaje1 = viajeFactoryForTest.newViaje();
		Viaje viaje2 = viajeFactoryForTest.newViaje();
		List<Viaje> viajes = new ArrayList<Viaje>();
		viajes.add(viaje1);
		viajes.add(viaje2);
		when(viajeService.findAll()).thenReturn(viajes);

		Cliente cliente1 = clienteFactoryForTest.newCliente();
		Cliente cliente2 = clienteFactoryForTest.newCliente();
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		when(clienteService.findAll()).thenReturn(clientes);

		Estado estado1 = estadoFactoryForTest.newEstado();
		Estado estado2 = estadoFactoryForTest.newEstado();
		List<Estado> estados = new ArrayList<Estado>();
		estados.add(estado1);
		estados.add(estado2);
		when(estadoService.findAll()).thenReturn(estados);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Clienteviaje> list = new ArrayList<Clienteviaje>();
		when(clienteviajeService.findAll()).thenReturn(list);
		
		// When
		String viewName = clienteviajeController.list(model);
		
		// Then
		assertEquals("clienteviaje/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = clienteviajeController.formForCreate(model);
		
		// Then
		assertEquals("clienteviaje/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Clienteviaje)modelMap.get("clienteviaje")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviaje/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		Long id = clienteviaje.getId();
		when(clienteviajeService.findById(id)).thenReturn(clienteviaje);
		
		// When
		String viewName = clienteviajeController.formForUpdate(model, id);
		
		// Then
		assertEquals("clienteviaje/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviaje, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviaje/update", modelMap.get("saveAction"));
		
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Clienteviaje clienteviajeCreated = new Clienteviaje();
		when(clienteviajeService.create(clienteviaje)).thenReturn(clienteviajeCreated); 
		
		// When
		String viewName = clienteviajeController.create(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/clienteviaje/form/"+clienteviaje.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeCreated, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = clienteviajeController.create(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviaje, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviaje/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
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

		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		
		Exception exception = new RuntimeException("test exception");
		when(clienteviajeService.create(clienteviaje)).thenThrow(exception);
		
		// When
		String viewName = clienteviajeController.create(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviaje, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/clienteviaje/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "clienteviaje.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		Long id = clienteviaje.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Clienteviaje clienteviajeSaved = new Clienteviaje();
		clienteviajeSaved.setId(id);
		when(clienteviajeService.update(clienteviaje)).thenReturn(clienteviajeSaved); 
		
		// When
		String viewName = clienteviajeController.update(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/clienteviaje/form/"+clienteviaje.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviajeSaved, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = clienteviajeController.update(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviaje, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviaje/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
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

		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		
		Exception exception = new RuntimeException("test exception");
		when(clienteviajeService.update(clienteviaje)).thenThrow(exception);
		
		// When
		String viewName = clienteviajeController.update(clienteviaje, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("clienteviaje/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(clienteviaje, (Clienteviaje) modelMap.get("clienteviaje"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/clienteviaje/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "clienteviaje.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<ViajeListItem> viajeListItems = (List<ViajeListItem>) modelMap.get("listOfViajeItems");
		assertEquals(2, viajeListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ClienteListItem> clienteListItems = (List<ClienteListItem>) modelMap.get("listOfClienteItems");
		assertEquals(2, clienteListItems.size());
		
		@SuppressWarnings("unchecked")
		List<EstadoListItem> estadoListItems = (List<EstadoListItem>) modelMap.get("listOfEstadoItems");
		assertEquals(2, estadoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		Long id = clienteviaje.getId();
		
		// When
		String viewName = clienteviajeController.delete(redirectAttributes, id);
		
		// Then
		verify(clienteviajeService).delete(id);
		assertEquals("redirect:/clienteviaje", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		Long id = clienteviaje.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(clienteviajeService).delete(id);
		
		// When
		String viewName = clienteviajeController.delete(redirectAttributes, id);
		
		// Then
		verify(clienteviajeService).delete(id);
		assertEquals("redirect:/clienteviaje", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "clienteviaje.error.delete", exception);
	}
	
	
}
