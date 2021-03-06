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
import com.tuin.bean.Documento;
import com.tuin.bean.jpa.DocumentoEntity;
import com.tuin.bean.jpa.EstadoEntity;
import com.tuin.bean.jpa.TipodocumentoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class DocumentoServiceMapperTest {

	private DocumentoServiceMapper documentoServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		documentoServiceMapper = new DocumentoServiceMapper();
		documentoServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'DocumentoEntity' to 'Documento'
	 * @param documentoEntity
	 */
	@Test
	public void testMapDocumentoEntityToDocumento() {
		// Given
		DocumentoEntity documentoEntity = new DocumentoEntity();
		documentoEntity.setNumero(mockValues.nextString(45));
		documentoEntity.setDescripcion(mockValues.nextString(45));
		documentoEntity.setValor(mockValues.nextBigDecimal());
		documentoEntity.setOrigenid(mockValues.nextLong());
		documentoEntity.setOrigen(mockValues.nextString(45));
		documentoEntity.setEstado(new EstadoEntity());
		documentoEntity.getEstado().setId(mockValues.nextLong());
		documentoEntity.setTipodocumento(new TipodocumentoEntity());
		documentoEntity.getTipodocumento().setId(mockValues.nextLong());
		
		// When
		Documento documento = documentoServiceMapper.mapDocumentoEntityToDocumento(documentoEntity);
		
		// Then
		assertEquals(documentoEntity.getNumero(), documento.getNumero());
		assertEquals(documentoEntity.getDescripcion(), documento.getDescripcion());
		assertEquals(documentoEntity.getValor(), documento.getValor());
		assertEquals(documentoEntity.getOrigenid(), documento.getOrigenid());
		assertEquals(documentoEntity.getOrigen(), documento.getOrigen());
		assertEquals(documentoEntity.getEstado().getId(), documento.getEstadoid());
		assertEquals(documentoEntity.getTipodocumento().getId(), documento.getTipodocumentoid());
	}
	
	/**
	 * Test : Mapping from 'Documento' to 'DocumentoEntity'
	 */
	@Test
	public void testMapDocumentoToDocumentoEntity() {
		// Given
		Documento documento = new Documento();
		documento.setNumero(mockValues.nextString(45));
		documento.setDescripcion(mockValues.nextString(45));
		documento.setValor(mockValues.nextBigDecimal());
		documento.setOrigenid(mockValues.nextLong());
		documento.setOrigen(mockValues.nextString(45));
		documento.setEstadoid(mockValues.nextLong());
		documento.setTipodocumentoid(mockValues.nextLong());

		DocumentoEntity documentoEntity = new DocumentoEntity();
		
		// When
		documentoServiceMapper.mapDocumentoToDocumentoEntity(documento, documentoEntity);
		
		// Then
		assertEquals(documento.getNumero(), documentoEntity.getNumero());
		assertEquals(documento.getDescripcion(), documentoEntity.getDescripcion());
		assertEquals(documento.getValor(), documentoEntity.getValor());
		assertEquals(documento.getOrigenid(), documentoEntity.getOrigenid());
		assertEquals(documento.getOrigen(), documentoEntity.getOrigen());
		assertEquals(documento.getEstadoid(), documentoEntity.getEstado().getId());
		assertEquals(documento.getTipodocumentoid(), documentoEntity.getTipodocumento().getId());
	}

}