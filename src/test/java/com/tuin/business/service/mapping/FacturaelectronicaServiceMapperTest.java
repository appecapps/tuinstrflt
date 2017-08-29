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
import com.tuin.bean.Facturaelectronica;
import com.tuin.bean.jpa.FacturaelectronicaEntity;
import com.tuin.bean.jpa.EstadoEntity;
import com.tuin.bean.jpa.DocumentoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class FacturaelectronicaServiceMapperTest {

	private FacturaelectronicaServiceMapper facturaelectronicaServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		facturaelectronicaServiceMapper = new FacturaelectronicaServiceMapper();
		facturaelectronicaServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'FacturaelectronicaEntity' to 'Facturaelectronica'
	 * @param facturaelectronicaEntity
	 */
	@Test
	public void testMapFacturaelectronicaEntityToFacturaelectronica() {
		// Given
		FacturaelectronicaEntity facturaelectronicaEntity = new FacturaelectronicaEntity();
		facturaelectronicaEntity.setClaveacceso(mockValues.nextString(100));
		facturaelectronicaEntity.setRespuesta(mockValues.nextString(200));
		facturaelectronicaEntity.setFechaautorizacion(mockValues.nextDate());
		facturaelectronicaEntity.setEstado(new EstadoEntity());
		facturaelectronicaEntity.getEstado().setId(mockValues.nextLong());
		facturaelectronicaEntity.setDocumento(new DocumentoEntity());
		facturaelectronicaEntity.getDocumento().setId(mockValues.nextLong());
		
		// When
		Facturaelectronica facturaelectronica = facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntity);
		
		// Then
		assertEquals(facturaelectronicaEntity.getClaveacceso(), facturaelectronica.getClaveacceso());
		assertEquals(facturaelectronicaEntity.getRespuesta(), facturaelectronica.getRespuesta());
		assertEquals(facturaelectronicaEntity.getFechaautorizacion(), facturaelectronica.getFechaautorizacion());
		assertEquals(facturaelectronicaEntity.getEstado().getId(), facturaelectronica.getEstadoid());
		assertEquals(facturaelectronicaEntity.getDocumento().getId(), facturaelectronica.getDocumentoid());
	}
	
	/**
	 * Test : Mapping from 'Facturaelectronica' to 'FacturaelectronicaEntity'
	 */
	@Test
	public void testMapFacturaelectronicaToFacturaelectronicaEntity() {
		// Given
		Facturaelectronica facturaelectronica = new Facturaelectronica();
		facturaelectronica.setClaveacceso(mockValues.nextString(100));
		facturaelectronica.setRespuesta(mockValues.nextString(200));
		facturaelectronica.setFechaautorizacion(mockValues.nextDate());
		facturaelectronica.setEstadoid(mockValues.nextLong());
		facturaelectronica.setDocumentoid(mockValues.nextLong());

		FacturaelectronicaEntity facturaelectronicaEntity = new FacturaelectronicaEntity();
		
		// When
		facturaelectronicaServiceMapper.mapFacturaelectronicaToFacturaelectronicaEntity(facturaelectronica, facturaelectronicaEntity);
		
		// Then
		assertEquals(facturaelectronica.getClaveacceso(), facturaelectronicaEntity.getClaveacceso());
		assertEquals(facturaelectronica.getRespuesta(), facturaelectronicaEntity.getRespuesta());
		assertEquals(facturaelectronica.getFechaautorizacion(), facturaelectronicaEntity.getFechaautorizacion());
		assertEquals(facturaelectronica.getEstadoid(), facturaelectronicaEntity.getEstado().getId());
		assertEquals(facturaelectronica.getDocumentoid(), facturaelectronicaEntity.getDocumento().getId());
	}

}