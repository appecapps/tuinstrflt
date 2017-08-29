/*
 * Created on 28 ago 2017 ( Time 17:51:15 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Entidadrol;
import com.tuin.bean.jpa.EntidadrolEntity;
import com.tuin.bean.jpa.RolEntity;
import com.tuin.bean.jpa.EntidadEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class EntidadrolServiceMapperTest {

	private EntidadrolServiceMapper entidadrolServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		entidadrolServiceMapper = new EntidadrolServiceMapper();
		entidadrolServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'EntidadrolEntity' to 'Entidadrol'
	 * @param entidadrolEntity
	 */
	@Test
	public void testMapEntidadrolEntityToEntidadrol() {
		// Given
		EntidadrolEntity entidadrolEntity = new EntidadrolEntity();
		entidadrolEntity.setRol(new RolEntity());
		entidadrolEntity.getRol().setId(mockValues.nextLong());
		entidadrolEntity.setEntidad(new EntidadEntity());
		entidadrolEntity.getEntidad().setId(mockValues.nextLong());
		
		// When
		Entidadrol entidadrol = entidadrolServiceMapper.mapEntidadrolEntityToEntidadrol(entidadrolEntity);
		
		// Then
		assertEquals(entidadrolEntity.getRol().getId(), entidadrol.getRolid());
		assertEquals(entidadrolEntity.getEntidad().getId(), entidadrol.getEntidadid());
	}
	
	/**
	 * Test : Mapping from 'Entidadrol' to 'EntidadrolEntity'
	 */
	@Test
	public void testMapEntidadrolToEntidadrolEntity() {
		// Given
		Entidadrol entidadrol = new Entidadrol();
		entidadrol.setRolid(mockValues.nextLong());
		entidadrol.setEntidadid(mockValues.nextLong());

		EntidadrolEntity entidadrolEntity = new EntidadrolEntity();
		
		// When
		entidadrolServiceMapper.mapEntidadrolToEntidadrolEntity(entidadrol, entidadrolEntity);
		
		// Then
		assertEquals(entidadrol.getRolid(), entidadrolEntity.getRol().getId());
		assertEquals(entidadrol.getEntidadid(), entidadrolEntity.getEntidad().getId());
	}

}