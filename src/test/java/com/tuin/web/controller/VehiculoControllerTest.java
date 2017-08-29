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
import com.tuin.bean.Vehiculo;
import com.tuin.bean.Modelo;
import com.tuin.bean.Color;
import com.tuin.test.VehiculoFactoryForTest;
import com.tuin.test.ModeloFactoryForTest;
import com.tuin.test.ColorFactoryForTest;

//--- Services 
import com.tuin.business.service.VehiculoService;
import com.tuin.business.service.ModeloService;
import com.tuin.business.service.ColorService;

//--- List Items 
import com.tuin.web.listitem.ModeloListItem;
import com.tuin.web.listitem.ColorListItem;

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
public class VehiculoControllerTest {
	
	@InjectMocks
	private VehiculoController vehiculoController;
	@Mock
	private VehiculoService vehiculoService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private ModeloService modeloService; // Injected by Spring
	@Mock
	private ColorService colorService; // Injected by Spring

	private VehiculoFactoryForTest vehiculoFactoryForTest = new VehiculoFactoryForTest();
	private ModeloFactoryForTest modeloFactoryForTest = new ModeloFactoryForTest();
	private ColorFactoryForTest colorFactoryForTest = new ColorFactoryForTest();

	List<Modelo> modelos = new ArrayList<Modelo>();
	List<Color> colors = new ArrayList<Color>();

	private void givenPopulateModel() {
		Modelo modelo1 = modeloFactoryForTest.newModelo();
		Modelo modelo2 = modeloFactoryForTest.newModelo();
		List<Modelo> modelos = new ArrayList<Modelo>();
		modelos.add(modelo1);
		modelos.add(modelo2);
		when(modeloService.findAll()).thenReturn(modelos);

		Color color1 = colorFactoryForTest.newColor();
		Color color2 = colorFactoryForTest.newColor();
		List<Color> colors = new ArrayList<Color>();
		colors.add(color1);
		colors.add(color2);
		when(colorService.findAll()).thenReturn(colors);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Vehiculo> list = new ArrayList<Vehiculo>();
		when(vehiculoService.findAll()).thenReturn(list);
		
		// When
		String viewName = vehiculoController.list(model);
		
		// Then
		assertEquals("vehiculo/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = vehiculoController.formForCreate(model);
		
		// Then
		assertEquals("vehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Vehiculo)modelMap.get("vehiculo")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		Long id = vehiculo.getId();
		when(vehiculoService.findById(id)).thenReturn(vehiculo);
		
		// When
		String viewName = vehiculoController.formForUpdate(model, id);
		
		// Then
		assertEquals("vehiculo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculo, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculo/update", modelMap.get("saveAction"));
		
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Vehiculo vehiculoCreated = new Vehiculo();
		when(vehiculoService.create(vehiculo)).thenReturn(vehiculoCreated); 
		
		// When
		String viewName = vehiculoController.create(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/vehiculo/form/"+vehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoCreated, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = vehiculoController.create(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculo, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
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

		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(vehiculoService.create(vehiculo)).thenThrow(exception);
		
		// When
		String viewName = vehiculoController.create(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculo, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/vehiculo/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "vehiculo.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		Long id = vehiculo.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Vehiculo vehiculoSaved = new Vehiculo();
		vehiculoSaved.setId(id);
		when(vehiculoService.update(vehiculo)).thenReturn(vehiculoSaved); 
		
		// When
		String viewName = vehiculoController.update(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/vehiculo/form/"+vehiculo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculoSaved, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = vehiculoController.update(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculo, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculo/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
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

		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		
		Exception exception = new RuntimeException("test exception");
		when(vehiculoService.update(vehiculo)).thenThrow(exception);
		
		// When
		String viewName = vehiculoController.update(vehiculo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("vehiculo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(vehiculo, (Vehiculo) modelMap.get("vehiculo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/vehiculo/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "vehiculo.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<ModeloListItem> modeloListItems = (List<ModeloListItem>) modelMap.get("listOfModeloItems");
		assertEquals(2, modeloListItems.size());
		
		@SuppressWarnings("unchecked")
		List<ColorListItem> colorListItems = (List<ColorListItem>) modelMap.get("listOfColorItems");
		assertEquals(2, colorListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		Long id = vehiculo.getId();
		
		// When
		String viewName = vehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(vehiculoService).delete(id);
		assertEquals("redirect:/vehiculo", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		Long id = vehiculo.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(vehiculoService).delete(id);
		
		// When
		String viewName = vehiculoController.delete(redirectAttributes, id);
		
		// Then
		verify(vehiculoService).delete(id);
		assertEquals("redirect:/vehiculo", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "vehiculo.error.delete", exception);
	}
	
	
}
