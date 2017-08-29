'use strict';

/* jasmine specs for controllers go here */

describe('services', function(){
  beforeEach(module('rolitemmenu.module'));
  
  describe('Rolitemmenu', function(){
	var $httpBackend, Rolitemmenu, restURL;
	  
    beforeEach(inject(function($injector) {
    	$httpBackend = $injector.get('$httpBackend');
    	Rolitemmenu = $injector.get('Rolitemmenu');
        restURL = $injector.get('restURL');
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
	it('getAllAsListItems', function() {
		$httpBackend.when('GET', restURL+'/items/rolitemmenu').respond("test");
    	Rolitemmenu.getAllAsListItems().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
	});

    it('getAll', function() {
    	$httpBackend.when('GET', restURL+'/rolitemmenu').respond("test");
    	Rolitemmenu.getAll().then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('get', function() {
    	$httpBackend.when('GET', restURL+'/rolitemmenu/1').respond("test");
    	Rolitemmenu.get('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('create throws exception : id not defined', function() {
    	try{
    		Rolitemmenu.create({id:null,name:'rolitemmenu'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('rolitemmenu.id.not.defined');
    	}
    });
    
    it('create', function() {
    	$httpBackend.when('POST', restURL+'/rolitemmenu').respond("test");
    	Rolitemmenu.create({id:'1',name:'rolitemmenu'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('update throws exception : id not defined', function() {
    	try{
    		Rolitemmenu.update({id:null,name:'rolitemmenu'});
    		$httpBackend.flush();
    	} catch(errors) {
    		expect(errors[0]).toBe('rolitemmenu.id.not.defined');
    	}
    });
    
    it('update', function() {
    	$httpBackend.when('PUT', restURL+'/rolitemmenu/1').respond("test");
    	Rolitemmenu.update({id:'1',name:'rolitemmenu'}).then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
    
    it('delete', function() {
    	$httpBackend.when('DELETE', restURL+'/rolitemmenu/1').respond("test");
    	Rolitemmenu.delete('1').then(function(response) {
        	expect(response.status).toBe(200);
    		expect(response.data).toBe("test");
    	});
    	$httpBackend.flush();
    });
  });
});