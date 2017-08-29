/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.test.persistence;


import com.tuin.bean.jpa.RolEntity;
import com.tuin.mock.RolEntityMock;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.RolPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Rol persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class RolPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		RolPersistence service = PersistenceServiceProvider.getService(RolPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Rol persistence : delete + load ..." );
		
		RolPersistence service = PersistenceServiceProvider.getService(RolPersistence.class);
		
		RolEntityMock mock = new RolEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, (long)0  );
		// process( service, mock, ... );
	}

	private void process(RolPersistence service, RolEntityMock mock, Long id ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		RolEntity entity = service.load( id );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( id ) ;
			Assert.assertNotNull(entity);

			// No reference : insert is possible 
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );

			System.out.println(" . delete : " );
			boolean deleted = service.delete( id );
			System.out.println("   deleted = " + deleted);
			Assert.assertTrue(deleted) ;
		}		
	}
}
