'use strict';

/**
 * Controller for Entidad
 **/
entidadModule.controller('EntidadCtrl', ['Entidad',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Entidad, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of entidads
    $scope.entidads = [];
	// entidad to edit
    $scope.entidad = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh entidads list
     */
    $scope.refreshEntidadList = function() {
    	try {
			$scope.entidads = [];
        	Entidad.getAll().then(
				function(success) {
        	        $scope.entidads = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh entidad
     */
    $scope.refreshEntidad = function(id) {
    	try {
        	$scope.entidad = null;
	        Entidad.get(id).then(
				function(success) {
        	        $scope.entidad = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the entidads list page
     */
    $scope.goToEntidadList = function() {
        $scope.refreshEntidadList();
        $location.path('/entidad');
    }
    /**
     * Go to the entidad edit page
     */
    $scope.goToEntidad = function(id) {
        $scope.refreshEntidad(id);
        $location.path('/entidad/'+id);
    }

    // Actions

    /**
     * Save entidad
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Entidad.create;
			} else {
				save = Entidad.update;
			}
			save($scope.entidad).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.entidad = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete entidad
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Entidad.delete(id).then(
				function(success) {
                	$scope.goToEntidadList();
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
        $scope.entidad = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshEntidad($routeParams.id);
    } else {
        // List page
        $scope.refreshEntidadList();
    }
    
    
}]);
