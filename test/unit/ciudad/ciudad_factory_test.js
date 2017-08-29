'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('ciudad.module'));
  
  describe('Ciudad', function(){
	var $httpBackend, Ciudad, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Ciudad = $injector.get('Ciudad');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/ciudad').respond("test");
    	Ciudad.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/ciudad').respond("test");
    	Ciudad.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/ciudad/1').respond("test");
    	Ciudad.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Ciudad.create({id:null,name:'ciudad'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('ciudad.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/ciudad').respond("test");
    	Ciudad.create({id:'1',name:'ciudad'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Ciudad.update({id:null,name:'ciudad'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('ciudad.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/ciudad/1').respond("test");
    	Ciudad.update({id:'1',name:'ciudad'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/ciudad/1').respond("test");
    	Ciudad.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});