
/*
 * Created on 28 ago 2017 ( Time 17:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.TipocomponentefinancieroEntity;
import com.tuin.mock.tool.MockValues;

public class TipocomponentefinancieroEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TipocomponentefinancieroEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TipocomponentefinancieroEntity createInstance( Long id ) {
		TipocomponentefinancieroEntity entity = new TipocomponentefinancieroEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNombre( mockValues.nextString(45) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfComponentefinanciero( TODO ) ; // List<Componentefinanciero> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TipocomponentefinancieroEntity> createList(int count) {
		List<TipocomponentefinancieroEntity> list = new LinkedList<TipocomponentefinancieroEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
