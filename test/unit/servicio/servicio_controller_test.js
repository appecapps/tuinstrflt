'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('servicio.module'));
  
  describe('ServicioCtrl', function(){
    var ServicioCtrl, Servicio,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Servicio service
    	Servicio = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'servicio1'});
    			return deferred.promise;
    		}
    	};
		
				ServicioCtrl = $controller('ServicioCtrl', {
    		'Servicio': Servicio,
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
    	expect($scope.servicio).toBeNull();
    	expect($scope.servicios).toBe('servicio1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshServicioList', function() {
    	// given
    	Servicio.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'servicio2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshServicioList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.servicios).toBe('servicio2');
    });
    
    it('refreshServicio', function() {
    	// given
    	Servicio.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'servicio'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshServicio('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.servicio).toBe('servicio'+'1');
    });
    
	it('goToServicioList', function() {
    	// given
    	spyOn($scope, "refreshServicioList");
    	
    	// when
    	$scope.goToServicioList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshServicioList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/servicio');
    });
    
    it('goToServicio', function() {
    	// given
    	spyOn($scope, "refreshServicio");
    	var id = 1;
    	
    	// when
    	$scope.goToServicio(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshServicio).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/servicio'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.servicio = {id:'1', name:'servicio'};
    	
    	$scope.mode = 'create';
    	Servicio.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'servicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.servicio).toBe('servicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.servicio = {id:'1', name:'servicio'};
    	
    	$scope.mode = 'update';
    	Servicio.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'servicioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.servicio).toBe('servicioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Servicio.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToServicioList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToServicioList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : servicio create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/servicio/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.servicio).toBeNull();
    	expect($scope.servicios).toBe('servicio1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});