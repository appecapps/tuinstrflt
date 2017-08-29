'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('tipodocumento.module'));
  
  describe('Tipodocumento', function(){
	var $httpBackend, Tipodocumento, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Tipodocumento = $injector.get('Tipodocumento');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/tipodocumento').respond("test");
    	Tipodocumento.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/tipodocumento').respond("test");
    	Tipodocumento.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/tipodocumento/1').respond("test");
    	Tipodocumento.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Tipodocumento.create({id:null,name:'tipodocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipodocumento.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/tipodocumento').respond("test");
    	Tipodocumento.create({id:'1',name:'tipodocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Tipodocumento.update({id:null,name:'tipodocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipodocumento.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/tipodocumento/1').respond("test");
    	Tipodocumento.update({id:'1',name:'tipodocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/tipodocumento/1').respond("test");
    	Tipodocumento.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});