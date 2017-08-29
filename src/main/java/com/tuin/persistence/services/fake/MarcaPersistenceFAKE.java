/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.MarcaEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.MarcaPersistence;

/**
 * Fake persistence service implementation ( entity "Marca" )
 *
 * @author Telosys Tools Generator
 */
public class MarcaPersistenceFAKE extends GenericFakeService<MarcaEntity> implements MarcaPersistence {

	public MarcaPersistenceFAKE () {
		super(MarcaEntity.class);
	}
	
	protected MarcaEntity buildEntity(int index) {
		MarcaEntity entity = new MarcaEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setNombre( nextString() ) ;
		entity.setActivo( nextByte() ) ;
		return entity ;
	}
	
	
	public boolean delete(MarcaEntity entity) {
		log("delete ( MarcaEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(MarcaEntity entity) {
		log("insert ( MarcaEntity : " + entity + ")" ) ;
	}

	public MarcaEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<MarcaEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<MarcaEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<MarcaEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public MarcaEntity save(MarcaEntity entity) {
		log("insert ( MarcaEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<MarcaEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
