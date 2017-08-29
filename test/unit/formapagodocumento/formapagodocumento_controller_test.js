'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('formapagodocumento.module'));
  
  describe('FormapagodocumentoCtrl', function(){
    var FormapagodocumentoCtrl, Formapagodocumento, Documento,  Formapago, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Formapagodocumento service
    	Formapagodocumento = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'formapagodocumento1'});
    			return deferred.promise;
    		}
    	};
		
				Documento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Formapago = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				FormapagodocumentoCtrl = $controller('FormapagodocumentoCtrl', {
    		'Formapagodocumento': Formapagodocumento,
						'Documento': Documento,
						'Formapago': Formapago,
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
    	expect($scope.formapagodocumento).toBeNull();
    	expect($scope.formapagodocumentos).toBe('formapagodocumento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshFormapagodocumentoList', function() {
    	// given
    	Formapagodocumento.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagodocumento2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFormapagodocumentoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.formapagodocumentos).toBe('formapagodocumento2');
    });
    
    it('refreshFormapagodocumento', function() {
    	// given
    	Formapagodocumento.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagodocumento'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshFormapagodocumento('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.formapagodocumento).toBe('formapagodocumento'+'1');
    });
    
	it('goToFormapagodocumentoList', function() {
    	// given
    	spyOn($scope, "refreshFormapagodocumentoList");
    	
    	// when
    	$scope.goToFormapagodocumentoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFormapagodocumentoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/formapagodocumento');
    });
    
    it('goToFormapagodocumento', function() {
    	// given
    	spyOn($scope, "refreshFormapagodocumento");
    	var id = 1;
    	
    	// when
    	$scope.goToFormapagodocumento(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshFormapagodocumento).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/formapagodocumento'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.formapagodocumento = {id:'1', name:'formapagodocumento'};
    	
    	$scope.mode = 'create';
    	Formapagodocumento.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagodocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.formapagodocumento).toBe('formapagodocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.formapagodocumento = {id:'1', name:'formapagodocumento'};
    	
    	$scope.mode = 'update';
    	Formapagodocumento.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'formapagodocumentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.formapagodocumento).toBe('formapagodocumentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Formapagodocumento.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToFormapagodocumentoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToFormapagodocumentoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : formapagodocumento create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/formapagodocumento/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.formapagodocumento).toBeNull();
    	expect($scope.formapagodocumentos).toBe('formapagodocumento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});