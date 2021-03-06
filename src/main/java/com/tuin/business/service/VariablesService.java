/*
 * Created on 28 ago 2017 ( Time 17:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service;

import java.util.List;

import com.tuin.bean.Variables;

/**
 * Business Service Interface for entity Variables.
 */
public interface VariablesService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Variables findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Variables> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Variables save(Variables entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Variables update(Variables entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Variables create(Variables entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}
