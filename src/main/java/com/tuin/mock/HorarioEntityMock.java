
/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.HorarioEntity;
import com.tuin.mock.tool.MockValues;

public class HorarioEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public HorarioEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public HorarioEntity createInstance( Long id ) {
		HorarioEntity entity = new HorarioEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setFecha( mockValues.nextDate() ) ; // java.util.Date 
		entity.setHora( mockValues.nextDate() ) ; // java.util.Date 
		entity.setActivo( mockValues.nextByte() ) ; // java.lang.Byte 
		// Init Link fields (if any)
		// setListOfDestinohorario( TODO ) ; // List<Destinohorario> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<HorarioEntity> createList(int count) {
		List<HorarioEntity> list = new LinkedList<HorarioEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
