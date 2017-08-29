'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('destinohorario.module'));
  
  describe('Destinohorario', function(){
	var $httpBackend, Destinohorario, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Destinohorario = $injector.get('Destinohorario');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/destinohorario').respond("test");
    	Destinohorario.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/destinohorario').respond("test");
    	Destinohorario.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/destinohorario/1').respond("test");
    	Destinohorario.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Destinohorario.create({id:null,name:'destinohorario'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('destinohorario.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/destinohorario').respond("test");
    	Destinohorario.create({id:'1',name:'destinohorario'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Destinohorario.update({id:null,name:'destinohorario'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('destinohorario.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/destinohorario/1').respond("test");
    	Destinohorario.update({id:'1',name:'destinohorario'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/destinohorario/1').respond("test");
    	Destinohorario.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});