/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ModeloEntity;

/**
 * Basic persistence operations for entity "Modelo"
 * 
 * This Bean has a basic Primary Key : Long
 *
 * @author Telosys Tools Generator
 *
 */
public interface ModeloPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param modelo
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(ModeloEntity modelo) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param id
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Long id) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param modelo
	 */
	public void insert(ModeloEntity modelo) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param id
	 * @return the entity loaded (or null if not found)
	 */
	public ModeloEntity load(Long id) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<ModeloEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<ModeloEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<ModeloEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param modelo
	 * @return
	 */
	public ModeloEntity save(ModeloEntity modelo) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<ModeloEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}