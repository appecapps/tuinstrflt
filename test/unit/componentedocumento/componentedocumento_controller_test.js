'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('componentedocumento.module'));
  
  describe('ComponentedocumentoCtrl', function(){
    var ComponentedocumentoCtrl, Componentedocumento, Componentefinanciero,  Documento, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
    beforeEach(inject(function($injector) {
    	$controller = $injector.get('$controller');
    	$q = $injector.get('$q');
    	$rootScope = $injector.get('$rootScope');
    	$scope = $rootScope.$new();
    	$routeParams = $injector.get('$routeParams');
    	$httpBackend = $injector.get('$httpBackend');
    	
    	// location is mocked due to redirection in browser : karma does not support it
    	$location = {
    		path: jasmine.createSpy("path").andCallFake(function() {
        	    return "";
        	})
    	};
    	
    	// Messages
    	MessageHandler = {
    		cleanMessage: jasmine.createSpy("cleanMessage"),
    		addSuccess: jasmine.createSpy("addSuccess"),
    		manageError: jasmine.createSpy("manageError"),
    		manageException: jasmine.createSpy("manageException"),
    	};

    	// Componentedocumento service
    	Componentedocumento = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'componentedocumento1'});
    			return deferred.promise;
    		}
    	};
		
				Componentefinanciero = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Documento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ComponentedocumentoCtrl = $controller('ComponentedocumentoCtrl', {
    		'Componentedocumento': Componentedocumento,
						'Componentefinanciero': Componentefinanciero,
						'Documento': Documento,
			    		'$scope': $scope,
    		'$routeParams': $routeParams,
    		'$http': $httpBackend,
    		'$location': $location,
    		'MessageHandler': MessageHandler
    	});
    }));

    afterEach(function() {
    	$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
    });
    
    it('init', function() {
    	$rootScope.$apply();
    	expect($scope.mode).toBeNull();
    	expect($scope.componentedocumento).toBeNull();
    	expect($scope.componentedocumentos).toBe('componentedocumento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshComponentedocumentoList', function() {
    	// given
    	Componentedocumento.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentedocumento2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshComponentedocumentoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.componentedocumentos).toBe('componentedocumento2');
    });
    
    it('refreshComponentedocumento', function() {
    	// given
    	Componentedocumento.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'componentedocumento'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshComponentedocumento('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.componentedocumento).toBe('componentedocumento'+'1');
    });
    
	it('goToComponentedocumentoList', function() {
    	// given
    	spyOn($scope, "refreshComponentedocumentoList");
    	
    	// when
    	$scope.goToComponentedocumentoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshComponentedocumentoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/componentedocumento');
    });
    
    it('goToComponentedocumento', function() {
    	// given
    	spyOn($scope, "refreshComponentedocumento");
    	var id = 1;
    	
    	// when
    	$scope.goToComponentedocumento(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshComponentedocumento).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/componentedocumento'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.componentedocumento = {id:'1', name:'componentedocumento'};
    	
    	$scope.mode = 'create';
    	Componentedocumento.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentedocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.componentedocumento).toBe('componentedocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.componentedocumento = {id:'1', name:'componentedocumento'};
    	
    	$scope.mode = 'update';
    	Componentedocumento.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentedocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.componentedocumento).toBe('componentedocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Componentedocumento.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToComponentedocumentoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToComponentedocumentoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : componentedocumento create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/componentedocumento/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.componentedocumento).toBeNull();
    	expect($scope.componentedocumentos).toBe('componentedocumento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});