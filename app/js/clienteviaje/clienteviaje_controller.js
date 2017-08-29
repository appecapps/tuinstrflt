'use strict';

/**
 * Controller for Clienteviaje
 **/
clienteviajeModule.controller('ClienteviajeCtrl', ['Clienteviaje',  'Viaje', 'Cliente', 'Estado', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Clienteviaje, Viaje, Cliente, Estado, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Viaje',  'Cliente',  'Estado',     // edition mode
    $scope.mode = null;
    
	// list of clienteviajes
    $scope.clienteviajes = [];
	// clienteviaje to edit
    $scope.clienteviaje = null;

	// referencies entities
	$scope.items = {};
    // viajes
	$scope.items.viajes = [];
    // clientes
	$scope.items.clientes = [];
    // estados
	$scope.items.estados = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Viaje.getAllAsListItems().then(
				function(success) {
        	        $scope.items.viajes = success.data;
            	}, 
	            MessageHandler.manageError);
		Cliente.getAllAsListItems().then(
				function(success) {
        	        $scope.items.clientes = success.data;
            	}, 
	            MessageHandler.manageError);
		Estado.getAllAsListItems().then(
				function(success) {
        	        $scope.items.estados = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh clienteviajes list
     */
    $scope.refreshClienteviajeList = function() {
    	try {
			$scope.clienteviajes = [];
        	Clienteviaje.getAll().then(
				function(success) {
        	        $scope.clienteviajes = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh clienteviaje
     */
    $scope.refreshClienteviaje = function(id) {
    	try {
        	$scope.clienteviaje = null;
	        Clienteviaje.get(id).then(
				function(success) {
        	        $scope.clienteviaje = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the clienteviajes list page
     */
    $scope.goToClienteviajeList = function() {
        $scope.refreshClienteviajeList();
        $location.path('/clienteviaje');
    }
    /**
     * Go to the clienteviaje edit page
     */
    $scope.goToClienteviaje = function(id) {
        $scope.refreshClienteviaje(id);
        $location.path('/clienteviaje/'+id);
    }

    // Actions

    /**
     * Save clienteviaje
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Clienteviaje.create;
			} else {
				save = Clienteviaje.update;
			}
			save($scope.clienteviaje).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.clienteviaje = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete clienteviaje
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Clienteviaje.delete(id).then(
				function(success) {
                	$scope.goToClienteviajeList();
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
        $scope.clienteviaje = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshClienteviaje($routeParams.id);
    } else {
        // List page
        $scope.refreshClienteviajeList();
    }
    
    
}]);
