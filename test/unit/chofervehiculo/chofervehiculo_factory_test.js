'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('chofervehiculo.module'));
  
  describe('Chofervehiculo', function(){
	var $httpBackend, Chofervehiculo, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Chofervehiculo = $injector.get('Chofervehiculo');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/chofervehiculo').respond("test");
    	Chofervehiculo.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/chofervehiculo').respond("test");
    	Chofervehiculo.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/chofervehiculo/1').respond("test");
    	Chofervehiculo.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Chofervehiculo.create({id:null,name:'chofervehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('chofervehiculo.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/chofervehiculo').respond("test");
    	Chofervehiculo.create({id:'1',name:'chofervehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Chofervehiculo.update({id:null,name:'chofervehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('chofervehiculo.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/chofervehiculo/1').respond("test");
    	Chofervehiculo.update({id:'1',name:'chofervehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/chofervehiculo/1').respond("test");
    	Chofervehiculo.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});