'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('vehiculo.module'));
  
  describe('Vehiculo', function(){
	var $httpBackend, Vehiculo, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Vehiculo = $injector.get('Vehiculo');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/vehiculo').respond("test");
    	Vehiculo.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/vehiculo').respond("test");
    	Vehiculo.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/vehiculo/1').respond("test");
    	Vehiculo.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Vehiculo.create({id:null,name:'vehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('vehiculo.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/vehiculo').respond("test");
    	Vehiculo.create({id:'1',name:'vehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Vehiculo.update({id:null,name:'vehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('vehiculo.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/vehiculo/1').respond("test");
    	Vehiculo.update({id:'1',name:'vehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/vehiculo/1').respond("test");
    	Vehiculo.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});