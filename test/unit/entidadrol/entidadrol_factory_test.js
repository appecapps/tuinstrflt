'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('entidadrol.module'));
  
  describe('Entidadrol', function(){
	var $httpBackend, Entidadrol, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Entidadrol = $injector.get('Entidadrol');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/entidadrol').respond("test");
    	Entidadrol.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/entidadrol').respond("test");
    	Entidadrol.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/entidadrol/1').respond("test");
    	Entidadrol.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Entidadrol.create({id:null,name:'entidadrol'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('entidadrol.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/entidadrol').respond("test");
    	Entidadrol.create({id:'1',name:'entidadrol'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Entidadrol.update({id:null,name:'entidadrol'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('entidadrol.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/entidadrol/1').respond("test");
    	Entidadrol.update({id:'1',name:'entidadrol'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/entidadrol/1').respond("test");
    	Entidadrol.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});