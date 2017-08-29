'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('componentefinanciero.module'));
  
  describe('ComponentefinancieroCtrl', function(){
    var ComponentefinancieroCtrl, Componentefinanciero, Tipodocumento,  Tipocomponentefinanciero, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Componentefinanciero service
    	Componentefinanciero = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'componentefinanciero1'});
    			return deferred.promise;
    		}
    	};
		
				Tipodocumento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Tipocomponentefinanciero = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ComponentefinancieroCtrl = $controller('ComponentefinancieroCtrl', {
    		'Componentefinanciero': Componentefinanciero,
						'Tipodocumento': Tipodocumento,
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
    	expect($scope.componentefinanciero).toBeNull();
    	expect($scope.componentefinancieros).toBe('componentefinanciero1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshComponentefinancieroList', function() {
    	// given
    	Componentefinanciero.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentefinanciero2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshComponentefinancieroList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.componentefinancieros).toBe('componentefinanciero2');
    });
    
    it('refreshComponentefinanciero', function() {
    	// given
    	Componentefinanciero.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'componentefinanciero'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshComponentefinanciero('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.componentefinanciero).toBe('componentefinanciero'+'1');
    });
    
	it('goToComponentefinancieroList', function() {
    	// given
    	spyOn($scope, "refreshComponentefinancieroList");
    	
    	// when
    	$scope.goToComponentefinancieroList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshComponentefinancieroList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/componentefinanciero');
    });
    
    it('goToComponentefinanciero', function() {
    	// given
    	spyOn($scope, "refreshComponentefinanciero");
    	var id = 1;
    	
    	// when
    	$scope.goToComponentefinanciero(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshComponentefinanciero).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/componentefinanciero'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.componentefinanciero = {id:'1', name:'componentefinanciero'};
    	
    	$scope.mode = 'create';
    	Componentefinanciero.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentefinancieroSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.componentefinanciero).toBe('componentefinancieroSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.componentefinanciero = {id:'1', name:'componentefinanciero'};
    	
    	$scope.mode = 'update';
    	Componentefinanciero.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'componentefinancieroSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.componentefinanciero).toBe('componentefinancieroSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Componentefinanciero.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToComponentefinancieroList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToComponentefinancieroList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : componentefinanciero create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/componentefinanciero/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.componentefinanciero).toBeNull();
    	expect($scope.componentefinancieros).toBe('componentefinanciero1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});