/*
 * Created on 28 ago 2017 ( Time 17:51:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service;

import java.util.List;

import com.tuin.bean.Marca;

/**
 * Business Service Interface for entity Marca.
 */
public interface MarcaService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Marca findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Marca> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Marca save(Marca entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Marca update(Marca entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Marca create(Marca entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}
