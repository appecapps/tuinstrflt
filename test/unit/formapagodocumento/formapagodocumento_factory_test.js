'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('formapagodocumento.module'));
  
  describe('Formapagodocumento', function(){
	var $httpBackend, Formapagodocumento, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Formapagodocumento = $injector.get('Formapagodocumento');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/formapagodocumento').respond("test");
    	Formapagodocumento.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/formapagodocumento').respond("test");
    	Formapagodocumento.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/formapagodocumento/1').respond("test");
    	Formapagodocumento.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Formapagodocumento.create({id:null,name:'formapagodocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('formapagodocumento.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/formapagodocumento').respond("test");
    	Formapagodocumento.create({id:'1',name:'formapagodocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Formapagodocumento.update({id:null,name:'formapagodocumento'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('formapagodocumento.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/formapagodocumento/1').respond("test");
    	Formapagodocumento.update({id:'1',name:'formapagodocumento'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/formapagodocumento/1').respond("test");
    	Formapagodocumento.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});