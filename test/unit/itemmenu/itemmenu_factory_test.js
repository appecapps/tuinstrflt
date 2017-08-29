'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('itemmenu.module'));
  
  describe('Itemmenu', function(){
	var $httpBackend, Itemmenu, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Itemmenu = $injector.get('Itemmenu');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/itemmenu').respond("test");
    	Itemmenu.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/itemmenu').respond("test");
    	Itemmenu.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/itemmenu/1').respond("test");
    	Itemmenu.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Itemmenu.create({id:null,name:'itemmenu'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('itemmenu.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/itemmenu').respond("test");
    	Itemmenu.create({id:'1',name:'itemmenu'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Itemmenu.update({id:null,name:'itemmenu'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('itemmenu.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/itemmenu/1').respond("test");
    	Itemmenu.update({id:'1',name:'itemmenu'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/itemmenu/1').respond("test");
    	Itemmenu.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});