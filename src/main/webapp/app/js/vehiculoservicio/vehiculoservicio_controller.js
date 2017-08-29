'use strict';

/**
 * Controller for Vehiculoservicio
 **/
vehiculoservicioModule.controller('VehiculoservicioCtrl', ['Vehiculoservicio',  'Servicio', 'Vehiculo', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Vehiculoservicio, Servicio, Vehiculo, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Servicio',  'Vehiculo',     // edition mode
    $scope.mode = null;
    
	// list of vehiculoservicios
    $scope.vehiculoservicios = [];
	// vehiculoservicio to edit
    $scope.vehiculoservicio = null;

	// referencies entities
	$scope.items = {};
    // servicios
	$scope.items.servicios = [];
    // vehiculos
	$scope.items.vehiculos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Servicio.getAllAsListItems().then(
				function(success) {
        	        $scope.items.servicios = success.data;
            	}, 
	            MessageHandler.manageError);
		Vehiculo.getAllAsListItems().then(
				function(success) {
        	        $scope.items.vehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh vehiculoservicios list
     */
    $scope.refreshVehiculoservicioList = function() {
    	try {
			$scope.vehiculoservicios = [];
        	Vehiculoservicio.getAll().then(
				function(success) {
        	        $scope.vehiculoservicios = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh vehiculoservicio
     */
    $scope.refreshVehiculoservicio = function(id) {
    	try {
        	$scope.vehiculoservicio = null;
	        Vehiculoservicio.get(id).then(
				function(success) {
        	        $scope.vehiculoservicio = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the vehiculoservicios list page
     */
    $scope.goToVehiculoservicioList = function() {
        $scope.refreshVehiculoservicioList();
        $location.path('/vehiculoservicio');
    }
    /**
     * Go to the vehiculoservicio edit page
     */
    $scope.goToVehiculoservicio = function(id) {
        $scope.refreshVehiculoservicio(id);
        $location.path('/vehiculoservicio/'+id);
    }

    // Actions

    /**
     * Save vehiculoservicio
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Vehiculoservicio.create;
			} else {
				save = Vehiculoservicio.update;
			}
			save($scope.vehiculoservicio).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.vehiculoservicio = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete vehiculoservicio
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Vehiculoservicio.delete(id).then(
				function(success) {
                	$scope.goToVehiculoservicioList();
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
        $scope.vehiculoservicio = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshVehiculoservicio($routeParams.id);
    } else {
        // List page
        $scope.refreshVehiculoservicioList();
    }
    
    
}]);
