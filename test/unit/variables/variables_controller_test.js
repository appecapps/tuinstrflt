'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('variables.module'));
  
  describe('VariablesCtrl', function(){
    var VariablesCtrl, Variables,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Variables service
    	Variables = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'variables1'});
    			return deferred.promise;
    		}
    	};
		
				VariablesCtrl = $controller('VariablesCtrl', {
    		'Variables': Variables,
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
    	expect($scope.variables).toBeNull();
    	expect($scope.variabless).toBe('variables1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshVariablesList', function() {
    	// given
    	Variables.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'variables2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVariablesList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.variabless).toBe('variables2');
    });
    
    it('refreshVariables', function() {
    	// given
    	Variables.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'variables'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshVariables('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.variables).toBe('variables'+'1');
    });
    
	it('goToVariablesList', function() {
    	// given
    	spyOn($scope, "refreshVariablesList");
    	
    	// when
    	$scope.goToVariablesList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVariablesList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/variables');
    });
    
    it('goToVariables', function() {
    	// given
    	spyOn($scope, "refreshVariables");
    	var id = 1;
    	
    	// when
    	$scope.goToVariables(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshVariables).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/variables'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.variables = {id:'1', name:'variables'};
    	
    	$scope.mode = 'create';
    	Variables.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'variablesSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.variables).toBe('variablesSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.variables = {id:'1', name:'variables'};
    	
    	$scope.mode = 'update';
    	Variables.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'variablesSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.variables).toBe('variablesSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Variables.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToVariablesList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToVariablesList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : variables create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/variables/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.variables).toBeNull();
    	expect($scope.variabless).toBe('variables1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});