'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function(){
  beforeEach(module('horario.module'));
  
  describe('HorarioCtrl', function(){
    var HorarioCtrl, Horario,$rootScope, $scope, $routeParams, $httpBackend, $location, MessageHandler, $q, $controller;
	  
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

    	// Horario service
    	Horario = {
    		getAll: function() {
    			var deferred = $q.defer();
    			deferred.resolve({data:'horario1'});
    			return deferred.promise;
    		}
    	};
		
				HorarioCtrl = $controller('HorarioCtrl', {
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
    	expect($scope.horario).toBeNull();
    	expect($scope.horarios).toBe('horario1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('refreshHorarioList', function() {
    	// given
    	Horario.getAll = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'horario2'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshHorarioList();
    	$scope.$apply();

    	// then
    	$rootScope.$apply();
    	expect($scope.horarios).toBe('horario2');
    });
    
    it('refreshHorario', function() {
    	// given
    	Horario.get = function(id) {
			var deferred = $q.defer();
			deferred.resolve({data:'horario'+id});
			return deferred.promise;
		}
    	
    	// when
    	$scope.refreshHorario('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.horario).toBe('horario'+'1');
    });
    
	it('goToHorarioList', function() {
    	// given
    	spyOn($scope, "refreshHorarioList");
    	
    	// when
    	$scope.goToHorarioList();
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshHorarioList).toHaveBeenCalled();
    	expect($location.path).toHaveBeenCalledWith('/horario');
    });
    
    it('goToHorario', function() {
    	// given
    	spyOn($scope, "refreshHorario");
    	var id = 1;
    	
    	// when
    	$scope.goToHorario(id);
    	$scope.$apply();
    	
    	// then
    	expect($scope.refreshHorario).toHaveBeenCalledWith(id);
    	expect($location.path).toHaveBeenCalledWith('/horario'+'/'+id);
    });
    
    it('save : create', function() {
    	// given
    	$scope.horario = {id:'1', name:'horario'};
    	
    	$scope.mode = 'create';
    	Horario.create = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'horarioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.horario).toBe('horarioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('save : update', function() {
    	// given
    	$scope.horario = {id:'1', name:'horario'};
    	
    	$scope.mode = 'update';
    	Horario.update = function() {
			var deferred = $q.defer();
			deferred.resolve({data:'horarioSaved'});
			return deferred.promise;
		}
    	
    	// when
    	$scope.save();
    	$scope.$apply();
    	
    	// then
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    	expect($scope.horario).toBe('horarioSaved');
    	expect(MessageHandler.addSuccess).toHaveBeenCalledWith('save ok');
    });
    
    it('delete', function() {
    	// given
    	Horario.delete = function() {
			var deferred = $q.defer();
			deferred.resolve(null);
			return deferred.promise;
		}
    	spyOn($scope, "goToHorarioList");
    	
    	// when
    	$scope.delete('1');
    	$scope.$apply();
    	
    	// then
    	expect($scope.goToHorarioList).toHaveBeenCalled();
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
    
    it('init : horario create page', function() {
    	// given
		$location.path.andCallFake(function() {
        	return "/horario/new";
       	});

		// when
		$scope.$apply();
		
		// then
    	expect($scope.mode).toBeNull();
    	expect($scope.horario).toBeNull();
    	expect($scope.horarios).toBe('horario1');
    	expect(Object.keys($scope.items).length).toBe(0);
    	expect(MessageHandler.cleanMessage).toHaveBeenCalled();
    });
	
  });
});