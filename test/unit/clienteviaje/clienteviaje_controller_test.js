'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('clienteviaje.module'));
  
  describe('ClienteviajeCtrl', function(){
    var ClienteviajeCtrl, Clienteviaje, Viaje,  Cliente,  Estado, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Clienteviaje service
    	Clienteviaje = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'clienteviaje1'});
    			return deferred.promise;
    		}
    	};
		
				Viaje = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Cliente = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Estado = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ClienteviajeCtrl = $controller('ClienteviajeCtrl', {
    		'Clienteviaje': Clienteviaje,
						'Viaje': Viaje,
						'Cliente': Cliente,
						'Estado': Estado,
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
    	expect($scope.clienteviaje).toBeNull();
    	expect($scope.clienteviajes).toBe('clienteviaje1');
    	expect(Object.keys($scope.items).length).toBe(3);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshClienteviajeList', function() {
    	// given
    	Clienteviaje.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviaje2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshClienteviajeList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.clienteviajes).toBe('clienteviaje2');
    });
    
    it('refreshClienteviaje', function() {
    	// given
    	Clienteviaje.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviaje'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshClienteviaje('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.clienteviaje).toBe('clienteviaje'+'1');
    });
    
	it('goToClienteviajeList', function() {
    	// given
    	spyOn($scope, "refreshClienteviajeList");
    	
    	// when
    	$scope.goToClienteviajeList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshClienteviajeList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/clienteviaje');
    });
    
    it('goToClienteviaje', function() {
    	// given
    	spyOn($scope, "refreshClienteviaje");
    	var id = 1;
    	
    	// when
    	$scope.goToClienteviaje(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshClienteviaje).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/clienteviaje'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.clienteviaje = {id:'1', name:'clienteviaje'};
    	
    	$scope.mode = 'create';
    	Clienteviaje.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.clienteviaje).toBe('clienteviajeSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.clienteviaje = {id:'1', name:'clienteviaje'};
    	
    	$scope.mode = 'update';
    	Clienteviaje.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'clienteviajeSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.clienteviaje).toBe('clienteviajeSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Clienteviaje.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToClienteviajeList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToClienteviajeList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : clienteviaje create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/clienteviaje/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.clienteviaje).toBeNull();
    	expect($scope.clienteviajes).toBe('clienteviaje1');
    	expect(Object.keys($scope.items).length).toBe(3);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});