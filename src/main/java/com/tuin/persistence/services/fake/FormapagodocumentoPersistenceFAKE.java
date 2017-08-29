/*
 * Created on 28 ago 2017 ( Time 17:50:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.FormapagodocumentoEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.FormapagodocumentoPersistence;

/**
 * Fake persistence service implementation ( entity "Formapagodocumento" )
 *
 * @author Telosys Tools Generator
 */
public class FormapagodocumentoPersistenceFAKE extends GenericFakeService<FormapagodocumentoEntity> implements FormapagodocumentoPersistence {

	public FormapagodocumentoPersistenceFAKE () {
		super(FormapagodocumentoEntity.class);
	}
	
	protected FormapagodocumentoEntity buildEntity(int index) {
		FormapagodocumentoEntity entity = new FormapagodocumentoEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		return entity ;
	}
	
	
	public boolean delete(FormapagodocumentoEntity entity) {
		log("delete ( FormapagodocumentoEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(FormapagodocumentoEntity entity) {
		log("insert ( FormapagodocumentoEntity : " + entity + ")" ) ;
	}

	public FormapagodocumentoEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<FormapagodocumentoEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<FormapagodocumentoEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<FormapagodocumentoEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public FormapagodocumentoEntity save(FormapagodocumentoEntity entity) {
		log("insert ( FormapagodocumentoEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<FormapagodocumentoEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}