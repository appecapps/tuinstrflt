'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('tipovehiculo.module'));
  
  describe('Tipovehiculo', function(){
	var $httpBackend, Tipovehiculo, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Tipovehiculo = $injector.get('Tipovehiculo');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/tipovehiculo').respond("test");
    	Tipovehiculo.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/tipovehiculo').respond("test");
    	Tipovehiculo.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/tipovehiculo/1').respond("test");
    	Tipovehiculo.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Tipovehiculo.create({id:null,name:'tipovehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipovehiculo.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/tipovehiculo').respond("test");
    	Tipovehiculo.create({id:'1',name:'tipovehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Tipovehiculo.update({id:null,name:'tipovehiculo'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipovehiculo.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/tipovehiculo/1').respond("test");
    	Tipovehiculo.update({id:'1',name:'tipovehiculo'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/tipovehiculo/1').respond("test");
    	Tipovehiculo.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});