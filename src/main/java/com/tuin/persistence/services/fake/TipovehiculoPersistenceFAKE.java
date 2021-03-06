/*
 * Created on 28 ago 2017 ( Time 17:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.TipovehiculoEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.TipovehiculoPersistence;

/**
 * Fake persistence service implementation ( entity "Tipovehiculo" )
 *
 * @author Telosys Tools Generator
 */
public class TipovehiculoPersistenceFAKE extends GenericFakeService<TipovehiculoEntity> implements TipovehiculoPersistence {

	public TipovehiculoPersistenceFAKE () {
		super(TipovehiculoEntity.class);
	}
	
	protected TipovehiculoEntity buildEntity(int index) {
		TipovehiculoEntity entity = new TipovehiculoEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(TipovehiculoEntity entity) {
		log("delete ( TipovehiculoEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(TipovehiculoEntity entity) {
		log("insert ( TipovehiculoEntity : " + entity + ")" ) ;
	}

	public TipovehiculoEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<TipovehiculoEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<TipovehiculoEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<TipovehiculoEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public TipovehiculoEntity save(TipovehiculoEntity entity) {
		log("insert ( TipovehiculoEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<TipovehiculoEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
