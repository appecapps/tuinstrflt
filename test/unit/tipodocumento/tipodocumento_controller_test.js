'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('tipodocumento.module'));
  
  describe('TipodocumentoCtrl', function(){
    var TipodocumentoCtrl, Tipodocumento, Tipocartera, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Tipodocumento service
    	Tipodocumento = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'tipodocumento1'});
    			return deferred.promise;
    		}
    	};
		
				Tipocartera = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				TipodocumentoCtrl = $controller('TipodocumentoCtrl', {
    		'Tipodocumento': Tipodocumento,
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
    	expect($scope.tipodocumento).toBeNull();
    	expect($scope.tipodocumentos).toBe('tipodocumento1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshTipodocumentoList', function() {
    	// given
    	Tipodocumento.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipodocumento2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipodocumentoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.tipodocumentos).toBe('tipodocumento2');
    });
    
    it('refreshTipodocumento', function() {
    	// given
    	Tipodocumento.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'tipodocumento'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipodocumento('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.tipodocumento).toBe('tipodocumento'+'1');
    });
    
	it('goToTipodocumentoList', function() {
    	// given
    	spyOn($scope, "refreshTipodocumentoList");
    	
    	// when
    	$scope.goToTipodocumentoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipodocumentoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/tipodocumento');
    });
    
    it('goToTipodocumento', function() {
    	// given
    	spyOn($scope, "refreshTipodocumento");
    	var id = 1;
    	
    	// when
    	$scope.goToTipodocumento(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipodocumento).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/tipodocumento'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.tipodocumento = {id:'1', name:'tipodocumento'};
    	
    	$scope.mode = 'create';
    	Tipodocumento.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipodocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipodocumento).toBe('tipodocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.tipodocumento = {id:'1', name:'tipodocumento'};
    	
    	$scope.mode = 'update';
    	Tipodocumento.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipodocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipodocumento).toBe('tipodocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Tipodocumento.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToTipodocumentoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToTipodocumentoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : tipodocumento create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/tipodocumento/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.tipodocumento).toBeNull();
    	expect($scope.tipodocumentos).toBe('tipodocumento1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});