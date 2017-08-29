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
import com.tuin.bean.Modelo;
import com.tuin.bean.Marca;
import com.tuin.bean.Tipovehiculo;
import com.tuin.test.ModeloFactoryForTest;
import com.tuin.test.MarcaFactoryForTest;
import com.tuin.test.TipovehiculoFactoryForTest;

//--- Services 
import com.tuin.business.service.ModeloService;
import com.tuin.business.service.MarcaService;
import com.tuin.business.service.TipovehiculoService;

//--- List Items 
import com.tuin.web.listitem.MarcaListItem;
import com.tuin.web.listitem.TipovehiculoListItem;

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
public class ModeloControllerTest {
	
	@InjectMocks
	private ModeloController modeloController;
	@Mock
	private ModeloService modeloService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private MarcaService marcaService; // Injected by Spring
	@Mock
	private TipovehiculoService tipovehiculoService; // Injected by Spring

	private ModeloFactoryForTest modeloFactoryForTest = new ModeloFactoryForTest();
	private MarcaFactoryForTest marcaFactoryForTest = new MarcaFactoryForTest();
	private TipovehiculoFactoryForTest tipovehiculoFactoryForTest = new TipovehiculoFactoryForTest();

	List<Marca> marcas = new ArrayList<Marca>();
	List<Tipovehiculo> tipovehiculos = new ArrayList<Tipovehiculo>();

	private void givenPopulateModel() {
		Marca marca1 = marcaFactoryForTest.newMarca();
		Marca marca2 = marcaFactoryForTest.newMarca();
		List<Marca> marcas = new ArrayList<Marca>();
		marcas.add(marca1);
		marcas.add(marca2);
		when(marcaService.findAll()).thenReturn(marcas);

		Tipovehiculo tipovehiculo1 = tipovehiculoFactoryForTest.newTipovehiculo();
		Tipovehiculo tipovehiculo2 = tipovehiculoFactoryForTest.newTipovehiculo();
		List<Tipovehiculo> tipovehiculos = new ArrayList<Tipovehiculo>();
		tipovehiculos.add(tipovehiculo1);
		tipovehiculos.add(tipovehiculo2);
		when(tipovehiculoService.findAll()).thenReturn(tipovehiculos);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Modelo> list = new ArrayList<Modelo>();
		when(modeloService.findAll()).thenReturn(list);
		
		// When
		String viewName = modeloController.list(model);
		
		// Then
		assertEquals("modelo/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = modeloController.formForCreate(model);
		
		// Then
		assertEquals("modelo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Modelo)modelMap.get("modelo")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/modelo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		Long id = modelo.getId();
		when(modeloService.findById(id)).thenReturn(modelo);
		
		// When
		String viewName = modeloController.formForUpdate(model, id);
		
		// Then
		assertEquals("modelo/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modelo, (Modelo) modelMap.get("modelo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/modelo/update", modelMap.get("saveAction"));
		
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Modelo modeloCreated = new Modelo();
		when(modeloService.create(modelo)).thenReturn(modeloCreated); 
		
		// When
		String viewName = modeloController.create(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/modelo/form/"+modelo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modeloCreated, (Modelo) modelMap.get("modelo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = modeloController.create(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("modelo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modelo, (Modelo) modelMap.get("modelo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/modelo/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
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

		Modelo modelo = modeloFactoryForTest.newModelo();
		
		Exception exception = new RuntimeException("test exception");
		when(modeloService.create(modelo)).thenThrow(exception);
		
		// When
		String viewName = modeloController.create(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("modelo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modelo, (Modelo) modelMap.get("modelo"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/modelo/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "modelo.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		Long id = modelo.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Modelo modeloSaved = new Modelo();
		modeloSaved.setId(id);
		when(modeloService.update(modelo)).thenReturn(modeloSaved); 
		
		// When
		String viewName = modeloController.update(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/modelo/form/"+modelo.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modeloSaved, (Modelo) modelMap.get("modelo"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = modeloController.update(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("modelo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modelo, (Modelo) modelMap.get("modelo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/modelo/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
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

		Modelo modelo = modeloFactoryForTest.newModelo();
		
		Exception exception = new RuntimeException("test exception");
		when(modeloService.update(modelo)).thenThrow(exception);
		
		// When
		String viewName = modeloController.update(modelo, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("modelo/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(modelo, (Modelo) modelMap.get("modelo"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/modelo/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "modelo.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<MarcaListItem> marcaListItems = (List<MarcaListItem>) modelMap.get("listOfMarcaItems");
		assertEquals(2, marcaListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TipovehiculoListItem> tipovehiculoListItems = (List<TipovehiculoListItem>) modelMap.get("listOfTipovehiculoItems");
		assertEquals(2, tipovehiculoListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		Long id = modelo.getId();
		
		// When
		String viewName = modeloController.delete(redirectAttributes, id);
		
		// Then
		verify(modeloService).delete(id);
		assertEquals("redirect:/modelo", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		Long id = modelo.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(modeloService).delete(id);
		
		// When
		String viewName = modeloController.delete(redirectAttributes, id);
		
		// Then
		verify(modeloService).delete(id);
		assertEquals("redirect:/modelo", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "modelo.error.delete", exception);
	}
	
	
}
