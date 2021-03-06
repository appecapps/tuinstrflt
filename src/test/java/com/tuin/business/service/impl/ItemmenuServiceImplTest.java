/*
 * Created on 28 ago 2017 ( Time 17:51:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Itemmenu;
import com.tuin.bean.jpa.ItemmenuEntity;
import java.util.List;
import com.tuin.business.service.mapping.ItemmenuServiceMapper;
import com.tuin.persistence.services.jpa.ItemmenuPersistenceJPA;
import com.tuin.test.ItemmenuFactoryForTest;
import com.tuin.test.ItemmenuEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of ItemmenuService
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemmenuServiceImplTest {

	@InjectMocks
	private ItemmenuServiceImpl itemmenuService;
	@Mock
	private ItemmenuPersistenceJPA itemmenuPersistenceJPA;
	@Mock
	private ItemmenuServiceMapper itemmenuServiceMapper;
	
	private ItemmenuFactoryForTest itemmenuFactoryForTest = new ItemmenuFactoryForTest();

	private ItemmenuEntityFactoryForTest itemmenuEntityFactoryForTest = new ItemmenuEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		ItemmenuEntity itemmenuEntity = itemmenuPersistenceJPA.load(id);
		
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();
		when(itemmenuServiceMapper.mapItemmenuEntityToItemmenu(itemmenuEntity)).thenReturn(itemmenu);

		// When
		Itemmenu itemmenuFound = itemmenuService.findById(id);

		// Then
		assertEquals(itemmenu.getId(),itemmenuFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<ItemmenuEntity> itemmenuEntitys = new ArrayList<ItemmenuEntity>();
		ItemmenuEntity itemmenuEntity1 = itemmenuEntityFactoryForTest.newItemmenuEntity();
		itemmenuEntitys.add(itemmenuEntity1);
		ItemmenuEntity itemmenuEntity2 = itemmenuEntityFactoryForTest.newItemmenuEntity();
		itemmenuEntitys.add(itemmenuEntity2);
		when(itemmenuPersistenceJPA.loadAll()).thenReturn(itemmenuEntitys);
		
		Itemmenu itemmenu1 = itemmenuFactoryForTest.newItemmenu();
		when(itemmenuServiceMapper.mapItemmenuEntityToItemmenu(itemmenuEntity1)).thenReturn(itemmenu1);
		Itemmenu itemmenu2 = itemmenuFactoryForTest.newItemmenu();
		when(itemmenuServiceMapper.mapItemmenuEntityToItemmenu(itemmenuEntity2)).thenReturn(itemmenu2);

		// When
		List<Itemmenu> itemmenusFounds = itemmenuService.findAll();

		// Then
		assertTrue(itemmenu1 == itemmenusFounds.get(0));
		assertTrue(itemmenu2 == itemmenusFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();

		ItemmenuEntity itemmenuEntity = itemmenuEntityFactoryForTest.newItemmenuEntity();
		when(itemmenuPersistenceJPA.load(itemmenu.getId())).thenReturn(null);
		
		itemmenuEntity = new ItemmenuEntity();
		itemmenuServiceMapper.mapItemmenuToItemmenuEntity(itemmenu, itemmenuEntity);
		ItemmenuEntity itemmenuEntitySaved = itemmenuPersistenceJPA.save(itemmenuEntity);
		
		Itemmenu itemmenuSaved = itemmenuFactoryForTest.newItemmenu();
		when(itemmenuServiceMapper.mapItemmenuEntityToItemmenu(itemmenuEntitySaved)).thenReturn(itemmenuSaved);

		// When
		Itemmenu itemmenuResult = itemmenuService.create(itemmenu);

		// Then
		assertTrue(itemmenuResult == itemmenuSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();

		ItemmenuEntity itemmenuEntity = itemmenuEntityFactoryForTest.newItemmenuEntity();
		when(itemmenuPersistenceJPA.load(itemmenu.getId())).thenReturn(itemmenuEntity);

		// When
		Exception exception = null;
		try {
			itemmenuService.create(itemmenu);
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
		Itemmenu itemmenu = itemmenuFactoryForTest.newItemmenu();

		ItemmenuEntity itemmenuEntity = itemmenuEntityFactoryForTest.newItemmenuEntity();
		when(itemmenuPersistenceJPA.load(itemmenu.getId())).thenReturn(itemmenuEntity);
		
		ItemmenuEntity itemmenuEntitySaved = itemmenuEntityFactoryForTest.newItemmenuEntity();
		when(itemmenuPersistenceJPA.save(itemmenuEntity)).thenReturn(itemmenuEntitySaved);
		
		Itemmenu itemmenuSaved = itemmenuFactoryForTest.newItemmenu();
		when(itemmenuServiceMapper.mapItemmenuEntityToItemmenu(itemmenuEntitySaved)).thenReturn(itemmenuSaved);

		// When
		Itemmenu itemmenuResult = itemmenuService.update(itemmenu);

		// Then
		verify(itemmenuServiceMapper).mapItemmenuToItemmenuEntity(itemmenu, itemmenuEntity);
		assertTrue(itemmenuResult == itemmenuSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		itemmenuService.delete(id);

		// Then
		verify(itemmenuPersistenceJPA).delete(id);
		
	}

}
