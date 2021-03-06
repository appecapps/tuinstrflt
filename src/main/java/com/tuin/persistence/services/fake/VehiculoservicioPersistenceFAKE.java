/*
 * Created on 28 ago 2017 ( Time 17:51:03 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.VehiculoservicioEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.VehiculoservicioPersistence;

/**
 * Fake persistence service implementation ( entity "Vehiculoservicio" )
 *
 * @author Telosys Tools Generator
 */
public class VehiculoservicioPersistenceFAKE extends GenericFakeService<VehiculoservicioEntity> implements VehiculoservicioPersistence {

	public VehiculoservicioPersistenceFAKE () {
		super(VehiculoservicioEntity.class);
	}
	
	protected VehiculoservicioEntity buildEntity(int index) {
		VehiculoservicioEntity entity = new VehiculoservicioEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		return entity ;
	}
	
	
	public boolean delete(VehiculoservicioEntity entity) {
		log("delete ( VehiculoservicioEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(VehiculoservicioEntity entity) {
		log("insert ( VehiculoservicioEntity : " + entity + ")" ) ;
	}

	public VehiculoservicioEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<VehiculoservicioEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<VehiculoservicioEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<VehiculoservicioEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public VehiculoservicioEntity save(VehiculoservicioEntity entity) {
		log("insert ( VehiculoservicioEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<VehiculoservicioEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
