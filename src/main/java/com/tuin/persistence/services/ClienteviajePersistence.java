/*
 * Created on 28 ago 2017 ( Time 17:50:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ClienteviajeEntity;

/**
 * Basic persistence operations for entity "Clienteviaje"
 * 
 * This Bean has a basic Primary Key : Long
 *
 * @author Telosys Tools Generator
 *
 */
public interface ClienteviajePersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param clienteviaje
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(ClienteviajeEntity clienteviaje) ;

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
	 * @param clienteviaje
	 */
	public void insert(ClienteviajeEntity clienteviaje) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param id
	 * @return the entity loaded (or null if not found)
	 */
	public ClienteviajeEntity load(Long id) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<ClienteviajeEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<ClienteviajeEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<ClienteviajeEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param clienteviaje
	 * @return
	 */
	public ClienteviajeEntity save(ClienteviajeEntity clienteviaje) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<ClienteviajeEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}
