/*
 * Created on 28 ago 2017 ( Time 17:50:58 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.EntidadrolEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.EntidadrolPersistence;

/**
 * Fake persistence service implementation ( entity "Entidadrol" )
 *
 * @author Telosys Tools Generator
 */
public class EntidadrolPersistenceFAKE extends GenericFakeService<EntidadrolEntity> implements EntidadrolPersistence {

	public EntidadrolPersistenceFAKE () {
		super(EntidadrolEntity.class);
	}
	
	protected EntidadrolEntity buildEntity(int index) {
		EntidadrolEntity entity = new EntidadrolEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		return entity ;
	}
	
	
	public boolean delete(EntidadrolEntity entity) {
		log("delete ( EntidadrolEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(EntidadrolEntity entity) {
		log("insert ( EntidadrolEntity : " + entity + ")" ) ;
	}

	public EntidadrolEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<EntidadrolEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<EntidadrolEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<EntidadrolEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public EntidadrolEntity save(EntidadrolEntity entity) {
		log("insert ( EntidadrolEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<EntidadrolEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
