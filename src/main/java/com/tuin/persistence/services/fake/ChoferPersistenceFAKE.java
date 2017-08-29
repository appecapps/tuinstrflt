/*
 * Created on 28 ago 2017 ( Time 17:50:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ChoferEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.ChoferPersistence;

/**
 * Fake persistence service implementation ( entity "Chofer" )
 *
 * @author Telosys Tools Generator
 */
public class ChoferPersistenceFAKE extends GenericFakeService<ChoferEntity> implements ChoferPersistence {

	public ChoferPersistenceFAKE () {
		super(ChoferEntity.class);
	}
	
	protected ChoferEntity buildEntity(int index) {
		ChoferEntity entity = new ChoferEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setPrioridad( nextInteger() ) ;
		entity.setTipolicencia( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(ChoferEntity entity) {
		log("delete ( ChoferEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ChoferEntity entity) {
		log("insert ( ChoferEntity : " + entity + ")" ) ;
	}

	public ChoferEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ChoferEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ChoferEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ChoferEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ChoferEntity save(ChoferEntity entity) {
		log("insert ( ChoferEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ChoferEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}