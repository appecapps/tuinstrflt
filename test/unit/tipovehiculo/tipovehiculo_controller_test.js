'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('tipovehiculo.module'));
  
  describe('TipovehiculoCtrl', function(){
    var TipovehiculoCtrl, Tipovehiculo,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Tipovehiculo service
    	Tipovehiculo = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'tipovehiculo1'});
    			return deferred.promise;
    		}
    	};
		
				TipovehiculoCtrl = $controller('TipovehiculoCtrl', {
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
    	expect($scope.tipovehiculo).toBeNull();
    	expect($scope.tipovehiculos).toBe('tipovehiculo1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshTipovehiculoList', function() {
    	// given
    	Tipovehiculo.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipovehiculo2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipovehiculoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.tipovehiculos).toBe('tipovehiculo2');
    });
    
    it('refreshTipovehiculo', function() {
    	// given
    	Tipovehiculo.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'tipovehiculo'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipovehiculo('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.tipovehiculo).toBe('tipovehiculo'+'1');
    });
    
	it('goToTipovehiculoList', function() {
    	// given
    	spyOn($scope, "refreshTipovehiculoList");
    	
    	// when
    	$scope.goToTipovehiculoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipovehiculoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/tipovehiculo');
    });
    
    it('goToTipovehiculo', function() {
    	// given
    	spyOn($scope, "refreshTipovehiculo");
    	var id = 1;
    	
    	// when
    	$scope.goToTipovehiculo(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipovehiculo).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/tipovehiculo'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.tipovehiculo = {id:'1', name:'tipovehiculo'};
    	
    	$scope.mode = 'create';
    	Tipovehiculo.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipovehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipovehiculo).toBe('tipovehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.tipovehiculo = {id:'1', name:'tipovehiculo'};
    	
    	$scope.mode = 'update';
    	Tipovehiculo.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipovehiculoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipovehiculo).toBe('tipovehiculoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Tipovehiculo.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToTipovehiculoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToTipovehiculoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : tipovehiculo create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/tipovehiculo/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.tipovehiculo).toBeNull();
    	expect($scope.tipovehiculos).toBe('tipovehiculo1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});