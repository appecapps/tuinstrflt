'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('tipocomponentefinanciero.module'));
  
  describe('Tipocomponentefinanciero', function(){
	var $httpBackend, Tipocomponentefinanciero, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Tipocomponentefinanciero = $injector.get('Tipocomponentefinanciero');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/tipocomponentefinanciero').respond("test");
    	Tipocomponentefinanciero.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/tipocomponentefinanciero').respond("test");
    	Tipocomponentefinanciero.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/tipocomponentefinanciero/1').respond("test");
    	Tipocomponentefinanciero.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Tipocomponentefinanciero.create({id:null,name:'tipocomponentefinanciero'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipocomponentefinanciero.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/tipocomponentefinanciero').respond("test");
    	Tipocomponentefinanciero.create({id:'1',name:'tipocomponentefinanciero'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Tipocomponentefinanciero.update({id:null,name:'tipocomponentefinanciero'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('tipocomponentefinanciero.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/tipocomponentefinanciero/1').respond("test");
    	Tipocomponentefinanciero.update({id:'1',name:'tipocomponentefinanciero'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/tipocomponentefinanciero/1').respond("test");
    	Tipocomponentefinanciero.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});