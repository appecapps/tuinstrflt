'use strict';

/**
 * Controller for Viaje
 **/
viajeModule.controller('ViajeCtrl', ['Viaje',  'Chofervehiculo', 'Destinohorario', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Viaje, Chofervehiculo, Destinohorario, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Chofervehiculo',  'Destinohorario',     // edition mode
    $scope.mode = null;
    
	// list of viajes
    $scope.viajes = [];
	// viaje to edit
    $scope.viaje = null;

	// referencies entities
	$scope.items = {};
    // chofervehiculos
	$scope.items.chofervehiculos = [];
    // destinohorarios
	$scope.items.destinohorarios = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Chofervehiculo.getAllAsListItems().then(
				function(success) {
        	        $scope.items.chofervehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
		Destinohorario.getAllAsListItems().then(
				function(success) {
        	        $scope.items.destinohorarios = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh viajes list
     */
    $scope.refreshViajeList = function() {
    	try {
			$scope.viajes = [];
        	Viaje.getAll().then(
				function(success) {
        	        $scope.viajes = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh viaje
     */
    $scope.refreshViaje = function(id) {
    	try {
        	$scope.viaje = null;
	        Viaje.get(id).then(
				function(success) {
        	        $scope.viaje = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the viajes list page
     */
    $scope.goToViajeList = function() {
        $scope.refreshViajeList();
        $location.path('/viaje');
    }
    /**
     * Go to the viaje edit page
     */
    $scope.goToViaje = function(id) {
        $scope.refreshViaje(id);
        $location.path('/viaje/'+id);
    }

    // Actions

    /**
     * Save viaje
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Viaje.create;
			} else {
				save = Viaje.update;
			}
			save($scope.viaje).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.viaje = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete viaje
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Viaje.delete(id).then(
				function(success) {
                	$scope.goToViajeList();
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
        $scope.viaje = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshViaje($routeParams.id);
    } else {
        // List page
        $scope.refreshViajeList();
    }
    
    
}]);
