'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('estado.module'));
  
  describe('EstadoCtrl', function(){
    var EstadoCtrl, Estado,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Estado service
    	Estado = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'estado1'});
    			return deferred.promise;
    		}
    	};
		
				EstadoCtrl = $controller('EstadoCtrl', {
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
    	expect($scope.estado).toBeNull();
    	expect($scope.estados).toBe('estado1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshEstadoList', function() {
    	// given
    	Estado.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'estado2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEstadoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.estados).toBe('estado2');
    });
    
    it('refreshEstado', function() {
    	// given
    	Estado.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'estado'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEstado('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.estado).toBe('estado'+'1');
    });
    
	it('goToEstadoList', function() {
    	// given
    	spyOn($scope, "refreshEstadoList");
    	
    	// when
    	$scope.goToEstadoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEstadoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/estado');
    });
    
    it('goToEstado', function() {
    	// given
    	spyOn($scope, "refreshEstado");
    	var id = 1;
    	
    	// when
    	$scope.goToEstado(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEstado).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/estado'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.estado = {id:'1', name:'estado'};
    	
    	$scope.mode = 'create';
    	Estado.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'estadoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.estado).toBe('estadoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.estado = {id:'1', name:'estado'};
    	
    	$scope.mode = 'update';
    	Estado.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'estadoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.estado).toBe('estadoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Estado.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToEstadoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToEstadoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : estado create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/estado/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.estado).toBeNull();
    	expect($scope.estados).toBe('estado1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});