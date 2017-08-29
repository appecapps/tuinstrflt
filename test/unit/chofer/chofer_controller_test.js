'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('chofer.module'));
  
  describe('ChoferCtrl', function(){
    var ChoferCtrl, Chofer, Entidad, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Chofer service
    	Chofer = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'chofer1'});
    			return deferred.promise;
    		}
    	};
		
				Entidad = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ChoferCtrl = $controller('ChoferCtrl', {
    		'Chofer': Chofer,
						'Entidad': Entidad,
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
    	expect($scope.chofer).toBeNull();
    	expect($scope.chofers).toBe('chofer1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshChoferList', function() {
    	// given
    	Chofer.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'chofer2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshChoferList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.chofers).toBe('chofer2');
    });
    
    it('refreshChofer', function() {
    	// given
    	Chofer.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'chofer'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshChofer('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.chofer).toBe('chofer'+'1');
    });
    
	it('goToChoferList', function() {
    	// given
    	spyOn($scope, "refreshChoferList");
    	
    	// when
    	$scope.goToChoferList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshChoferList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/chofer');
    });
    
    it('goToChofer', function() {
    	// given
    	spyOn($scope, "refreshChofer");
    	var id = 1;
    	
    	// when
    	$scope.goToChofer(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshChofer).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/chofer'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.chofer = {id:'1', name:'chofer'};
    	
    	$scope.mode = 'create';
    	Chofer.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'choferSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.chofer).toBe('choferSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.chofer = {id:'1', name:'chofer'};
    	
    	$scope.mode = 'update';
    	Chofer.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'choferSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.chofer).toBe('choferSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Chofer.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToChoferList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToChoferList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : chofer create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/chofer/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.chofer).toBeNull();
    	expect($scope.chofers).toBe('chofer1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});