'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('rol.module'));
  
  describe('RolCtrl', function(){
    var RolCtrl, Rol,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Rol service
    	Rol = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'rol1'});
    			return deferred.promise;
    		}
    	};
		
				RolCtrl = $controller('RolCtrl', {
    		'Rol': Rol,
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
    	expect($scope.rol).toBeNull();
    	expect($scope.rols).toBe('rol1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshRolList', function() {
    	// given
    	Rol.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rol2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshRolList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.rols).toBe('rol2');
    });
    
    it('refreshRol', function() {
    	// given
    	Rol.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'rol'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshRol('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.rol).toBe('rol'+'1');
    });
    
	it('goToRolList', function() {
    	// given
    	spyOn($scope, "refreshRolList");
    	
    	// when
    	$scope.goToRolList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshRolList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/rol');
    });
    
    it('goToRol', function() {
    	// given
    	spyOn($scope, "refreshRol");
    	var id = 1;
    	
    	// when
    	$scope.goToRol(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshRol).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/rol'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.rol = {id:'1', name:'rol'};
    	
    	$scope.mode = 'create';
    	Rol.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rolSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.rol).toBe('rolSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.rol = {id:'1', name:'rol'};
    	
    	$scope.mode = 'update';
    	Rol.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rolSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.rol).toBe('rolSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Rol.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToRolList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToRolList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : rol create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/rol/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.rol).toBeNull();
    	expect($scope.rols).toBe('rol1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});