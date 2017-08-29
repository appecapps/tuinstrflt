'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('chofervehiculo.module'));
  
  describe('ChofervehiculoCtrl', function(){
    var ChofervehiculoCtrl, Chofervehiculo, Chofer,  Vehiculo, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Chofervehiculo service
    	Chofervehiculo = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'chofervehiculo1'});
    			return deferred.promise;
    		}
    	};
		
				Chofer = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Vehiculo = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ChofervehiculoCtrl = $controller('ChofervehiculoCtrl', {
    		'Chofervehiculo': Chofervehiculo,
						'Chofer': Chofer,
						'Vehiculo': Vehiculo,
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
    	expect($scope.chofervehiculo).toBeNull();
    	expect($scope.chofervehiculos).toBe('chofervehiculo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshChofervehiculoList', function() {
    	// given
    	Chofervehiculo.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'chofervehiculo2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshChofervehiculoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.chofervehiculos).toBe('chofervehiculo2');
    });
    
    it('refreshChofervehiculo', function() {
    	// given
    	Chofervehiculo.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'chofervehiculo'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshChofervehiculo('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.chofervehiculo).toBe('chofervehiculo'+'1');
    });
    
	it('goToChofervehiculoList', function() {
    	// given
    	spyOn($scope, "refreshChofervehiculoList");
    	
    	// when
    	$scope.goToChofervehiculoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshChofervehiculoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/chofervehiculo');
    });
    
    it('goToChofervehiculo', function() {
    	// given
    	spyOn($scope, "refreshChofervehiculo");
    	var id = 1;
    	
    	// when
    	$scope.goToChofervehiculo(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshChofervehiculo).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/chofervehiculo'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.chofervehiculo = {id:'1', name:'chofervehiculo'};
    	
    	$scope.mode = 'create';
    	Chofervehiculo.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'chofervehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.chofervehiculo).toBe('chofervehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.chofervehiculo = {id:'1', name:'chofervehiculo'};
    	
    	$scope.mode = 'update';
    	Chofervehiculo.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'chofervehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.chofervehiculo).toBe('chofervehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Chofervehiculo.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToChofervehiculoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToChofervehiculoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : chofervehiculo create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/chofervehiculo/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.chofervehiculo).toBeNull();
    	expect($scope.chofervehiculos).toBe('chofervehiculo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});