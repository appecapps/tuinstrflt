'use strict';

/**
 * Controller for Horario
 **/
horarioModule.controller('HorarioCtrl', ['Horario',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Horario, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of horarios
    $scope.horarios = [];
	// horario to edit
    $scope.horario = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh horarios list
     */
    $scope.refreshHorarioList = function() {
    	try {
			$scope.horarios = [];
        	Horario.getAll().then(
				function(success) {
        	        $scope.horarios = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh horario
     */
    $scope.refreshHorario = function(id) {
    	try {
        	$scope.horario = null;
	        Horario.get(id).then(
				function(success) {
        	        $scope.horario = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the horarios list page
     */
    $scope.goToHorarioList = function() {
        $scope.refreshHorarioList();
        $location.path('/horario');
    }
    /**
     * Go to the horario edit page
     */
    $scope.goToHorario = function(id) {
        $scope.refreshHorario(id);
        $location.path('/horario/'+id);
    }

    // Actions

    /**
     * Save horario
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Horario.create;
			} else {
				save = Horario.update;
			}
			save($scope.horario).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.horario = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete horario
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Horario.delete(id).then(
				function(success) {
                	$scope.goToHorarioList();
            	}, 
                MessageHandler.manageError);
        } catch(ex) {
            MessageHandler.manageException(ex);
        }
    };
    
    // Main
	MessageHandler.cleanMessage();
    if( $location.path().endsWith('/new') ) {
        // Creation page
        $scope.horario = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshHorario($routeParams.id);
    } else {
        // List page
        $scope.refreshHorarioList();
    }
    
    
}]);
