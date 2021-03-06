/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.tuin.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tuin.bean.jpa.HorarioEntity;
import com.tuin.persistence.commons.jpa.GenericJpaService;
import com.tuin.persistence.commons.jpa.JpaOperation;
import com.tuin.persistence.services.HorarioPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Horario" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class HorarioPersistenceJPA extends GenericJpaService<HorarioEntity, Long> implements HorarioPersistence {

	/**
	 * Constructor
	 */
	public HorarioPersistenceJPA() {
		super(HorarioEntity.class);
	}

	@Override
	public HorarioEntity load( Long id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Long id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(HorarioEntity entity) {
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
				Query query = em.createNamedQuery("HorarioEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
