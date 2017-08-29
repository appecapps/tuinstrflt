'use strict';

/**
 * Controller for Servicio
 **/
servicioModule.controller('ServicioCtrl', ['Servicio',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Servicio, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of servicios
    $scope.servicios = [];
	// servicio to edit
    $scope.servicio = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh servicios list
     */
    $scope.refreshServicioList = function() {
    	try {
			$scope.servicios = [];
        	Servicio.getAll().then(
				function(success) {
        	        $scope.servicios = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh servicio
     */
    $scope.refreshServicio = function(id) {
    	try {
        	$scope.servicio = null;
	        Servicio.get(id).then(
				function(success) {
        	        $scope.servicio = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the servicios list page
     */
    $scope.goToServicioList = function() {
        $scope.refreshServicioList();
        $location.path('/servicio');
    }
    /**
     * Go to the servicio edit page
     */
    $scope.goToServicio = function(id) {
        $scope.refreshServicio(id);
        $location.path('/servicio/'+id);
    }

    // Actions

    /**
     * Save servicio
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Servicio.create;
			} else {
				save = Servicio.update;
			}
			save($scope.servicio).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.servicio = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete servicio
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Servicio.delete(id).then(
				function(success) {
                	$scope.goToServicioList();
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
        $scope.servicio = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshServicio($routeParams.id);
    } else {
        // List page
        $scope.refreshServicioList();
    }
    
    
}]);
