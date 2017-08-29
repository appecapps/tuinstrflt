/*
 * Created on 28 ago 2017 ( Time 17:50:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.FormapagoEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.FormapagoPersistence;

/**
 * Fake persistence service implementation ( entity "Formapago" )
 *
 * @author Telosys Tools Generator
 */
public class FormapagoPersistenceFAKE extends GenericFakeService<FormapagoEntity> implements FormapagoPersistence {

	public FormapagoPersistenceFAKE () {
		super(FormapagoEntity.class);
	}
	
	protected FormapagoEntity buildEntity(int index) {
		FormapagoEntity entity = new FormapagoEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		entity.setActivo( nextByte() ) ;
		return entity ;
	}
	
	
	public boolean delete(FormapagoEntity entity) {
		log("delete ( FormapagoEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(FormapagoEntity entity) {
		log("insert ( FormapagoEntity : " + entity + ")" ) ;
	}

	public FormapagoEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<FormapagoEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<FormapagoEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<FormapagoEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public FormapagoEntity save(FormapagoEntity entity) {
		log("insert ( FormapagoEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<FormapagoEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
