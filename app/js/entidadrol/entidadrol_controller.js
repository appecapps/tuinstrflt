'use strict';

/**
 * Controller for Entidadrol
 **/
entidadrolModule.controller('EntidadrolCtrl', ['Entidadrol',  'Rol', 'Entidad', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Entidadrol, Rol, Entidad, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Rol',  'Entidad',     // edition mode
    $scope.mode = null;
    
	// list of entidadrols
    $scope.entidadrols = [];
	// entidadrol to edit
    $scope.entidadrol = null;

	// referencies entities
	$scope.items = {};
    // rols
	$scope.items.rols = [];
    // entidads
	$scope.items.entidads = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Rol.getAllAsListItems().then(
				function(success) {
        	        $scope.items.rols = success.data;
            	}, 
	            MessageHandler.manageError);
		Entidad.getAllAsListItems().then(
				function(success) {
        	        $scope.items.entidads = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh entidadrols list
     */
    $scope.refreshEntidadrolList = function() {
    	try {
			$scope.entidadrols = [];
        	Entidadrol.getAll().then(
				function(success) {
        	        $scope.entidadrols = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh entidadrol
     */
    $scope.refreshEntidadrol = function(id) {
    	try {
        	$scope.entidadrol = null;
	        Entidadrol.get(id).then(
				function(success) {
        	        $scope.entidadrol = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the entidadrols list page
     */
    $scope.goToEntidadrolList = function() {
        $scope.refreshEntidadrolList();
        $location.path('/entidadrol');
    }
    /**
     * Go to the entidadrol edit page
     */
    $scope.goToEntidadrol = function(id) {
        $scope.refreshEntidadrol(id);
        $location.path('/entidadrol/'+id);
    }

    // Actions

    /**
     * Save entidadrol
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Entidadrol.create;
			} else {
				save = Entidadrol.update;
			}
			save($scope.entidadrol).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.entidadrol = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete entidadrol
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Entidadrol.delete(id).then(
				function(success) {
                	$scope.goToEntidadrolList();
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
        $scope.entidadrol = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshEntidadrol($routeParams.id);
    } else {
        // List page
        $scope.refreshEntidadrolList();
    }
    
    
}]);
