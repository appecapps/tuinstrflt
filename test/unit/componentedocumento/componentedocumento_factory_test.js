'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('componentedocumento.module'));
  
  describe('Componentedocumento', function(){
	var $httpBackend, Componentedocumento, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Componentedocumento = $injector.get('Componentedocumento');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/componentedocumento').respond("test");
    	Componentedocumento.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/componentedocumento').respond("test");
    	Componentedocumento.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/componentedocumento/1').respond("test");
    	Componentedocumento.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Componentedocumento.create({id:null,name:'componentedocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('componentedocumento.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/componentedocumento').respond("test");
    	Componentedocumento.create({id:'1',name:'componentedocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Componentedocumento.update({id:null,name:'componentedocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('componentedocumento.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/componentedocumento/1').respond("test");
    	Componentedocumento.update({id:'1',name:'componentedocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/componentedocumento/1').respond("test");
    	Componentedocumento.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});