'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('modelo.module'));
  
  describe('ModeloCtrl', function(){
    var ModeloCtrl, Modelo, Marca,  Tipovehiculo, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Modelo service
    	Modelo = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'modelo1'});
    			return deferred.promise;
    		}
    	};
		
				Marca = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Tipovehiculo = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ModeloCtrl = $controller('ModeloCtrl', {
    		'Modelo': Modelo,
						'Marca': Marca,
						'Tipovehiculo': Tipovehiculo,
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
    	expect($scope.modelo).toBeNull();
    	expect($scope.modelos).toBe('modelo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshModeloList', function() {
    	// given
    	Modelo.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'modelo2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshModeloList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.modelos).toBe('modelo2');
    });
    
    it('refreshModelo', function() {
    	// given
    	Modelo.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'modelo'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshModelo('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.modelo).toBe('modelo'+'1');
    });
    
	it('goToModeloList', function() {
    	// given
    	spyOn($scope, "refreshModeloList");
    	
    	// when
    	$scope.goToModeloList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshModeloList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/modelo');
    });
    
    it('goToModelo', function() {
    	// given
    	spyOn($scope, "refreshModelo");
    	var id = 1;
    	
    	// when
    	$scope.goToModelo(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshModelo).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/modelo'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.modelo = {id:'1', name:'modelo'};
    	
    	$scope.mode = 'create';
    	Modelo.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'modeloSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.modelo).toBe('modeloSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.modelo = {id:'1', name:'modelo'};
    	
    	$scope.mode = 'update';
    	Modelo.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'modeloSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.modelo).toBe('modeloSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Modelo.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToModeloList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToModeloList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : modelo create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/modelo/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.modelo).toBeNull();
    	expect($scope.modelos).toBe('modelo1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});