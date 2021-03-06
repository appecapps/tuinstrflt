/*
 * Created on 28 ago 2017 ( Time 17:50:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ClienteEntity;
import com.tuin.persistence.commons.fake.GenericFakeService;
import com.tuin.persistence.services.ClientePersistence;

/**
 * Fake persistence service implementation ( entity "Cliente" )
 *
 * @author Telosys Tools Generator
 */
public class ClientePersistenceFAKE extends GenericFakeService<ClienteEntity> implements ClientePersistence {

	public ClientePersistenceFAKE () {
		super(ClienteEntity.class);
	}
	
	protected ClienteEntity buildEntity(int index) {
		ClienteEntity entity = new ClienteEntity();
		// Init fields with mock values
		entity.setId( nextLong() ) ;
		return entity ;
	}
	
	
	public boolean delete(ClienteEntity entity) {
		log("delete ( ClienteEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Long id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ClienteEntity entity) {
		log("insert ( ClienteEntity : " + entity + ")" ) ;
	}

	public ClienteEntity load( Long id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ClienteEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ClienteEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ClienteEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ClienteEntity save(ClienteEntity entity) {
		log("insert ( ClienteEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ClienteEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
