/*
 * Created on 28 ago 2017 ( Time 17:51:03 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.tuin.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tuin.bean.jpa.ViajeEntity;
import com.tuin.persistence.commons.jpa.GenericJpaService;
import com.tuin.persistence.commons.jpa.JpaOperation;
import com.tuin.persistence.services.ViajePersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Viaje" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class ViajePersistenceJPA extends GenericJpaService<ViajeEntity, Long> implements ViajePersistence {

	/**
	 * Constructor
	 */
	public ViajePersistenceJPA() {
		super(ViajeEntity.class);
	}

	@Override
	public ViajeEntity load( Long id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Long id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(ViajeEntity entity) {
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
				Query query = em.createNamedQuery("ViajeEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
