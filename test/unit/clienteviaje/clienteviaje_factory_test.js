'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('clienteviaje.module'));
  
  describe('Clienteviaje', function(){
	var $httpBackend, Clienteviaje, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Clienteviaje = $injector.get('Clienteviaje');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/clienteviaje').respond("test");
    	Clienteviaje.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/clienteviaje').respond("test");
    	Clienteviaje.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/clienteviaje/1').respond("test");
    	Clienteviaje.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Clienteviaje.create({id:null,name:'clienteviaje'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('clienteviaje.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/clienteviaje').respond("test");
    	Clienteviaje.create({id:'1',name:'clienteviaje'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Clienteviaje.update({id:null,name:'clienteviaje'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('clienteviaje.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/clienteviaje/1').respond("test");
    	Clienteviaje.update({id:'1',name:'clienteviaje'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/clienteviaje/1').respond("test");
    	Clienteviaje.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});