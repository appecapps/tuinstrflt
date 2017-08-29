'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('ciudad.module'));
  
  describe('CiudadCtrl', function(){
    var CiudadCtrl, Ciudad, Provincia, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Ciudad service
    	Ciudad = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'ciudad1'});
    			return deferred.promise;
    		}
    	};
		
				Provincia = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				CiudadCtrl = $controller('CiudadCtrl', {
    		'Ciudad': Ciudad,
						'Provincia': Provincia,
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
    	expect($scope.ciudad).toBeNull();
    	expect($scope.ciudads).toBe('ciudad1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshCiudadList', function() {
    	// given
    	Ciudad.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'ciudad2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshCiudadList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.ciudads).toBe('ciudad2');
    });
    
    it('refreshCiudad', function() {
    	// given
    	Ciudad.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'ciudad'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshCiudad('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.ciudad).toBe('ciudad'+'1');
    });
    
	it('goToCiudadList', function() {
    	// given
    	spyOn($scope, "refreshCiudadList");
    	
    	// when
    	$scope.goToCiudadList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshCiudadList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/ciudad');
    });
    
    it('goToCiudad', function() {
    	// given
    	spyOn($scope, "refreshCiudad");
    	var id = 1;
    	
    	// when
    	$scope.goToCiudad(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshCiudad).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/ciudad'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.ciudad = {id:'1', name:'ciudad'};
    	
    	$scope.mode = 'create';
    	Ciudad.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'ciudadSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.ciudad).toBe('ciudadSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.ciudad = {id:'1', name:'ciudad'};
    	
    	$scope.mode = 'update';
    	Ciudad.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'ciudadSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.ciudad).toBe('ciudadSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Ciudad.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToCiudadList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToCiudadList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : ciudad create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/ciudad/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.ciudad).toBeNull();
    	expect($scope.ciudads).toBe('ciudad1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});