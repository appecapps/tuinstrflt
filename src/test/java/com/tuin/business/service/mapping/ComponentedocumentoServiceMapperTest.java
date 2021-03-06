/*
 * Created on 28 ago 2017 ( Time 17:51:14 )
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
import com.tuin.bean.Componentedocumento;
import com.tuin.bean.jpa.ComponentedocumentoEntity;
import com.tuin.bean.jpa.ComponentefinancieroEntity;
import com.tuin.bean.jpa.DocumentoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class ComponentedocumentoServiceMapperTest {

	private ComponentedocumentoServiceMapper componentedocumentoServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		componentedocumentoServiceMapper = new ComponentedocumentoServiceMapper();
		componentedocumentoServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'ComponentedocumentoEntity' to 'Componentedocumento'
	 * @param componentedocumentoEntity
	 */
	@Test
	public void testMapComponentedocumentoEntityToComponentedocumento() {
		// Given
		ComponentedocumentoEntity componentedocumentoEntity = new ComponentedocumentoEntity();
		componentedocumentoEntity.setValor(mockValues.nextBigDecimal());
		componentedocumentoEntity.setComponentefinanciero(new ComponentefinancieroEntity());
		componentedocumentoEntity.getComponentefinanciero().setId(mockValues.nextLong());
		componentedocumentoEntity.setDocumento(new DocumentoEntity());
		componentedocumentoEntity.getDocumento().setId(mockValues.nextLong());
		
		// When
		Componentedocumento componentedocumento = componentedocumentoServiceMapper.mapComponentedocumentoEntityToComponentedocumento(componentedocumentoEntity);
		
		// Then
		assertEquals(componentedocumentoEntity.getValor(), componentedocumento.getValor());
		assertEquals(componentedocumentoEntity.getComponentefinanciero().getId(), componentedocumento.getComponentefinancieroid());
		assertEquals(componentedocumentoEntity.getDocumento().getId(), componentedocumento.getDocumentoid());
	}
	
	/**
	 * Test : Mapping from 'Componentedocumento' to 'ComponentedocumentoEntity'
	 */
	@Test
	public void testMapComponentedocumentoToComponentedocumentoEntity() {
		// Given
		Componentedocumento componentedocumento = new Componentedocumento();
		componentedocumento.setValor(mockValues.nextBigDecimal());
		componentedocumento.setComponentefinancieroid(mockValues.nextLong());
		componentedocumento.setDocumentoid(mockValues.nextLong());

		ComponentedocumentoEntity componentedocumentoEntity = new ComponentedocumentoEntity();
		
		// When
		componentedocumentoServiceMapper.mapComponentedocumentoToComponentedocumentoEntity(componentedocumento, componentedocumentoEntity);
		
		// Then
		assertEquals(componentedocumento.getValor(), componentedocumentoEntity.getValor());
		assertEquals(componentedocumento.getComponentefinancieroid(), componentedocumentoEntity.getComponentefinanciero().getId());
		assertEquals(componentedocumento.getDocumentoid(), componentedocumentoEntity.getDocumento().getId());
	}

}