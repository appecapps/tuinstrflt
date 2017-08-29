'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('clienteviajeservicio.module'));
  
  describe('Clienteviajeservicio', function(){
	var $httpBackend, Clienteviajeservicio, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Clienteviajeservicio = $injector.get('Clienteviajeservicio');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/clienteviajeservicio').respond("test");
    	Clienteviajeservicio.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/clienteviajeservicio').respond("test");
    	Clienteviajeservicio.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/clienteviajeservicio/1').respond("test");
    	Clienteviajeservicio.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Clienteviajeservicio.create({id:null,name:'clienteviajeservicio'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('clienteviajeservicio.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/clienteviajeservicio').respond("test");
    	Clienteviajeservicio.create({id:'1',name:'clienteviajeservicio'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Clienteviajeservicio.update({id:null,name:'clienteviajeservicio'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('clienteviajeservicio.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/clienteviajeservicio/1').respond("test");
    	Clienteviajeservicio.update({id:'1',name:'clienteviajeservicio'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/clienteviajeservicio/1').respond("test");
    	Clienteviajeservicio.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});