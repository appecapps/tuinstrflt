'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('provincia.module'));
  
  describe('ProvinciaCtrl', function(){
    var ProvinciaCtrl, Provincia,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Provincia service
    	Provincia = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'provincia1'});
    			return deferred.promise;
    		}
    	};
		
				ProvinciaCtrl = $controller('ProvinciaCtrl', {
    		'Provincia': Provincia,
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
    	expect($scope.provincia).toBeNull();
    	expect($scope.provincias).toBe('provincia1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshProvinciaList', function() {
    	// given
    	Provincia.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'provincia2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshProvinciaList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.provincias).toBe('provincia2');
    });
    
    it('refreshProvincia', function() {
    	// given
    	Provincia.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'provincia'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshProvincia('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.provincia).toBe('provincia'+'1');
    });
    
	it('goToProvinciaList', function() {
    	// given
    	spyOn($scope, "refreshProvinciaList");
    	
    	// when
    	$scope.goToProvinciaList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshProvinciaList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/provincia');
    });
    
    it('goToProvincia', function() {
    	// given
    	spyOn($scope, "refreshProvincia");
    	var id = 1;
    	
    	// when
    	$scope.goToProvincia(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshProvincia).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/provincia'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.provincia = {id:'1', name:'provincia'};
    	
    	$scope.mode = 'create';
    	Provincia.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'provinciaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.provincia).toBe('provinciaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.provincia = {id:'1', name:'provincia'};
    	
    	$scope.mode = 'update';
    	Provincia.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'provinciaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.provincia).toBe('provinciaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Provincia.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToProvinciaList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToProvinciaList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : provincia create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/provincia/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.provincia).toBeNull();
    	expect($scope.provincias).toBe('provincia1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});