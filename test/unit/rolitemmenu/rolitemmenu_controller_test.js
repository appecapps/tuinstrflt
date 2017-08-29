'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('rolitemmenu.module'));
  
  describe('RolitemmenuCtrl', function(){
    var RolitemmenuCtrl, Rolitemmenu, Itemmenu,  Rol, $rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Rolitemmenu service
    	Rolitemmenu = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'rolitemmenu1'});
    			return deferred.promise;
    		}
    	};
		
				Itemmenu = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				Rol = {
			getAllAsListItems: jasmine.createSpy("getAllAsListItems").andCallFake(function() {
				return [];
			})
		};

				RolitemmenuCtrl = $controller('RolitemmenuCtrl', {
    		'Rolitemmenu': Rolitemmenu,
						'Itemmenu': Itemmenu,
						'Rol': Rol,
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
    	expect($scope.rolitemmenu).toBeNull();
    	expect($scope.rolitemmenus).toBe('rolitemmenu1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshRolitemmenuList', function() {
    	// given
    	Rolitemmenu.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rolitemmenu2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshRolitemmenuList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.rolitemmenus).toBe('rolitemmenu2');
    });
    
    it('refreshRolitemmenu', function() {
    	// given
    	Rolitemmenu.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'rolitemmenu'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshRolitemmenu('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.rolitemmenu).toBe('rolitemmenu'+'1');
    });
    
	it('goToRolitemmenuList', function() {
    	// given
    	spyOn($scope, "refreshRolitemmenuList");
    	
    	// when
    	$scope.goToRolitemmenuList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshRolitemmenuList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/rolitemmenu');
    });
    
    it('goToRolitemmenu', function() {
    	// given
    	spyOn($scope, "refreshRolitemmenu");
    	var id = 1;
    	
    	// when
    	$scope.goToRolitemmenu(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshRolitemmenu).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/rolitemmenu'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.rolitemmenu = {id:'1', name:'rolitemmenu'};
    	
    	$scope.mode = 'create';
    	Rolitemmenu.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rolitemmenuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.rolitemmenu).toBe('rolitemmenuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.rolitemmenu = {id:'1', name:'rolitemmenu'};
    	
    	$scope.mode = 'update';
    	Rolitemmenu.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'rolitemmenuSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.rolitemmenu).toBe('rolitemmenuSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Rolitemmenu.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToRolitemmenuList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToRolitemmenuList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : rolitemmenu create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/rolitemmenu/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.rolitemmenu).toBeNull();
    	expect($scope.rolitemmenus).toBe('rolitemmenu1');
    	expect(Object.keys($scope.items).length).toBe(2);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});