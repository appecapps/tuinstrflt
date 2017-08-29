'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('formapago.module'));
  
  describe('FormapagoCtrl', function(){
    var FormapagoCtrl, Formapago, Tipodocumento, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Formapago service
    	Formapago = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'formapago1'});
    			return deferred.promise;
    		}
    	};
		
				Tipodocumento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				FormapagoCtrl = $controller('FormapagoCtrl', {
    		'Formapago': Formapago,
						'Tipodocumento': Tipodocumento,
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
    	expect($scope.formapago).toBeNull();
    	expect($scope.formapagos).toBe('formapago1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshFormapagoList', function() {
    	// given
    	Formapago.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapago2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFormapagoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.formapagos).toBe('formapago2');
    });
    
    it('refreshFormapago', function() {
    	// given
    	Formapago.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'formapago'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFormapago('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.formapago).toBe('formapago'+'1');
    });
    
	it('goToFormapagoList', function() {
    	// given
    	spyOn($scope, "refreshFormapagoList");
    	
    	// when
    	$scope.goToFormapagoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFormapagoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/formapago');
    });
    
    it('goToFormapago', function() {
    	// given
    	spyOn($scope, "refreshFormapago");
    	var id = 1;
    	
    	// when
    	$scope.goToFormapago(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFormapago).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/formapago'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.formapago = {id:'1', name:'formapago'};
    	
    	$scope.mode = 'create';
    	Formapago.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.formapago).toBe('formapagoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.formapago = {id:'1', name:'formapago'};
    	
    	$scope.mode = 'update';
    	Formapago.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.formapago).toBe('formapagoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Formapago.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToFormapagoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToFormapagoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : formapago create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/formapago/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.formapago).toBeNull();
    	expect($scope.formapagos).toBe('formapago1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});