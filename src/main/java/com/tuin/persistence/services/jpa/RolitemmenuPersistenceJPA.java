/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.tuin.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tuin.bean.jpa.RolitemmenuEntity;
import com.tuin.persistence.commons.jpa.GenericJpaService;
import com.tuin.persistence.commons.jpa.JpaOperation;
import com.tuin.persistence.services.RolitemmenuPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Rolitemmenu" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class RolitemmenuPersistenceJPA extends GenericJpaService<RolitemmenuEntity, Long> implements RolitemmenuPersistence {

	/**
	 * Constructor
	 */
	public RolitemmenuPersistenceJPA() {
		super(RolitemmenuEntity.class);
	}

	@Override
	public RolitemmenuEntity load( Long id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Long id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(RolitemmenuEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("RolitemmenuEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
