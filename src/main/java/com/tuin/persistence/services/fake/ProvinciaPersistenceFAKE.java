/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ProvinciaEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.ProvinciaPersistence;

/**
 * Fake persistence service implementation ( entity "Provincia" )
 *
 * @author Telosys Tools Generator
 */
public class ProvinciaPersistenceFAKE extends GenericFakeService<ProvinciaEntity> implements ProvinciaPersistence {

	public ProvinciaPersistenceFAKE () {
		super(ProvinciaEntity.class);
	}
	
	protected ProvinciaEntity buildEntity(int index) {
		ProvinciaEntity entity = new ProvinciaEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(ProvinciaEntity entity) {
		log("delete ( ProvinciaEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ProvinciaEntity entity) {
		log("insert ( ProvinciaEntity : " + entity + ")" ) ;
	}

	public ProvinciaEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ProvinciaEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ProvinciaEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ProvinciaEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ProvinciaEntity save(ProvinciaEntity entity) {
		log("insert ( ProvinciaEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ProvinciaEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}