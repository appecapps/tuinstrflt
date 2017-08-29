'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('destinohorario.module'));
  
  describe('DestinohorarioCtrl', function(){
    var DestinohorarioCtrl, Destinohorario, Ciudad,  Horario, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Destinohorario service
    	Destinohorario = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'destinohorario1'});
    			return deferred.promise;
    		}
    	};
		
				Ciudad = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Horario = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				DestinohorarioCtrl = $controller('DestinohorarioCtrl', {
    		'Destinohorario': Destinohorario,
						'Ciudad': Ciudad,
						'Horario': Horario,
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
    	expect($scope.destinohorario).toBeNull();
    	expect($scope.destinohorarios).toBe('destinohorario1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshDestinohorarioList', function() {
    	// given
    	Destinohorario.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'destinohorario2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshDestinohorarioList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.destinohorarios).toBe('destinohorario2');
    });
    
    it('refreshDestinohorario', function() {
    	// given
    	Destinohorario.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'destinohorario'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshDestinohorario('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.destinohorario).toBe('destinohorario'+'1');
    });
    
	it('goToDestinohorarioList', function() {
    	// given
    	spyOn($scope, "refreshDestinohorarioList");
    	
    	// when
    	$scope.goToDestinohorarioList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshDestinohorarioList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/destinohorario');
    });
    
    it('goToDestinohorario', function() {
    	// given
    	spyOn($scope, "refreshDestinohorario");
    	var id = 1;
    	
    	// when
    	$scope.goToDestinohorario(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshDestinohorario).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/destinohorario'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.destinohorario = {id:'1', name:'destinohorario'};
    	
    	$scope.mode = 'create';
    	Destinohorario.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'destinohorarioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.destinohorario).toBe('destinohorarioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.destinohorario = {id:'1', name:'destinohorario'};
    	
    	$scope.mode = 'update';
    	Destinohorario.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'destinohorarioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.destinohorario).toBe('destinohorarioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Destinohorario.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToDestinohorarioList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToDestinohorarioList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : destinohorario create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/destinohorario/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.destinohorario).toBeNull();
    	expect($scope.destinohorarios).toBe('destinohorario1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});