'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('marca.module'));
  
  describe('MarcaCtrl', function(){
    var MarcaCtrl, Marca,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Marca service
    	Marca = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'marca1'});
    			return deferred.promise;
    		}
    	};
		
				MarcaCtrl = $controller('MarcaCtrl', {
    		'Marca': Marca,
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
    	expect($scope.marca).toBeNull();
    	expect($scope.marcas).toBe('marca1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshMarcaList', function() {
    	// given
    	Marca.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'marca2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshMarcaList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.marcas).toBe('marca2');
    });
    
    it('refreshMarca', function() {
    	// given
    	Marca.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'marca'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshMarca('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.marca).toBe('marca'+'1');
    });
    
	it('goToMarcaList', function() {
    	// given
    	spyOn($scope, "refreshMarcaList");
    	
    	// when
    	$scope.goToMarcaList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshMarcaList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/marca');
    });
    
    it('goToMarca', function() {
    	// given
    	spyOn($scope, "refreshMarca");
    	var id = 1;
    	
    	// when
    	$scope.goToMarca(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshMarca).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/marca'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.marca = {id:'1', name:'marca'};
    	
    	$scope.mode = 'create';
    	Marca.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'marcaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.marca).toBe('marcaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.marca = {id:'1', name:'marca'};
    	
    	$scope.mode = 'update';
    	Marca.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'marcaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.marca).toBe('marcaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Marca.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToMarcaList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToMarcaList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : marca create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/marca/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.marca).toBeNull();
    	expect($scope.marcas).toBe('marca1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});