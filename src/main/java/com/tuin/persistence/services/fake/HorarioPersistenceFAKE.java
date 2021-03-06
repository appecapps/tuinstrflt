/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.HorarioEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.HorarioPersistence;

/**
 * Fake persistence service implementation ( entity "Horario" )
 *
 * @author Telosys Tools Generator
 */
public class HorarioPersistenceFAKE extends GenericFakeService<HorarioEntity> implements HorarioPersistence {

	public HorarioPersistenceFAKE () {
		super(HorarioEntity.class);
	}
	
	protected HorarioEntity buildEntity(int index) {
		HorarioEntity entity = new HorarioEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		entity.setFecha( nextDate() ) ;
		entity.setHora( nextDate() ) ;
		entity.setActivo( nextByte() ) ;
		return entity ;
	}
	
	
	public boolean delete(HorarioEntity entity) {
		log("delete ( HorarioEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(HorarioEntity entity) {
		log("insert ( HorarioEntity : " + entity + ")" ) ;
	}

	public HorarioEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<HorarioEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<HorarioEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<HorarioEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public HorarioEntity save(HorarioEntity entity) {
		log("insert ( HorarioEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<HorarioEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
