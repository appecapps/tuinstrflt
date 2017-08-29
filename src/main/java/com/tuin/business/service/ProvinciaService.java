/*
 * Created on 28 ago 2017 ( Time 17:51:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service;

import java.util.List;

import com.tuin.bean.Provincia;

/**
 * Business Service Interface for entity Provincia.
 */
public interface ProvinciaService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Provincia findById( Long id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Provincia> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Provincia save(Provincia entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Provincia update(Provincia entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Provincia create(Provincia entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Long id );


}