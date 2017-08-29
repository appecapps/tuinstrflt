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
import com.tuin.bean.Horario;
import com.tuin.test.HorarioFactoryForTest;

//--- Services 
import com.tuin.business.service.HorarioService;


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
public class HorarioControllerTest {
	
	@InjectMocks
	private HorarioController horarioController;
	@Mock
	private HorarioService horarioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private HorarioFactoryForTest horarioFactoryForTest = new HorarioFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Horario> list = new ArrayList<Horario>();
		when(horarioService.findAll()).thenReturn(list);
		
		// When
		String viewName = horarioController.list(model);
		
		// Then
		assertEquals("horario/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = horarioController.formForCreate(model);
		
		// Then
		assertEquals("horario/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Horario)modelMap.get("horario")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/horario/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Horario horario = horarioFactoryForTest.newHorario();
		Long id = horario.getId();
		when(horarioService.findById(id)).thenReturn(horario);
		
		// When
		String viewName = horarioController.formForUpdate(model, id);
		
		// Then
		assertEquals("horario/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horario, (Horario) modelMap.get("horario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/horario/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Horario horario = horarioFactoryForTest.newHorario();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Horario horarioCreated = new Horario();
		when(horarioService.create(horario)).thenReturn(horarioCreated); 
		
		// When
		String viewName = horarioController.create(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/horario/form/"+horario.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horarioCreated, (Horario) modelMap.get("horario"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Horario horario = horarioFactoryForTest.newHorario();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = horarioController.create(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("horario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horario, (Horario) modelMap.get("horario"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/horario/create", modelMap.get("saveAction"));
		
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

		Horario horario = horarioFactoryForTest.newHorario();
		
		Exception exception = new RuntimeException("test exception");
		when(horarioService.create(horario)).thenThrow(exception);
		
		// When
		String viewName = horarioController.create(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("horario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horario, (Horario) modelMap.get("horario"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/horario/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "horario.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Horario horario = horarioFactoryForTest.newHorario();
		Long id = horario.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Horario horarioSaved = new Horario();
		horarioSaved.setId(id);
		when(horarioService.update(horario)).thenReturn(horarioSaved); 
		
		// When
		String viewName = horarioController.update(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/horario/form/"+horario.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horarioSaved, (Horario) modelMap.get("horario"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Horario horario = horarioFactoryForTest.newHorario();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = horarioController.update(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("horario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horario, (Horario) modelMap.get("horario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/horario/update", modelMap.get("saveAction"));
		
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

		Horario horario = horarioFactoryForTest.newHorario();
		
		Exception exception = new RuntimeException("test exception");
		when(horarioService.update(horario)).thenThrow(exception);
		
		// When
		String viewName = horarioController.update(horario, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("horario/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(horario, (Horario) modelMap.get("horario"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/horario/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "horario.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Horario horario = horarioFactoryForTest.newHorario();
		Long id = horario.getId();
		
		// When
		String viewName = horarioController.delete(redirectAttributes, id);
		
		// Then
		verify(horarioService).delete(id);
		assertEquals("redirect:/horario", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Horario horario = horarioFactoryForTest.newHorario();
		Long id = horario.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(horarioService).delete(id);
		
		// When
		String viewName = horarioController.delete(redirectAttributes, id);
		
		// Then
		verify(horarioService).delete(id);
		assertEquals("redirect:/horario", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "horario.error.delete", exception);
	}
	
	
}
