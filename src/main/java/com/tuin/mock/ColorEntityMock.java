
/*
 * Created on 28 ago 2017 ( Time 17:50:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.ColorEntity;
import com.tuin.mock.tool.MockValues;

public class ColorEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public ColorEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public ColorEntity createInstance( Long id ) {
		ColorEntity entity = new ColorEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNombre( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setActivo( mockValues.nextByte() ) ; // java.lang.Byte 
		// Init Link fields (if any)
		// setListOfVehiculo( TODO ) ; // List<Vehiculo> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<ColorEntity> createList(int count) {
		List<ColorEntity> list = new LinkedList<ColorEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
