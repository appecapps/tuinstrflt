
/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.ModeloEntity;
import com.tuin.mock.tool.MockValues;

public class ModeloEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public ModeloEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public ModeloEntity createInstance( Long id ) {
		ModeloEntity entity = new ModeloEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNombre( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setActivo( mockValues.nextByte() ) ; // java.lang.Byte 
		// Init Link fields (if any)
		// setMarca( TODO ) ; // Marca 
		// setTipovehiculo( TODO ) ; // Tipovehiculo 
		// setListOfVehiculo( TODO ) ; // List<Vehiculo> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<ModeloEntity> createList(int count) {
		List<ModeloEntity> list = new LinkedList<ModeloEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
