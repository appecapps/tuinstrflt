'use strict';

/**
 * Controller for Vehiculo
 **/
vehiculoModule.controller('VehiculoCtrl', ['Vehiculo',  'Modelo', 'Color', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Vehiculo, Modelo, Color, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Modelo',  'Color',     // edition mode
    $scope.mode = null;
    
	// list of vehiculos
    $scope.vehiculos = [];
	// vehiculo to edit
    $scope.vehiculo = null;

	// referencies entities
	$scope.items = {};
    // modelos
	$scope.items.modelos = [];
    // colors
	$scope.items.colors = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Modelo.getAllAsListItems().then(
				function(success) {
        	        $scope.items.modelos = success.data;
            	}, 
	            MessageHandler.manageError);
		Color.getAllAsListItems().then(
				function(success) {
        	        $scope.items.colors = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh vehiculos list
     */
    $scope.refreshVehiculoList = function() {
    	try {
			$scope.vehiculos = [];
        	Vehiculo.getAll().then(
				function(success) {
        	        $scope.vehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh vehiculo
     */
    $scope.refreshVehiculo = function(id) {
    	try {
        	$scope.vehiculo = null;
	        Vehiculo.get(id).then(
				function(success) {
        	        $scope.vehiculo = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the vehiculos list page
     */
    $scope.goToVehiculoList = function() {
        $scope.refreshVehiculoList();
        $location.path('/vehiculo');
    }
    /**
     * Go to the vehiculo edit page
     */
    $scope.goToVehiculo = function(id) {
        $scope.refreshVehiculo(id);
        $location.path('/vehiculo/'+id);
    }

    // Actions

    /**
     * Save vehiculo
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Vehiculo.create;
			} else {
				save = Vehiculo.update;
			}
			save($scope.vehiculo).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.vehiculo = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete vehiculo
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Vehiculo.delete(id).then(
				function(success) {
                	$scope.goToVehiculoList();
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
        $scope.vehiculo = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshVehiculo($routeParams.id);
    } else {
        // List page
        $scope.refreshVehiculoList();
    }
    
    
}]);
