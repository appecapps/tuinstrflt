'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('itemmenu.module'));
  
  describe('ItemmenuCtrl', function(){
    var ItemmenuCtrl, Itemmenu, Menu, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Itemmenu service
    	Itemmenu = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'itemmenu1'});
    			return deferred.promise;
    		}
    	};
		
				Menu = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				ItemmenuCtrl = $controller('ItemmenuCtrl', {
    		'Itemmenu': Itemmenu,
						'Menu': Menu,
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
    	expect($scope.itemmenu).toBeNull();
    	expect($scope.itemmenus).toBe('itemmenu1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshItemmenuList', function() {
    	// given
    	Itemmenu.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'itemmenu2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshItemmenuList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.itemmenus).toBe('itemmenu2');
    });
    
    it('refreshItemmenu', function() {
    	// given
    	Itemmenu.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'itemmenu'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshItemmenu('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.itemmenu).toBe('itemmenu'+'1');
    });
    
	it('goToItemmenuList', function() {
    	// given
    	spyOn($scope, "refreshItemmenuList");
    	
    	// when
    	$scope.goToItemmenuList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshItemmenuList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/itemmenu');
    });
    
    it('goToItemmenu', function() {
    	// given
    	spyOn($scope, "refreshItemmenu");
    	var id = 1;
    	
    	// when
    	$scope.goToItemmenu(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshItemmenu).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/itemmenu'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.itemmenu = {id:'1', name:'itemmenu'};
    	
    	$scope.mode = 'create';
    	Itemmenu.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'itemmenuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.itemmenu).toBe('itemmenuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.itemmenu = {id:'1', name:'itemmenu'};
    	
    	$scope.mode = 'update';
    	Itemmenu.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'itemmenuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.itemmenu).toBe('itemmenuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Itemmenu.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToItemmenuList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToItemmenuList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : itemmenu create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/itemmenu/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.itemmenu).toBeNull();
    	expect($scope.itemmenus).toBe('itemmenu1');
    	expect(Object.keys($scope.items).length).toBe(1);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});