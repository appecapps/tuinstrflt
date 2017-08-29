'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('facturaelectronica.module'));
  
  describe('Facturaelectronica', function(){
	var $httpBackend, Facturaelectronica, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Facturaelectronica = $injector.get('Facturaelectronica');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/facturaelectronica').respond("test");
    	Facturaelectronica.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/facturaelectronica').respond("test");
    	Facturaelectronica.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/facturaelectronica/1').respond("test");
    	Facturaelectronica.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Facturaelectronica.create({id:null,name:'facturaelectronica'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('facturaelectronica.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/facturaelectronica').respond("test");
    	Facturaelectronica.create({id:'1',name:'facturaelectronica'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Facturaelectronica.update({id:null,name:'facturaelectronica'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('facturaelectronica.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/facturaelectronica/1').respond("test");
    	Facturaelectronica.update({id:'1',name:'facturaelectronica'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/facturaelectronica/1').respond("test");
    	Facturaelectronica.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});