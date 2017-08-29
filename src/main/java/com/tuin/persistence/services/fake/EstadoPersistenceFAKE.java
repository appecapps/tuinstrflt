/*
 * Created on 28 ago 2017 ( Time 17:50:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.EstadoEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.EstadoPersistence;

/**
 * Fake persistence service implementation ( entity "Estado" )
 *
 * @author Telosys Tools Generator
 */
public class EstadoPersistenceFAKE extends GenericFakeService<EstadoEntity> implements EstadoPersistence {

	public EstadoPersistenceFAKE () {
		super(EstadoEntity.class);
	}
	
	protected EstadoEntity buildEntity(int index) {
		EstadoEntity entity = new EstadoEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		entity.setTabla( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(EstadoEntity entity) {
		log("delete ( EstadoEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(EstadoEntity entity) {
		log("insert ( EstadoEntity : " + entity + ")" ) ;
	}

	public EstadoEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<EstadoEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<EstadoEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<EstadoEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public EstadoEntity save(EstadoEntity entity) {
		log("insert ( EstadoEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<EstadoEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}