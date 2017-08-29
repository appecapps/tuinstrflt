'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('viaje.module'));
  
  describe('ViajeCtrl', function(){
    var ViajeCtrl, Viaje, Chofervehiculo,  Destinohorario, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Viaje service
    	Viaje = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'viaje1'});
    			return deferred.promise;
    		}
    	};
		
				Chofervehiculo = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Destinohorario = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ViajeCtrl = $controller('ViajeCtrl', {
    		'Viaje': Viaje,
						'Chofervehiculo': Chofervehiculo,
						'Destinohorario': Destinohorario,
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
    	expect($scope.viaje).toBeNull();
    	expect($scope.viajes).toBe('viaje1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshViajeList', function() {
    	// given
    	Viaje.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'viaje2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshViajeList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.viajes).toBe('viaje2');
    });
    
    it('refreshViaje', function() {
    	// given
    	Viaje.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'viaje'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshViaje('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.viaje).toBe('viaje'+'1');
    });
    
	it('goToViajeList', function() {
    	// given
    	spyOn($scope, "refreshViajeList");
    	
    	// when
    	$scope.goToViajeList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshViajeList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/viaje');
    });
    
    it('goToViaje', function() {
    	// given
    	spyOn($scope, "refreshViaje");
    	var id = 1;
    	
    	// when
    	$scope.goToViaje(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshViaje).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/viaje'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.viaje = {id:'1', name:'viaje'};
    	
    	$scope.mode = 'create';
    	Viaje.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'viajeSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.viaje).toBe('viajeSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.viaje = {id:'1', name:'viaje'};
    	
    	$scope.mode = 'update';
    	Viaje.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'viajeSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.viaje).toBe('viajeSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Viaje.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToViajeList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToViajeList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : viaje create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/viaje/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.viaje).toBeNull();
    	expect($scope.viajes).toBe('viaje1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});