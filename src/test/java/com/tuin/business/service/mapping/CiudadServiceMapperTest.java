/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
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
import com.tuin.bean.Ciudad;
import com.tuin.bean.jpa.CiudadEntity;
import com.tuin.bean.jpa.ProvinciaEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class CiudadServiceMapperTest {

	private CiudadServiceMapper ciudadServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		ciudadServiceMapper = new CiudadServiceMapper();
		ciudadServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'CiudadEntity' to 'Ciudad'
	 * @param ciudadEntity
	 */
	@Test
	public void testMapCiudadEntityToCiudad() {
		// Given
		CiudadEntity ciudadEntity = new CiudadEntity();
		ciudadEntity.setNombre(mockValues.nextString(45));
		ciudadEntity.setActivo(mockValues.nextByte());
		ciudadEntity.setCosto(mockValues.nextBigDecimal());
		ciudadEntity.setProvincia(new ProvinciaEntity());
		ciudadEntity.getProvincia().setId(mockValues.nextLong());
		
		// When
		Ciudad ciudad = ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntity);
		
		// Then
		assertEquals(ciudadEntity.getNombre(), ciudad.getNombre());
		assertEquals(ciudadEntity.getActivo(), ciudad.getActivo());
		assertEquals(ciudadEntity.getCosto(), ciudad.getCosto());
		assertEquals(ciudadEntity.getProvincia().getId(), ciudad.getProvinciaid());
	}
	
	/**
	 * Test : Mapping from 'Ciudad' to 'CiudadEntity'
	 */
	@Test
	public void testMapCiudadToCiudadEntity() {
		// Given
		Ciudad ciudad = new Ciudad();
		ciudad.setNombre(mockValues.nextString(45));
		ciudad.setActivo(mockValues.nextByte());
		ciudad.setCosto(mockValues.nextBigDecimal());
		ciudad.setProvinciaid(mockValues.nextLong());

		CiudadEntity ciudadEntity = new CiudadEntity();
		
		// When
		ciudadServiceMapper.mapCiudadToCiudadEntity(ciudad, ciudadEntity);
		
		// Then
		assertEquals(ciudad.getNombre(), ciudadEntity.getNombre());
		assertEquals(ciudad.getActivo(), ciudadEntity.getActivo());
		assertEquals(ciudad.getCosto(), ciudadEntity.getCosto());
		assertEquals(ciudad.getProvinciaid(), ciudadEntity.getProvincia().getId());
	}

}