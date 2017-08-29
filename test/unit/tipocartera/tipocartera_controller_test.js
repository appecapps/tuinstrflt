'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('tipocartera.module'));
  
  describe('TipocarteraCtrl', function(){
    var TipocarteraCtrl, Tipocartera,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Tipocartera service
    	Tipocartera = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'tipocartera1'});
    			return deferred.promise;
    		}
    	};
		
				TipocarteraCtrl = $controller('TipocarteraCtrl', {
    		'Tipocartera': Tipocartera,
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
    	expect($scope.tipocartera).toBeNull();
    	expect($scope.tipocarteras).toBe('tipocartera1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshTipocarteraList', function() {
    	// given
    	Tipocartera.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocartera2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipocarteraList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.tipocarteras).toBe('tipocartera2');
    });
    
    it('refreshTipocartera', function() {
    	// given
    	Tipocartera.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocartera'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipocartera('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.tipocartera).toBe('tipocartera'+'1');
    });
    
	it('goToTipocarteraList', function() {
    	// given
    	spyOn($scope, "refreshTipocarteraList");
    	
    	// when
    	$scope.goToTipocarteraList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipocarteraList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/tipocartera');
    });
    
    it('goToTipocartera', function() {
    	// given
    	spyOn($scope, "refreshTipocartera");
    	var id = 1;
    	
    	// when
    	$scope.goToTipocartera(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipocartera).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/tipocartera'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.tipocartera = {id:'1', name:'tipocartera'};
    	
    	$scope.mode = 'create';
    	Tipocartera.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocarteraSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipocartera).toBe('tipocarteraSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.tipocartera = {id:'1', name:'tipocartera'};
    	
    	$scope.mode = 'update';
    	Tipocartera.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocarteraSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipocartera).toBe('tipocarteraSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Tipocartera.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToTipocarteraList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToTipocarteraList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : tipocartera create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/tipocartera/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.tipocartera).toBeNull();
    	expect($scope.tipocarteras).toBe('tipocartera1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});