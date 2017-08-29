'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('entidadrol.module'));
  
  describe('EntidadrolCtrl', function(){
    var EntidadrolCtrl, Entidadrol, Rol,  Entidad, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Entidadrol service
    	Entidadrol = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'entidadrol1'});
    			return deferred.promise;
    		}
    	};
		
				Rol = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Entidad = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				EntidadrolCtrl = $controller('EntidadrolCtrl', {
    		'Entidadrol': Entidadrol,
						'Rol': Rol,
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
    	expect($scope.entidadrol).toBeNull();
    	expect($scope.entidadrols).toBe('entidadrol1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshEntidadrolList', function() {
    	// given
    	Entidadrol.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadrol2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEntidadrolList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.entidadrols).toBe('entidadrol2');
    });
    
    it('refreshEntidadrol', function() {
    	// given
    	Entidadrol.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadrol'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshEntidadrol('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.entidadrol).toBe('entidadrol'+'1');
    });
    
	it('goToEntidadrolList', function() {
    	// given
    	spyOn($scope, "refreshEntidadrolList");
    	
    	// when
    	$scope.goToEntidadrolList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEntidadrolList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/entidadrol');
    });
    
    it('goToEntidadrol', function() {
    	// given
    	spyOn($scope, "refreshEntidadrol");
    	var id = 1;
    	
    	// when
    	$scope.goToEntidadrol(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshEntidadrol).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/entidadrol'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.entidadrol = {id:'1', name:'entidadrol'};
    	
    	$scope.mode = 'create';
    	Entidadrol.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadrolSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.entidadrol).toBe('entidadrolSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.entidadrol = {id:'1', name:'entidadrol'};
    	
    	$scope.mode = 'update';
    	Entidadrol.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'entidadrolSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.entidadrol).toBe('entidadrolSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Entidadrol.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToEntidadrolList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToEntidadrolList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : entidadrol create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/entidadrol/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.entidadrol).toBeNull();
    	expect($scope.entidadrols).toBe('entidadrol1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});