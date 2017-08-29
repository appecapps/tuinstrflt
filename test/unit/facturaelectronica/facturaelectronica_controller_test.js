'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('facturaelectronica.module'));
  
  describe('FacturaelectronicaCtrl', function(){
    var FacturaelectronicaCtrl, Facturaelectronica, Estado,  Documento, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Facturaelectronica service
    	Facturaelectronica = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'facturaelectronica1'});
    			return deferred.promise;
    		}
    	};
		
				Estado = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Documento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				FacturaelectronicaCtrl = $controller('FacturaelectronicaCtrl', {
    		'Facturaelectronica': Facturaelectronica,
						'Estado': Estado,
						'Documento': Documento,
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
    	expect($scope.facturaelectronica).toBeNull();
    	expect($scope.facturaelectronicas).toBe('facturaelectronica1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshFacturaelectronicaList', function() {
    	// given
    	Facturaelectronica.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'facturaelectronica2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFacturaelectronicaList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.facturaelectronicas).toBe('facturaelectronica2');
    });
    
    it('refreshFacturaelectronica', function() {
    	// given
    	Facturaelectronica.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'facturaelectronica'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFacturaelectronica('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.facturaelectronica).toBe('facturaelectronica'+'1');
    });
    
	it('goToFacturaelectronicaList', function() {
    	// given
    	spyOn($scope, "refreshFacturaelectronicaList");
    	
    	// when
    	$scope.goToFacturaelectronicaList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFacturaelectronicaList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/facturaelectronica');
    });
    
    it('goToFacturaelectronica', function() {
    	// given
    	spyOn($scope, "refreshFacturaelectronica");
    	var id = 1;
    	
    	// when
    	$scope.goToFacturaelectronica(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFacturaelectronica).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/facturaelectronica'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.facturaelectronica = {id:'1', name:'facturaelectronica'};
    	
    	$scope.mode = 'create';
    	Facturaelectronica.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'facturaelectronicaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.facturaelectronica).toBe('facturaelectronicaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.facturaelectronica = {id:'1', name:'facturaelectronica'};
    	
    	$scope.mode = 'update';
    	Facturaelectronica.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'facturaelectronicaSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.facturaelectronica).toBe('facturaelectronicaSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Facturaelectronica.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToFacturaelectronicaList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToFacturaelectronicaList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : facturaelectronica create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/facturaelectronica/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.facturaelectronica).toBeNull();
    	expect($scope.facturaelectronicas).toBe('facturaelectronica1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});