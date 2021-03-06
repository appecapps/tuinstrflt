/*
 * Created on 28 ago 2017 ( Time 17:51:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Rol;
import com.tuin.bean.jpa.RolEntity;
import java.util.List;
import com.tuin.business.service.mapping.RolServiceMapper;
import com.tuin.persistence.services.jpa.RolPersistenceJPA;
import com.tuin.test.RolFactoryForTest;
import com.tuin.test.RolEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of RolService
 */
@RunWith(MockitoJUnitRunner.class)
public class RolServiceImplTest {

	@InjectMocks
	private RolServiceImpl rolService;
	@Mock
	private RolPersistenceJPA rolPersistenceJPA;
	@Mock
	private RolServiceMapper rolServiceMapper;
	
	private RolFactoryForTest rolFactoryForTest = new RolFactoryForTest();

	private RolEntityFactoryForTest rolEntityFactoryForTest = new RolEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		RolEntity rolEntity = rolPersistenceJPA.load(id);
		
		Rol rol = rolFactoryForTest.newRol();
		when(rolServiceMapper.mapRolEntityToRol(rolEntity)).thenReturn(rol);

		// When
		Rol rolFound = rolService.findById(id);

		// Then
		assertEquals(rol.getId(),rolFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<RolEntity> rolEntitys = new ArrayList<RolEntity>();
		RolEntity rolEntity1 = rolEntityFactoryForTest.newRolEntity();
		rolEntitys.add(rolEntity1);
		RolEntity rolEntity2 = rolEntityFactoryForTest.newRolEntity();
		rolEntitys.add(rolEntity2);
		when(rolPersistenceJPA.loadAll()).thenReturn(rolEntitys);
		
		Rol rol1 = rolFactoryForTest.newRol();
		when(rolServiceMapper.mapRolEntityToRol(rolEntity1)).thenReturn(rol1);
		Rol rol2 = rolFactoryForTest.newRol();
		when(rolServiceMapper.mapRolEntityToRol(rolEntity2)).thenReturn(rol2);

		// When
		List<Rol> rolsFounds = rolService.findAll();

		// Then
		assertTrue(rol1 == rolsFounds.get(0));
		assertTrue(rol2 == rolsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Rol rol = rolFactoryForTest.newRol();

		RolEntity rolEntity = rolEntityFactoryForTest.newRolEntity();
		when(rolPersistenceJPA.load(rol.getId())).thenReturn(null);
		
		rolEntity = new RolEntity();
		rolServiceMapper.mapRolToRolEntity(rol, rolEntity);
		RolEntity rolEntitySaved = rolPersistenceJPA.save(rolEntity);
		
		Rol rolSaved = rolFactoryForTest.newRol();
		when(rolServiceMapper.mapRolEntityToRol(rolEntitySaved)).thenReturn(rolSaved);

		// When
		Rol rolResult = rolService.create(rol);

		// Then
		assertTrue(rolResult == rolSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Rol rol = rolFactoryForTest.newRol();

		RolEntity rolEntity = rolEntityFactoryForTest.newRolEntity();
		when(rolPersistenceJPA.load(rol.getId())).thenReturn(rolEntity);

		// When
		Exception exception = null;
		try {
			rolService.create(rol);
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
		Rol rol = rolFactoryForTest.newRol();

		RolEntity rolEntity = rolEntityFactoryForTest.newRolEntity();
		when(rolPersistenceJPA.load(rol.getId())).thenReturn(rolEntity);
		
		RolEntity rolEntitySaved = rolEntityFactoryForTest.newRolEntity();
		when(rolPersistenceJPA.save(rolEntity)).thenReturn(rolEntitySaved);
		
		Rol rolSaved = rolFactoryForTest.newRol();
		when(rolServiceMapper.mapRolEntityToRol(rolEntitySaved)).thenReturn(rolSaved);

		// When
		Rol rolResult = rolService.update(rol);

		// Then
		verify(rolServiceMapper).mapRolToRolEntity(rol, rolEntity);
		assertTrue(rolResult == rolSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		rolService.delete(id);

		// Then
		verify(rolPersistenceJPA).delete(id);
		
	}

}
