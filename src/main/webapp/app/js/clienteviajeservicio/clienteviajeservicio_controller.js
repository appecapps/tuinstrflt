'use strict';

/**
 * Controller for Clienteviajeservicio
 **/
clienteviajeservicioModule.controller('ClienteviajeservicioCtrl', ['Clienteviajeservicio',  'Clienteviaje', 'Vehiculoservicio', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Clienteviajeservicio, Clienteviaje, Vehiculoservicio, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Clienteviaje',  'Vehiculoservicio',     // edition mode
    $scope.mode = null;
    
	// list of clienteviajeservicios
    $scope.clienteviajeservicios = [];
	// clienteviajeservicio to edit
    $scope.clienteviajeservicio = null;

	// referencies entities
	$scope.items = {};
    // clienteviajes
	$scope.items.clienteviajes = [];
    // vehiculoservicios
	$scope.items.vehiculoservicios = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Clienteviaje.getAllAsListItems().then(
				function(success) {
        	        $scope.items.clienteviajes = success.data;
            	}, 
	            MessageHandler.manageError);
		Vehiculoservicio.getAllAsListItems().then(
				function(success) {
        	        $scope.items.vehiculoservicios = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh clienteviajeservicios list
     */
    $scope.refreshClienteviajeservicioList = function() {
    	try {
			$scope.clienteviajeservicios = [];
        	Clienteviajeservicio.getAll().then(
				function(success) {
        	        $scope.clienteviajeservicios = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh clienteviajeservicio
     */
    $scope.refreshClienteviajeservicio = function(id) {
    	try {
        	$scope.clienteviajeservicio = null;
	        Clienteviajeservicio.get(id).then(
				function(success) {
        	        $scope.clienteviajeservicio = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the clienteviajeservicios list page
     */
    $scope.goToClienteviajeservicioList = function() {
        $scope.refreshClienteviajeservicioList();
        $location.path('/clienteviajeservicio');
    }
    /**
     * Go to the clienteviajeservicio edit page
     */
    $scope.goToClienteviajeservicio = function(id) {
        $scope.refreshClienteviajeservicio(id);
        $location.path('/clienteviajeservicio/'+id);
    }

    // Actions

    /**
     * Save clienteviajeservicio
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Clienteviajeservicio.create;
			} else {
				save = Clienteviajeservicio.update;
			}
			save($scope.clienteviajeservicio).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.clienteviajeservicio = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete clienteviajeservicio
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Clienteviajeservicio.delete(id).then(
				function(success) {
                	$scope.goToClienteviajeservicioList();
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
        $scope.clienteviajeservicio = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshClienteviajeservicio($routeParams.id);
    } else {
        // List page
        $scope.refreshClienteviajeservicioList();
    }
    
    
}]);
