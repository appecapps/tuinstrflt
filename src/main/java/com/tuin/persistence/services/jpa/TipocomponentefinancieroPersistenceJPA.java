/*
 * Created on 28 ago 2017 ( Time 17:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.tuin.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tuin.bean.jpa.TipocomponentefinancieroEntity;
import com.tuin.persistence.commons.jpa.GenericJpaService;
import com.tuin.persistence.commons.jpa.JpaOperation;
import com.tuin.persistence.services.TipocomponentefinancieroPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Tipocomponentefinanciero" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class TipocomponentefinancieroPersistenceJPA extends GenericJpaService<TipocomponentefinancieroEntity, Long> implements TipocomponentefinancieroPersistence {

	/**
	 * Constructor
	 */
	public TipocomponentefinancieroPersistenceJPA() {
		super(TipocomponentefinancieroEntity.class);
	}

	@Override
	public TipocomponentefinancieroEntity load( Long id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Long id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(TipocomponentefinancieroEntity entity) {
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
				Query query = em.createNamedQuery("TipocomponentefinancieroEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
