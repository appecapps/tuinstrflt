/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.persistence.services;

import java.util.List;
import java.util.Map;

import com.tuin.bean.jpa.ItemmenuEntity;

/**
 * Basic persistence operations for entity "Itemmenu"
 * 
 * This Bean has a basic Primary Key : Long
 *
 * @author Telosys Tools Generator
 *
 */
public interface ItemmenuPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param itemmenu
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(ItemmenuEntity itemmenu) ;

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
	 * @param itemmenu
	 */
	public void insert(ItemmenuEntity itemmenu) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param id
	 * @return the entity loaded (or null if not found)
	 */
	public ItemmenuEntity load(Long id) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<ItemmenuEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<ItemmenuEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<ItemmenuEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param itemmenu
	 * @return
	 */
	public ItemmenuEntity save(ItemmenuEntity itemmenu) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<ItemmenuEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}
