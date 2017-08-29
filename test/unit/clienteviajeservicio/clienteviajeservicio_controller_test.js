'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('clienteviajeservicio.module'));
  
  describe('ClienteviajeservicioCtrl', function(){
    var ClienteviajeservicioCtrl, Clienteviajeservicio, Clienteviaje,  Vehiculoservicio, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Clienteviajeservicio service
    	Clienteviajeservicio = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'clienteviajeservicio1'});
    			return deferred.promise;
    		}
    	};
		
				Clienteviaje = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Vehiculoservicio = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ClienteviajeservicioCtrl = $controller('ClienteviajeservicioCtrl', {
    		'Clienteviajeservicio': Clienteviajeservicio,
						'Clienteviaje': Clienteviaje,
						'Vehiculoservicio': Vehiculoservicio,
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
    	expect($scope.clienteviajeservicio).toBeNull();
    	expect($scope.clienteviajeservicios).toBe('clienteviajeservicio1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshClienteviajeservicioList', function() {
    	// given
    	Clienteviajeservicio.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeservicio2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshClienteviajeservicioList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.clienteviajeservicios).toBe('clienteviajeservicio2');
    });
    
    it('refreshClienteviajeservicio', function() {
    	// given
    	Clienteviajeservicio.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeservicio'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshClienteviajeservicio('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.clienteviajeservicio).toBe('clienteviajeservicio'+'1');
    });
    
	it('goToClienteviajeservicioList', function() {
    	// given
    	spyOn($scope, "refreshClienteviajeservicioList");
    	
    	// when
    	$scope.goToClienteviajeservicioList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshClienteviajeservicioList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/clienteviajeservicio');
    });
    
    it('goToClienteviajeservicio', function() {
    	// given
    	spyOn($scope, "refreshClienteviajeservicio");
    	var id = 1;
    	
    	// when
    	$scope.goToClienteviajeservicio(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshClienteviajeservicio).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/clienteviajeservicio'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.clienteviajeservicio = {id:'1', name:'clienteviajeservicio'};
    	
    	$scope.mode = 'create';
    	Clienteviajeservicio.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeservicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.clienteviajeservicio).toBe('clienteviajeservicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.clienteviajeservicio = {id:'1', name:'clienteviajeservicio'};
    	
    	$scope.mode = 'update';
    	Clienteviajeservicio.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeservicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.clienteviajeservicio).toBe('clienteviajeservicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Clienteviajeservicio.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToClienteviajeservicioList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToClienteviajeservicioList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : clienteviajeservicio create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/clienteviajeservicio/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.clienteviajeservicio).toBeNull();
    	expect($scope.clienteviajeservicios).toBe('clienteviajeservicio1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});