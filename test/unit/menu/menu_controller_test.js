'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('menu.module'));
  
  describe('MenuCtrl', function(){
    var MenuCtrl, Menu,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Menu service
    	Menu = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'menu1'});
    			return deferred.promise;
    		}
    	};
		
				MenuCtrl = $controller('MenuCtrl', {
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
    	expect($scope.menu).toBeNull();
    	expect($scope.menus).toBe('menu1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshMenuList', function() {
    	// given
    	Menu.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'menu2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshMenuList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.menus).toBe('menu2');
    });
    
    it('refreshMenu', function() {
    	// given
    	Menu.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'menu'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshMenu('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.menu).toBe('menu'+'1');
    });
    
	it('goToMenuList', function() {
    	// given
    	spyOn($scope, "refreshMenuList");
    	
    	// when
    	$scope.goToMenuList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshMenuList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/menu');
    });
    
    it('goToMenu', function() {
    	// given
    	spyOn($scope, "refreshMenu");
    	var id = 1;
    	
    	// when
    	$scope.goToMenu(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshMenu).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/menu'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.menu = {id:'1', name:'menu'};
    	
    	$scope.mode = 'create';
    	Menu.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'menuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.menu).toBe('menuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.menu = {id:'1', name:'menu'};
    	
    	$scope.mode = 'update';
    	Menu.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'menuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.menu).toBe('menuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Menu.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToMenuList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToMenuList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : menu create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/menu/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.menu).toBeNull();
    	expect($scope.menus).toBe('menu1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});