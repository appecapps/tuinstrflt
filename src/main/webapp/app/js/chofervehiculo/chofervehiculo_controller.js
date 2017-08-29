'use strict';

/**
 * Controller for Chofervehiculo
 **/
chofervehiculoModule.controller('ChofervehiculoCtrl', ['Chofervehiculo',  'Chofer', 'Vehiculo', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Chofervehiculo, Chofer, Vehiculo, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Chofer',  'Vehiculo',     // edition mode
    $scope.mode = null;
    
	// list of chofervehiculos
    $scope.chofervehiculos = [];
	// chofervehiculo to edit
    $scope.chofervehiculo = null;

	// referencies entities
	$scope.items = {};
    // chofers
	$scope.items.chofers = [];
    // vehiculos
	$scope.items.vehiculos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Chofer.getAllAsListItems().then(
				function(success) {
        	        $scope.items.chofers = success.data;
            	}, 
	            MessageHandler.manageError);
		Vehiculo.getAllAsListItems().then(
				function(success) {
        	        $scope.items.vehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh chofervehiculos list
     */
    $scope.refreshChofervehiculoList = function() {
    	try {
			$scope.chofervehiculos = [];
        	Chofervehiculo.getAll().then(
				function(success) {
        	        $scope.chofervehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh chofervehiculo
     */
    $scope.refreshChofervehiculo = function(id) {
    	try {
        	$scope.chofervehiculo = null;
	        Chofervehiculo.get(id).then(
				function(success) {
        	        $scope.chofervehiculo = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the chofervehiculos list page
     */
    $scope.goToChofervehiculoList = function() {
        $scope.refreshChofervehiculoList();
        $location.path('/chofervehiculo');
    }
    /**
     * Go to the chofervehiculo edit page
     */
    $scope.goToChofervehiculo = function(id) {
        $scope.refreshChofervehiculo(id);
        $location.path('/chofervehiculo/'+id);
    }

    // Actions

    /**
     * Save chofervehiculo
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Chofervehiculo.create;
			} else {
				save = Chofervehiculo.update;
			}
			save($scope.chofervehiculo).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.chofervehiculo = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete chofervehiculo
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Chofervehiculo.delete(id).then(
				function(success) {
                	$scope.goToChofervehiculoList();
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
        $scope.chofervehiculo = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshChofervehiculo($routeParams.id);
    } else {
        // List page
        $scope.refreshChofervehiculoList();
    }
    
    
}]);
