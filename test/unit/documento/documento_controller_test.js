'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('documento.module'));
  
  describe('DocumentoCtrl', function(){
    var DocumentoCtrl, Documento, Estado,  Tipodocumento, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Documento service
    	Documento = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'documento1'});
    			return deferred.promise;
    		}
    	};
		
				Estado = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Tipodocumento = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				DocumentoCtrl = $controller('DocumentoCtrl', {
    		'Documento': Documento,
						'Estado': Estado,
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
    	expect($scope.documento).toBeNull();
    	expect($scope.documentos).toBe('documento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshDocumentoList', function() {
    	// given
    	Documento.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'documento2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshDocumentoList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.documentos).toBe('documento2');
    });
    
    it('refreshDocumento', function() {
    	// given
    	Documento.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'documento'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshDocumento('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.documento).toBe('documento'+'1');
    });
    
	it('goToDocumentoList', function() {
    	// given
    	spyOn($scope, "refreshDocumentoList");
    	
    	// when
    	$scope.goToDocumentoList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshDocumentoList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/documento');
    });
    
    it('goToDocumento', function() {
    	// given
    	spyOn($scope, "refreshDocumento");
    	var id = 1;
    	
    	// when
    	$scope.goToDocumento(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshDocumento).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/documento'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.documento = {id:'1', name:'documento'};
    	
    	$scope.mode = 'create';
    	Documento.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'documentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.documento).toBe('documentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.documento = {id:'1', name:'documento'};
    	
    	$scope.mode = 'update';
    	Documento.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'documentoSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.documento).toBe('documentoSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Documento.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToDocumentoList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToDocumentoList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : documento create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/documento/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.documento).toBeNull();
    	expect($scope.documentos).toBe('documento1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});