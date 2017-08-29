'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('vehiculoservicio.module'));
  
  describe('Vehiculoservicio', function(){
	var $httpBackend, Vehiculoservicio, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Vehiculoservicio = $injector.get('Vehiculoservicio');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/vehiculoservicio').respond("test");
    	Vehiculoservicio.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/vehiculoservicio').respond("test");
    	Vehiculoservicio.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/vehiculoservicio/1').respond("test");
    	Vehiculoservicio.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Vehiculoservicio.create({id:null,name:'vehiculoservicio'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('vehiculoservicio.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/vehiculoservicio').respond("test");
    	Vehiculoservicio.create({id:'1',name:'vehiculoservicio'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Vehiculoservicio.update({id:null,name:'vehiculoservicio'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('vehiculoservicio.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/vehiculoservicio/1').respond("test");
    	Vehiculoservicio.update({id:'1',name:'vehiculoservicio'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/vehiculoservicio/1').respond("test");
    	Vehiculoservicio.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});