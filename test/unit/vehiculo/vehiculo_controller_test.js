'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('vehiculo.module'));
  
  describe('VehiculoCtrl', function(){
    var VehiculoCtrl, Vehiculo, Modelo,  Color, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Vehiculo service
    	Vehiculo = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'vehiculo1'});
    			return deferred.promise;
    		}
    	};
		
				Modelo = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Color = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				VehiculoCtrl = $controller('VehiculoCtrl', {
    		'Vehiculo': Vehiculo,
						'Modelo': Modelo,
						'Color': Color,
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
    	expect($scope.vehiculo).toBeNull();
    	expect($scope.vehiculos).toBe('vehiculo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshVehiculoList', function() {
    	// given
    	Vehiculo.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculo2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVehiculoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.vehiculos).toBe('vehiculo2');
    });
    
    it('refreshVehiculo', function() {
    	// given
    	Vehiculo.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculo'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVehiculo('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.vehiculo).toBe('vehiculo'+'1');
    });
    
	it('goToVehiculoList', function() {
    	// given
    	spyOn($scope, "refreshVehiculoList");
    	
    	// when
    	$scope.goToVehiculoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVehiculoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/vehiculo');
    });
    
    it('goToVehiculo', function() {
    	// given
    	spyOn($scope, "refreshVehiculo");
    	var id = 1;
    	
    	// when
    	$scope.goToVehiculo(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVehiculo).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/vehiculo'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.vehiculo = {id:'1', name:'vehiculo'};
    	
    	$scope.mode = 'create';
    	Vehiculo.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.vehiculo).toBe('vehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.vehiculo = {id:'1', name:'vehiculo'};
    	
    	$scope.mode = 'update';
    	Vehiculo.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.vehiculo).toBe('vehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Vehiculo.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToVehiculoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToVehiculoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : vehiculo create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/vehiculo/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.vehiculo).toBeNull();
    	expect($scope.vehiculos).toBe('vehiculo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});