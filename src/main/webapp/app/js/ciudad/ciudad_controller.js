'use strict';

/**
 * Controller for Ciudad
 **/
ciudadModule.controller('CiudadCtrl', ['Ciudad',  'Provincia', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Ciudad, Provincia, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Provincia',     // edition mode
    $scope.mode = null;
    
	// list of ciudads
    $scope.ciudads = [];
	// ciudad to edit
    $scope.ciudad = null;

	// referencies entities
	$scope.items = {};
    // provincias
	$scope.items.provincias = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Provincia.getAllAsListItems().then(
				function(success) {
        	        $scope.items.provincias = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh ciudads list
     */
    $scope.refreshCiudadList = function() {
    	try {
			$scope.ciudads = [];
        	Ciudad.getAll().then(
				function(success) {
        	        $scope.ciudads = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh ciudad
     */
    $scope.refreshCiudad = function(id) {
    	try {
        	$scope.ciudad = null;
	        Ciudad.get(id).then(
				function(success) {
        	        $scope.ciudad = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the ciudads list page
     */
    $scope.goToCiudadList = function() {
        $scope.refreshCiudadList();
        $location.path('/ciudad');
    }
    /**
     * Go to the ciudad edit page
     */
    $scope.goToCiudad = function(id) {
        $scope.refreshCiudad(id);
        $location.path('/ciudad/'+id);
    }

    // Actions

    /**
     * Save ciudad
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Ciudad.create;
			} else {
				save = Ciudad.update;
			}
			save($scope.ciudad).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.ciudad = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete ciudad
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Ciudad.delete(id).then(
				function(success) {
                	$scope.goToCiudadList();
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
        $scope.ciudad = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshCiudad($routeParams.id);
    } else {
        // List page
        $scope.refreshCiudadList();
    }
    
    
}]);
