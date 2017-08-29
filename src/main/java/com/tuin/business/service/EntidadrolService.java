/*
 * Created on 28 ago 2017 ( Time 17:51:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service;

import java.util.List;

import com.tuin.bean.Entidadrol;

/**
 * Business Service Interface for entity Entidadrol.
 */
public interface EntidadrolService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Entidadrol findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Entidadrol> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Entidadrol save(Entidadrol entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Entidadrol update(Entidadrol entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Entidadrol create(Entidadrol entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}
