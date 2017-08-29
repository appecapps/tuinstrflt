'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('color.module'));
  
  describe('ColorCtrl', function(){
    var ColorCtrl, Color,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Color service
    	Color = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'color1'});
    			return deferred.promise;
    		}
    	};
		
				ColorCtrl = $controller('ColorCtrl', {
    		'Color': Color,
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
    	expect($scope.color).toBeNull();
    	expect($scope.colors).toBe('color1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshColorList', function() {
    	// given
    	Color.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'color2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshColorList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.colors).toBe('color2');
    });
    
    it('refreshColor', function() {
    	// given
    	Color.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'color'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshColor('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.color).toBe('color'+'1');
    });
    
	it('goToColorList', function() {
    	// given
    	spyOn($scope, "refreshColorList");
    	
    	// when
    	$scope.goToColorList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshColorList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/color');
    });
    
    it('goToColor', function() {
    	// given
    	spyOn($scope, "refreshColor");
    	var id = 1;
    	
    	// when
    	$scope.goToColor(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshColor).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/color'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.color = {id:'1', name:'color'};
    	
    	$scope.mode = 'create';
    	Color.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'colorSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.color).toBe('colorSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.color = {id:'1', name:'color'};
    	
    	$scope.mode = 'update';
    	Color.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'colorSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.color).toBe('colorSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Color.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToColorList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToColorList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : color create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/color/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.color).toBeNull();
    	expect($scope.colors).toBe('color1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});