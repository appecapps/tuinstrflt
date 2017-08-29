'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('tipocomponentefinanciero.module'));
  
  describe('TipocomponentefinancieroCtrl', function(){
    var TipocomponentefinancieroCtrl, Tipocomponentefinanciero,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Tipocomponentefinanciero service
    	Tipocomponentefinanciero = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'tipocomponentefinanciero1'});
    			return deferred.promise;
    		}
    	};
		
				TipocomponentefinancieroCtrl = $controller('TipocomponentefinancieroCtrl', {
    		'Tipocomponentefinanciero': Tipocomponentefinanciero,
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
    	expect($scope.tipocomponentefinanciero).toBeNull();
    	expect($scope.tipocomponentefinancieros).toBe('tipocomponentefinanciero1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshTipocomponentefinancieroList', function() {
    	// given
    	Tipocomponentefinanciero.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocomponentefinanciero2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipocomponentefinancieroList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.tipocomponentefinancieros).toBe('tipocomponentefinanciero2');
    });
    
    it('refreshTipocomponentefinanciero', function() {
    	// given
    	Tipocomponentefinanciero.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocomponentefinanciero'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshTipocomponentefinanciero('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.tipocomponentefinanciero).toBe('tipocomponentefinanciero'+'1');
    });
    
	it('goToTipocomponentefinancieroList', function() {
    	// given
    	spyOn($scope, "refreshTipocomponentefinancieroList");
    	
    	// when
    	$scope.goToTipocomponentefinancieroList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipocomponentefinancieroList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/tipocomponentefinanciero');
    });
    
    it('goToTipocomponentefinanciero', function() {
    	// given
    	spyOn($scope, "refreshTipocomponentefinanciero");
    	var id = 1;
    	
    	// when
    	$scope.goToTipocomponentefinanciero(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshTipocomponentefinanciero).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/tipocomponentefinanciero'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.tipocomponentefinanciero = {id:'1', name:'tipocomponentefinanciero'};
    	
    	$scope.mode = 'create';
    	Tipocomponentefinanciero.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocomponentefinancieroSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipocomponentefinanciero).toBe('tipocomponentefinancieroSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.tipocomponentefinanciero = {id:'1', name:'tipocomponentefinanciero'};
    	
    	$scope.mode = 'update';
    	Tipocomponentefinanciero.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'tipocomponentefinancieroSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.tipocomponentefinanciero).toBe('tipocomponentefinancieroSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Tipocomponentefinanciero.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToTipocomponentefinancieroList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToTipocomponentefinancieroList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : tipocomponentefinanciero create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/tipocomponentefinanciero/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.tipocomponentefinanciero).toBeNull();
    	expect($scope.tipocomponentefinancieros).toBe('tipocomponentefinanciero1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});