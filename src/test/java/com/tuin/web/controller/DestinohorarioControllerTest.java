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
import com.tuin.bean.Destinohorario;
import com.tuin.bean.Ciudad;
import com.tuin.bean.Horario;
import com.tuin.test.DestinohorarioFactoryForTest;
import com.tuin.test.CiudadFactoryForTest;
import com.tuin.test.HorarioFactoryForTest;

//--- Services 
import com.tuin.business.service.DestinohorarioService;
import com.tuin.business.service.CiudadService;
import com.tuin.business.service.HorarioService;

//--- List Items 
import com.tuin.web.listitem.CiudadListItem;
import com.tuin.web.listitem.HorarioListItem;

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
public class DestinohorarioControllerTest {
	
	@InjectMocks
	private DestinohorarioController destinohorarioController;
	@Mock
	private DestinohorarioService destinohorarioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private CiudadService ciudadService; // Injected by Spring
	@Mock
	private HorarioService horarioService; // Injected by Spring

	private DestinohorarioFactoryForTest destinohorarioFactoryForTest = new DestinohorarioFactoryForTest();
	private CiudadFactoryForTest ciudadFactoryForTest = new CiudadFactoryForTest();
	private HorarioFactoryForTest horarioFactoryForTest = new HorarioFactoryForTest();

	List<Ciudad> ciudads = new ArrayList<Ciudad>();
	List<Horario> horarios = new ArrayList<Horario>();

	private void givenPopulateModel() {
		Ciudad ciudad1 = ciudadFactoryForTest.newCiudad();
		Ciudad ciudad2 = ciudadFactoryForTest.newCiudad();
		List<Ciudad> ciudads = new ArrayList<Ciudad>();
		ciudads.add(ciudad1);
		ciudads.add(ciudad2);
		when(ciudadService.findAll()).thenReturn(ciudads);

		Horario horario1 = horarioFactoryForTest.newHorario();
		Horario horario2 = horarioFactoryForTest.newHorario();
		List<Horario> horarios = new ArrayList<Horario>();
		horarios.add(horario1);
		horarios.add(horario2);
		when(horarioService.findAll()).thenReturn(horarios);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Destinohorario> list = new ArrayList<Destinohorario>();
		when(destinohorarioService.findAll()).thenReturn(list);
		
		// When
		String viewName = destinohorarioController.list(model);
		
		// Then
		assertEquals("destinohorario/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = destinohorarioController.formForCreate(model);
		
		// Then
		assertEquals("destinohorario/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Destinohorario)modelMap.get("destinohorario")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/destinohorario/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
		@SuppressWarnings("unchecked")
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		Long id = destinohorario.getId();
		when(destinohorarioService.findById(id)).thenReturn(destinohorario);
		
		// When
		String viewName = destinohorarioController.formForUpdate(model, id);
		
		// Then
		assertEquals("destinohorario/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorario, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/destinohorario/update", modelMap.get("saveAction"));
		
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Destinohorario destinohorarioCreated = new Destinohorario();
		when(destinohorarioService.create(destinohorario)).thenReturn(destinohorarioCreated); 
		
		// When
		String viewName = destinohorarioController.create(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/destinohorario/form/"+destinohorario.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorarioCreated, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = destinohorarioController.create(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("destinohorario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorario, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/destinohorario/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
		@SuppressWarnings("unchecked")
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
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

		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		
		Exception exception = new RuntimeException("test exception");
		when(destinohorarioService.create(destinohorario)).thenThrow(exception);
		
		// When
		String viewName = destinohorarioController.create(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("destinohorario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorario, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/destinohorario/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "destinohorario.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
		@SuppressWarnings("unchecked")
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		Long id = destinohorario.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Destinohorario destinohorarioSaved = new Destinohorario();
		destinohorarioSaved.setId(id);
		when(destinohorarioService.update(destinohorario)).thenReturn(destinohorarioSaved); 
		
		// When
		String viewName = destinohorarioController.update(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/destinohorario/form/"+destinohorario.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorarioSaved, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = destinohorarioController.update(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("destinohorario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorario, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/destinohorario/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
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

		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		
		Exception exception = new RuntimeException("test exception");
		when(destinohorarioService.update(destinohorario)).thenThrow(exception);
		
		// When
		String viewName = destinohorarioController.update(destinohorario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("destinohorario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(destinohorario, (Destinohorario) modelMap.get("destinohorario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/destinohorario/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "destinohorario.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<HorarioListItem> horarioListItems = (List<HorarioListItem>) modelMap.get("listOfHorarioItems");
		assertEquals(2, horarioListItems.size());
		
		@SuppressWarnings("unchecked")
		List<CiudadListItem> ciudadListItems = (List<CiudadListItem>) modelMap.get("listOfCiudadItems");
		assertEquals(2, ciudadListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		Long id = destinohorario.getId();
		
		// When
		String viewName = destinohorarioController.delete(redirectAttributes, id);
		
		// Then
		verify(destinohorarioService).delete(id);
		assertEquals("redirect:/destinohorario", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Destinohorario destinohorario = destinohorarioFactoryForTest.newDestinohorario();
		Long id = destinohorario.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(destinohorarioService).delete(id);
		
		// When
		String viewName = destinohorarioController.delete(redirectAttributes, id);
		
		// Then
		verify(destinohorarioService).delete(id);
		assertEquals("redirect:/destinohorario", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "destinohorario.error.delete", exception);
	}
	
	
}
