/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ModeloEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.ModeloPersistence;

/**
 * Fake persistence service implementation ( entity "Modelo" )
 *
 * @author Telosys Tools Generator
 */
public class ModeloPersistenceFAKE extends GenericFakeService<ModeloEntity> implements ModeloPersistence {

	public ModeloPersistenceFAKE () {
		super(ModeloEntity.class);
	}
	
	protected ModeloEntity buildEntity(int index) {
		ModeloEntity entity = new ModeloEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		entity.setActivo( nextByte() ) ;
		return entity ;
	}
	
	
	public boolean delete(ModeloEntity entity) {
		log("delete ( ModeloEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ModeloEntity entity) {
		log("insert ( ModeloEntity : " + entity + ")" ) ;
	}

	public ModeloEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ModeloEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ModeloEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ModeloEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ModeloEntity save(ModeloEntity entity) {
		log("insert ( ModeloEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ModeloEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
