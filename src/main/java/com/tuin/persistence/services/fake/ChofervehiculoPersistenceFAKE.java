/*
 * Created on 28 ago 2017 ( Time 17:50:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ChofervehiculoEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.ChofervehiculoPersistence;

/**
 * Fake persistence service implementation ( entity "Chofervehiculo" )
 *
 * @author Telosys Tools Generator
 */
public class ChofervehiculoPersistenceFAKE extends GenericFakeService<ChofervehiculoEntity> implements ChofervehiculoPersistence {

	public ChofervehiculoPersistenceFAKE () {
		super(ChofervehiculoEntity.class);
	}
	
	protected ChofervehiculoEntity buildEntity(int index) {
		ChofervehiculoEntity entity = new ChofervehiculoEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setPredeterminado( nextByte() ) ;
		return entity ;
	}
	
	
	public boolean delete(ChofervehiculoEntity entity) {
		log("delete ( ChofervehiculoEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ChofervehiculoEntity entity) {
		log("insert ( ChofervehiculoEntity : " + entity + ")" ) ;
	}

	public ChofervehiculoEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ChofervehiculoEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ChofervehiculoEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ChofervehiculoEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ChofervehiculoEntity save(ChofervehiculoEntity entity) {
		log("insert ( ChofervehiculoEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ChofervehiculoEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}