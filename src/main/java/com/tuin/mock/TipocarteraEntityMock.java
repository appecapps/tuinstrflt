
/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.TipocarteraEntity;
import com.tuin.mock.tool.MockValues;

public class TipocarteraEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TipocarteraEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TipocarteraEntity createInstance( Long id ) {
		TipocarteraEntity entity = new TipocarteraEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNombre( mockValues.nextString(45) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfTipodocumento( TODO ) ; // List<Tipodocumento> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TipocarteraEntity> createList(int count) {
		List<TipocarteraEntity> list = new LinkedList<TipocarteraEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}