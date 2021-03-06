/*
 * Created on 28 ago 2017 ( Time 17:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.TipocomponentefinancieroEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.TipocomponentefinancieroPersistence;

/**
 * Fake persistence service implementation ( entity "Tipocomponentefinanciero" )
 *
 * @author Telosys Tools Generator
 */
public class TipocomponentefinancieroPersistenceFAKE extends GenericFakeService<TipocomponentefinancieroEntity> implements TipocomponentefinancieroPersistence {

	public TipocomponentefinancieroPersistenceFAKE () {
		super(TipocomponentefinancieroEntity.class);
	}
	
	protected TipocomponentefinancieroEntity buildEntity(int index) {
		TipocomponentefinancieroEntity entity = new TipocomponentefinancieroEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(TipocomponentefinancieroEntity entity) {
		log("delete ( TipocomponentefinancieroEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(TipocomponentefinancieroEntity entity) {
		log("insert ( TipocomponentefinancieroEntity : " + entity + ")" ) ;
	}

	public TipocomponentefinancieroEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<TipocomponentefinancieroEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<TipocomponentefinancieroEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<TipocomponentefinancieroEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public TipocomponentefinancieroEntity save(TipocomponentefinancieroEntity entity) {
		log("insert ( TipocomponentefinancieroEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<TipocomponentefinancieroEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
