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
import com.tuin.bean.Vehiculoservicio;
import com.tuin.bean.Servicio;
import com.tuin.bean.Vehiculo;
import com.tuin.test.VehiculoservicioFactoryForTest;
import com.tuin.test.ServicioFactoryForTest;
import com.tuin.test.VehiculoFactoryForTest;

//--- Services 
import com.tuin.business.service.VehiculoservicioService;
import com.tuin.business.service.ServicioService;
import com.tuin.business.service.VehiculoService;

//--- List Items 
import com.tuin.web.listitem.ServicioListItem;
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
public class VehiculoservicioControllerTest {
	
	@InjectMocks
	private VehiculoservicioController vehiculoservicioController;
	@Mock
	private VehiculoservicioService vehiculoservicioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ServicioService servicioService; // Injected by Spring
	@Mock
	private VehiculoService vehiculoService; // Injected by Spring

	private VehiculoservicioFactoryForTest vehiculoservicioFactoryForTest = new VehiculoservicioFactoryForTest();
	private ServicioFactoryForTest servicioFactoryForTest = new ServicioFactoryForTest();
	private VehiculoFactoryForTest vehiculoFactoryForTest = new VehiculoFactoryForTest();

	List<Servicio> servicios = new ArrayList<Servicio>();
	List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	private void givenPopulateModel() {
		Servicio servicio1 = servicioFactoryForTest.newServicio();
		Servicio servicio2 = servicioFactoryForTest.newServicio();
		List<Servicio> servicios = new ArrayList<Servicio>();
		servicios.add(servicio1);
		servicios.add(servicio2);
		when(servicioService.findAll()).thenReturn(servicios);

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
		
		List<Vehiculoservicio> list = new ArrayList<Vehiculoservicio>();
		when(vehiculoservicioService.findAll()).thenReturn(list);
		
		// When
		String viewName = vehiculoservicioController.list(model);
		
		// Then
		assertEquals("vehiculoservicio/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = vehiculoservicioController.formForCreate(model);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Vehiculoservicio)modelMap.get("vehiculoservicio")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		Long id = vehiculoservicio.getId();
		when(vehiculoservicioService.findById(id)).thenReturn(vehiculoservicio);
		
		// When
		String viewName = vehiculoservicioController.formForUpdate(model, id);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicio, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/update", modelMap.get("saveAction"));
		
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Vehiculoservicio vehiculoservicioCreated = new Vehiculoservicio();
		when(vehiculoservicioService.create(vehiculoservicio)).thenReturn(vehiculoservicioCreated); 
		
		// When
		String viewName = vehiculoservicioController.create(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/vehiculoservicio/form/"+vehiculoservicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicioCreated, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = vehiculoservicioController.create(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicio, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
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

		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		
		Exception exception = new RuntimeException("test exception");
		when(vehiculoservicioService.create(vehiculoservicio)).thenThrow(exception);
		
		// When
		String viewName = vehiculoservicioController.create(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicio, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "vehiculoservicio.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		Long id = vehiculoservicio.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Vehiculoservicio vehiculoservicioSaved = new Vehiculoservicio();
		vehiculoservicioSaved.setId(id);
		when(vehiculoservicioService.update(vehiculoservicio)).thenReturn(vehiculoservicioSaved); 
		
		// When
		String viewName = vehiculoservicioController.update(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/vehiculoservicio/form/"+vehiculoservicio.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicioSaved, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = vehiculoservicioController.update(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicio, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
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

		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		
		Exception exception = new RuntimeException("test exception");
		when(vehiculoservicioService.update(vehiculoservicio)).thenThrow(exception);
		
		// When
		String viewName = vehiculoservicioController.update(vehiculoservicio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculoservicio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoservicio, (Vehiculoservicio) modelMap.get("vehiculoservicio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculoservicio/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "vehiculoservicio.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<VehiculoListItem> vehiculoListItems = (List<VehiculoListItem>) modelMap.get("listOfVehiculoItems");
		assertEquals(2, vehiculoListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ServicioListItem> servicioListItems = (List<ServicioListItem>) modelMap.get("listOfServicioItems");
		assertEquals(2, servicioListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		Long id = vehiculoservicio.getId();
		
		// When
		String viewName = vehiculoservicioController.delete(redirectAttributes, id);
		
		// Then
		verify(vehiculoservicioService).delete(id);
		assertEquals("redirect:/vehiculoservicio", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Vehiculoservicio vehiculoservicio = vehiculoservicioFactoryForTest.newVehiculoservicio();
		Long id = vehiculoservicio.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(vehiculoservicioService).delete(id);
		
		// When
		String viewName = vehiculoservicioController.delete(redirectAttributes, id);
		
		// Then
		verify(vehiculoservicioService).delete(id);
		assertEquals("redirect:/vehiculoservicio", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "vehiculoservicio.error.delete", exception);
	}
	
	
}
