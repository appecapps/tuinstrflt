'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('entidad.module'));
  
  describe('EntidadCtrl', function(){
    var EntidadCtrl, Entidad,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Entidad service
    	Entidad = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'entidad1'});
    			return deferred.promise;
    		}
    	};
		
				EntidadCtrl = $controller('EntidadCtrl', {
    		'Entidad': Entidad,
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
    	expect($scope.entidad).toBeNull();
    	expect($scope.entidads).toBe('entidad1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshEntidadList', function() {
    	// given
    	Entidad.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidad2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEntidadList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.entidads).toBe('entidad2');
    });
    
    it('refreshEntidad', function() {
    	// given
    	Entidad.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'entidad'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEntidad('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.entidad).toBe('entidad'+'1');
    });
    
	it('goToEntidadList', function() {
    	// given
    	spyOn($scope, "refreshEntidadList");
    	
    	// when
    	$scope.goToEntidadList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEntidadList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/entidad');
    });
    
    it('goToEntidad', function() {
    	// given
    	spyOn($scope, "refreshEntidad");
    	var id = 1;
    	
    	// when
    	$scope.goToEntidad(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEntidad).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/entidad'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.entidad = {id:'1', name:'entidad'};
    	
    	$scope.mode = 'create';
    	Entidad.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.entidad).toBe('entidadSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.entidad = {id:'1', name:'entidad'};
    	
    	$scope.mode = 'update';
    	Entidad.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.entidad).toBe('entidadSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Entidad.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToEntidadList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToEntidadList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : entidad create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/entidad/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.entidad).toBeNull();
    	expect($scope.entidads).toBe('entidad1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});