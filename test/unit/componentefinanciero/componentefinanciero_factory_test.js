'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('componentefinanciero.module'));
  
  describe('Componentefinanciero', function(){
	var $httpBackend, Componentefinanciero, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Componentefinanciero = $injector.get('Componentefinanciero');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/componentefinanciero').respond("test");
    	Componentefinanciero.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/componentefinanciero').respond("test");
    	Componentefinanciero.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/componentefinanciero/1').respond("test");
    	Componentefinanciero.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Componentefinanciero.create({id:null,name:'componentefinanciero'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('componentefinanciero.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/componentefinanciero').respond("test");
    	Componentefinanciero.create({id:'1',name:'componentefinanciero'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Componentefinanciero.update({id:null,name:'componentefinanciero'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('componentefinanciero.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/componentefinanciero/1').respond("test");
    	Componentefinanciero.update({id:'1',name:'componentefinanciero'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/componentefinanciero/1').respond("test");
    	Componentefinanciero.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});