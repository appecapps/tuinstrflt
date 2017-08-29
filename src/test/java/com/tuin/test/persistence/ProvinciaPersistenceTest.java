/*
 * Created on 28 ago 2017 ( Time 17:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.test.persistence;


import com.tuin.bean.jpa.ProvinciaEntity;
import com.tuin.mock.ProvinciaEntityMock;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.ProvinciaPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Provincia persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class ProvinciaPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		ProvinciaPersistence service = PersistenceServiceProvider.getService(ProvinciaPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Provincia persistence : delete + load ..." );
		
		ProvinciaPersistence service = PersistenceServiceProvider.getService(ProvinciaPersistence.class);
		
		ProvinciaEntityMock mock = new ProvinciaEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, (long)0  );
		// process( service, mock, ... );
	}

	private void process(ProvinciaPersistence service, ProvinciaEntityMock mock, Long id ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		ProvinciaEntity entity = service.load( id );
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