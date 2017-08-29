
/*
 * Created on 28 ago 2017 ( Time 17:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.VariablesEntity;
import com.tuin.mock.tool.MockValues;

public class VariablesEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public VariablesEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public VariablesEntity createInstance( Long id ) {
		VariablesEntity entity = new VariablesEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setContenido( mockValues.nextString(200) ) ; // java.lang.String 
		entity.setActiva( mockValues.nextByte() ) ; // java.lang.Byte 
		// Init Link fields (if any)
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<VariablesEntity> createList(int count) {
		List<VariablesEntity> list = new LinkedList<VariablesEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
