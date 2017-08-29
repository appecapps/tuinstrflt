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
import com.tuin.bean.Chofervehiculo;
import com.tuin.bean.Chofer;
import com.tuin.bean.Vehiculo;
import com.tuin.test.ChofervehiculoFactoryForTest;
import com.tuin.test.ChoferFactoryForTest;
import com.tuin.test.VehiculoFactoryForTest;

//--- Services 
import com.tuin.business.service.ChofervehiculoService;
import com.tuin.business.service.ChoferService;
import com.tuin.business.service.VehiculoService;

//--- List Items 
import com.tuin.web.listitem.ChoferListItem;
import com.tuin.web.listitem.VehiculoListItem;

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
public class ChofervehiculoControllerTest {
	
	@InjectMocks
	private ChofervehiculoController chofervehiculoController;
	@Mock
	private ChofervehiculoService chofervehiculoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ChoferService choferService; // Injected by Spring
	@Mock
	private VehiculoService vehiculoService; // Injected by Spring

	private ChofervehiculoFactoryForTest chofervehiculoFactoryForTest = new ChofervehiculoFactoryForTest();
	private ChoferFactoryForTest choferFactoryForTest = new ChoferFactoryForTest();
	private VehiculoFactoryForTest vehiculoFactoryForTest = new VehiculoFactoryForTest();

	List<Chofer> chofers = new ArrayList<Chofer>();
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	private void givenPopulateModel() {
		Chofer chofer1 = choferFactoryForTest.newChofer();
		Chofer chofer2 = choferFactoryForTest.newChofer();
		List<Chofer> chofers = new ArrayList<Chofer>();
		chofers.add(chofer1);
		chofers.add(chofer2);
		when(choferService.findAll()).thenReturn(chofers);

		Vehiculo vehiculo1 = vehiculoFactoryForTest.newVehiculo();
		Vehiculo vehiculo2 = vehiculoFactoryForTest.newVehiculo();
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos.add(vehiculo1);
		vehiculos.add(vehiculo2);
		when(vehiculoService.findAll()).thenReturn(vehiculos);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Chofervehiculo> list = new ArrayList<Chofervehiculo>();
		when(chofervehiculoService.findAll()).thenReturn(list);
		
		// When
		String viewName = chofervehiculoController.list(model);
		
		// Then
		assertEquals("chofervehiculo/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = chofervehiculoController.formForCreate(model);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Chofervehiculo)modelMap.get("chofervehiculo")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofervehiculo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		Long id = chofervehiculo.getId();
		when(chofervehiculoService.findById(id)).thenReturn(chofervehiculo);
		
		// When
		String viewName = chofervehiculoController.formForUpdate(model, id);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculo, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofervehiculo/update", modelMap.get("saveAction"));
		
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Chofervehiculo chofervehiculoCreated = new Chofervehiculo();
		when(chofervehiculoService.create(chofervehiculo)).thenReturn(chofervehiculoCreated); 
		
		// When
		String viewName = chofervehiculoController.create(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/chofervehiculo/form/"+chofervehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculoCreated, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = chofervehiculoController.create(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculo, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofervehiculo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
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

		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(chofervehiculoService.create(chofervehiculo)).thenThrow(exception);
		
		// When
		String viewName = chofervehiculoController.create(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculo, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/chofervehiculo/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "chofervehiculo.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		Long id = chofervehiculo.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Chofervehiculo chofervehiculoSaved = new Chofervehiculo();
		chofervehiculoSaved.setId(id);
		when(chofervehiculoService.update(chofervehiculo)).thenReturn(chofervehiculoSaved); 
		
		// When
		String viewName = chofervehiculoController.update(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/chofervehiculo/form/"+chofervehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculoSaved, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = chofervehiculoController.update(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculo, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofervehiculo/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
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

		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(chofervehiculoService.update(chofervehiculo)).thenThrow(exception);
		
		// When
		String viewName = chofervehiculoController.update(chofervehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("chofervehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(chofervehiculo, (Chofervehiculo) modelMap.get("chofervehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/chofervehiculo/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "chofervehiculo.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<ChoferListItem> choferListItems = (List<ChoferListItem>) modelMap.get("listOfChoferItems");
		assertEquals(2, choferListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		Long id = chofervehiculo.getId();
		
		// When
		String viewName = chofervehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(chofervehiculoService).delete(id);
		assertEquals("redirect:/chofervehiculo", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Chofervehiculo chofervehiculo = chofervehiculoFactoryForTest.newChofervehiculo();
		Long id = chofervehiculo.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(chofervehiculoService).delete(id);
		
		// When
		String viewName = chofervehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(chofervehiculoService).delete(id);
		assertEquals("redirect:/chofervehiculo", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "chofervehiculo.error.delete", exception);
	}
	
	
}
