/*
 * Created on 28 ago 2017 ( Time 17:51:17 )
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
import com.tuin.bean.Marca;
import com.tuin.bean.jpa.MarcaEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class MarcaServiceMapperTest {

	private MarcaServiceMapper marcaServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		marcaServiceMapper = new MarcaServiceMapper();
		marcaServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'MarcaEntity' to 'Marca'
	 * @param marcaEntity
	 */
	@Test
	public void testMapMarcaEntityToMarca() {
		// Given
		MarcaEntity marcaEntity = new MarcaEntity();
		marcaEntity.setNombre(mockValues.nextString(45));
		marcaEntity.setActivo(mockValues.nextByte());
		
		// When
		Marca marca = marcaServiceMapper.mapMarcaEntityToMarca(marcaEntity);
		
		// Then
		assertEquals(marcaEntity.getNombre(), marca.getNombre());
		assertEquals(marcaEntity.getActivo(), marca.getActivo());
	}
	
	/**
	 * Test : Mapping from 'Marca' to 'MarcaEntity'
	 */
	@Test
	public void testMapMarcaToMarcaEntity() {
		// Given
		Marca marca = new Marca();
		marca.setNombre(mockValues.nextString(45));
		marca.setActivo(mockValues.nextByte());

		MarcaEntity marcaEntity = new MarcaEntity();
		
		// When
		marcaServiceMapper.mapMarcaToMarcaEntity(marca, marcaEntity);
		
		// Then
		assertEquals(marca.getNombre(), marcaEntity.getNombre());
		assertEquals(marca.getActivo(), marcaEntity.getActivo());
	}

}