'use strict';

/**
 * Controller for Estado
 **/
estadoModule.controller('EstadoCtrl', ['Estado',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Estado, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of estados
    $scope.estados = [];
	// estado to edit
    $scope.estado = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh estados list
     */
    $scope.refreshEstadoList = function() {
    	try {
			$scope.estados = [];
        	Estado.getAll().then(
				function(success) {
        	        $scope.estados = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh estado
     */
    $scope.refreshEstado = function(id) {
    	try {
        	$scope.estado = null;
	        Estado.get(id).then(
				function(success) {
        	        $scope.estado = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the estados list page
     */
    $scope.goToEstadoList = function() {
        $scope.refreshEstadoList();
        $location.path('/estado');
    }
    /**
     * Go to the estado edit page
     */
    $scope.goToEstado = function(id) {
        $scope.refreshEstado(id);
        $location.path('/estado/'+id);
    }

    // Actions

    /**
     * Save estado
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Estado.create;
			} else {
				save = Estado.update;
			}
			save($scope.estado).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.estado = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete estado
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Estado.delete(id).then(
				function(success) {
                	$scope.goToEstadoList();
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
        $scope.estado = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshEstado($routeParams.id);
    } else {
        // List page
        $scope.refreshEstadoList();
    }
    
    
}]);
