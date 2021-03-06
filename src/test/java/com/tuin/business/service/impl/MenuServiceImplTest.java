/*
 * Created on 28 ago 2017 ( Time 17:51:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Menu;
import com.tuin.bean.jpa.MenuEntity;
import java.util.List;
import com.tuin.business.service.mapping.MenuServiceMapper;
import com.tuin.persistence.services.jpa.MenuPersistenceJPA;
import com.tuin.test.MenuFactoryForTest;
import com.tuin.test.MenuEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of MenuService
 */
@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

	@InjectMocks
	private MenuServiceImpl menuService;
	@Mock
	private MenuPersistenceJPA menuPersistenceJPA;
	@Mock
	private MenuServiceMapper menuServiceMapper;
	
	private MenuFactoryForTest menuFactoryForTest = new MenuFactoryForTest();

	private MenuEntityFactoryForTest menuEntityFactoryForTest = new MenuEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		MenuEntity menuEntity = menuPersistenceJPA.load(id);
		
		Menu menu = menuFactoryForTest.newMenu();
		when(menuServiceMapper.mapMenuEntityToMenu(menuEntity)).thenReturn(menu);

		// When
		Menu menuFound = menuService.findById(id);

		// Then
		assertEquals(menu.getId(),menuFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<MenuEntity> menuEntitys = new ArrayList<MenuEntity>();
		MenuEntity menuEntity1 = menuEntityFactoryForTest.newMenuEntity();
		menuEntitys.add(menuEntity1);
		MenuEntity menuEntity2 = menuEntityFactoryForTest.newMenuEntity();
		menuEntitys.add(menuEntity2);
		when(menuPersistenceJPA.loadAll()).thenReturn(menuEntitys);
		
		Menu menu1 = menuFactoryForTest.newMenu();
		when(menuServiceMapper.mapMenuEntityToMenu(menuEntity1)).thenReturn(menu1);
		Menu menu2 = menuFactoryForTest.newMenu();
		when(menuServiceMapper.mapMenuEntityToMenu(menuEntity2)).thenReturn(menu2);

		// When
		List<Menu> menusFounds = menuService.findAll();

		// Then
		assertTrue(menu1 == menusFounds.get(0));
		assertTrue(menu2 == menusFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Menu menu = menuFactoryForTest.newMenu();

		MenuEntity menuEntity = menuEntityFactoryForTest.newMenuEntity();
		when(menuPersistenceJPA.load(menu.getId())).thenReturn(null);
		
		menuEntity = new MenuEntity();
		menuServiceMapper.mapMenuToMenuEntity(menu, menuEntity);
		MenuEntity menuEntitySaved = menuPersistenceJPA.save(menuEntity);
		
		Menu menuSaved = menuFactoryForTest.newMenu();
		when(menuServiceMapper.mapMenuEntityToMenu(menuEntitySaved)).thenReturn(menuSaved);

		// When
		Menu menuResult = menuService.create(menu);

		// Then
		assertTrue(menuResult == menuSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Menu menu = menuFactoryForTest.newMenu();

		MenuEntity menuEntity = menuEntityFactoryForTest.newMenuEntity();
		when(menuPersistenceJPA.load(menu.getId())).thenReturn(menuEntity);

		// When
		Exception exception = null;
		try {
			menuService.create(menu);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Menu menu = menuFactoryForTest.newMenu();

		MenuEntity menuEntity = menuEntityFactoryForTest.newMenuEntity();
		when(menuPersistenceJPA.load(menu.getId())).thenReturn(menuEntity);
		
		MenuEntity menuEntitySaved = menuEntityFactoryForTest.newMenuEntity();
		when(menuPersistenceJPA.save(menuEntity)).thenReturn(menuEntitySaved);
		
		Menu menuSaved = menuFactoryForTest.newMenu();
		when(menuServiceMapper.mapMenuEntityToMenu(menuEntitySaved)).thenReturn(menuSaved);

		// When
		Menu menuResult = menuService.update(menu);

		// Then
		verify(menuServiceMapper).mapMenuToMenuEntity(menu, menuEntity);
		assertTrue(menuResult == menuSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		menuService.delete(id);

		// Then
		verify(menuPersistenceJPA).delete(id);
		
	}

}
