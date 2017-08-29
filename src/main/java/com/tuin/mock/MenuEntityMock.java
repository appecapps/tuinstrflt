
/*
 * Created on 28 ago 2017 ( Time 17:51:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.mock;

import java.util.LinkedList;
import java.util.List;

import com.tuin.bean.jpa.MenuEntity;
import com.tuin.mock.tool.MockValues;

public class MenuEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public MenuEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextLong() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public MenuEntity createInstance( Long id ) {
		MenuEntity entity = new MenuEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNombre( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setOrden( mockValues.nextString(45) ) ; // java.lang.String 
		entity.setActivo( mockValues.nextByte() ) ; // java.lang.Byte 
		// Init Link fields (if any)
		// setListOfItemmenu( TODO ) ; // List<Itemmenu> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<MenuEntity> createList(int count) {
		List<MenuEntity> list = new LinkedList<MenuEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
