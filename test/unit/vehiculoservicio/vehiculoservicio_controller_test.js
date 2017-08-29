'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('vehiculoservicio.module'));
  
  describe('VehiculoservicioCtrl', function(){
    var VehiculoservicioCtrl, Vehiculoservicio, Servicio,  Vehiculo, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Vehiculoservicio service
    	Vehiculoservicio = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'vehiculoservicio1'});
    			return deferred.promise;
    		}
    	};
		
				Servicio = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Vehiculo = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				VehiculoservicioCtrl = $controller('VehiculoservicioCtrl', {
    		'Vehiculoservicio': Vehiculoservicio,
						'Servicio': Servicio,
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
    	expect($scope.vehiculoservicio).toBeNull();
    	expect($scope.vehiculoservicios).toBe('vehiculoservicio1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshVehiculoservicioList', function() {
    	// given
    	Vehiculoservicio.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoservicio2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVehiculoservicioList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.vehiculoservicios).toBe('vehiculoservicio2');
    });
    
    it('refreshVehiculoservicio', function() {
    	// given
    	Vehiculoservicio.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoservicio'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVehiculoservicio('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.vehiculoservicio).toBe('vehiculoservicio'+'1');
    });
    
	it('goToVehiculoservicioList', function() {
    	// given
    	spyOn($scope, "refreshVehiculoservicioList");
    	
    	// when
    	$scope.goToVehiculoservicioList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVehiculoservicioList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/vehiculoservicio');
    });
    
    it('goToVehiculoservicio', function() {
    	// given
    	spyOn($scope, "refreshVehiculoservicio");
    	var id = 1;
    	
    	// when
    	$scope.goToVehiculoservicio(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVehiculoservicio).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/vehiculoservicio'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.vehiculoservicio = {id:'1', name:'vehiculoservicio'};
    	
    	$scope.mode = 'create';
    	Vehiculoservicio.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoservicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.vehiculoservicio).toBe('vehiculoservicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.vehiculoservicio = {id:'1', name:'vehiculoservicio'};
    	
    	$scope.mode = 'update';
    	Vehiculoservicio.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'vehiculoservicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.vehiculoservicio).toBe('vehiculoservicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Vehiculoservicio.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToVehiculoservicioList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToVehiculoservicioList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : vehiculoservicio create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/vehiculoservicio/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.vehiculoservicio).toBeNull();
    	expect($scope.vehiculoservicios).toBe('vehiculoservicio1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});