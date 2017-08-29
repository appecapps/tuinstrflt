'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('tipocartera.module'));
  
  describe('Tipocartera', function(){
	var $httpBackend, Tipocartera, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Tipocartera = $injector.get('Tipocartera');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/tipocartera').respond("test");
    	Tipocartera.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/tipocartera').respond("test");
    	Tipocartera.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/tipocartera/1').respond("test");
    	Tipocartera.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Tipocartera.create({id:null,name:'tipocartera'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipocartera.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/tipocartera').respond("test");
    	Tipocartera.create({id:'1',name:'tipocartera'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Tipocartera.update({id:null,name:'tipocartera'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipocartera.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/tipocartera/1').respond("test");
    	Tipocartera.update({id:'1',name:'tipocartera'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/tipocartera/1').respond("test");
    	Tipocartera.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});