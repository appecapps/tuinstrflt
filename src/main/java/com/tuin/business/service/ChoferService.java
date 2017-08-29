/*
 * Created on 28 ago 2017 ( Time 17:51:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service;

import java.util.List;

import com.tuin.bean.Chofer;

/**
 * Business Service Interface for entity Chofer.
 */
public interface ChoferService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Chofer findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Chofer> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Chofer save(Chofer entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Chofer update(Chofer entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Chofer create(Chofer entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}