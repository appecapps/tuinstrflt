/*
 * Created on 28 ago 2017 ( Time 17:51:18 )
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
import com.tuin.bean.Rolitemmenu;
import com.tuin.bean.jpa.RolitemmenuEntity;
import com.tuin.bean.jpa.ItemmenuEntity;
import com.tuin.bean.jpa.RolEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class RolitemmenuServiceMapperTest {

	private RolitemmenuServiceMapper rolitemmenuServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		rolitemmenuServiceMapper = new RolitemmenuServiceMapper();
		rolitemmenuServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'RolitemmenuEntity' to 'Rolitemmenu'
	 * @param rolitemmenuEntity
	 */
	@Test
	public void testMapRolitemmenuEntityToRolitemmenu() {
		// Given
		RolitemmenuEntity rolitemmenuEntity = new RolitemmenuEntity();
		rolitemmenuEntity.setActivo(mockValues.nextByte());
		rolitemmenuEntity.setItemmenu(new ItemmenuEntity());
		rolitemmenuEntity.getItemmenu().setId(mockValues.nextLong());
		rolitemmenuEntity.setRol(new RolEntity());
		rolitemmenuEntity.getRol().setId(mockValues.nextLong());
		
		// When
		Rolitemmenu rolitemmenu = rolitemmenuServiceMapper.mapRolitemmenuEntityToRolitemmenu(rolitemmenuEntity);
		
		// Then
		assertEquals(rolitemmenuEntity.getActivo(), rolitemmenu.getActivo());
		assertEquals(rolitemmenuEntity.getItemmenu().getId(), rolitemmenu.getItemmenuid());
		assertEquals(rolitemmenuEntity.getRol().getId(), rolitemmenu.getRolid());
	}
	
	/**
	 * Test : Mapping from 'Rolitemmenu' to 'RolitemmenuEntity'
	 */
	@Test
	public void testMapRolitemmenuToRolitemmenuEntity() {
		// Given
		Rolitemmenu rolitemmenu = new Rolitemmenu();
		rolitemmenu.setActivo(mockValues.nextByte());
		rolitemmenu.setItemmenuid(mockValues.nextLong());
		rolitemmenu.setRolid(mockValues.nextLong());

		RolitemmenuEntity rolitemmenuEntity = new RolitemmenuEntity();
		
		// When
		rolitemmenuServiceMapper.mapRolitemmenuToRolitemmenuEntity(rolitemmenu, rolitemmenuEntity);
		
		// Then
		assertEquals(rolitemmenu.getActivo(), rolitemmenuEntity.getActivo());
		assertEquals(rolitemmenu.getItemmenuid(), rolitemmenuEntity.getItemmenu().getId());
		assertEquals(rolitemmenu.getRolid(), rolitemmenuEntity.getRol().getId());
	}

}