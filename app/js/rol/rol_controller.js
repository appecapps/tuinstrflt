'use strict';

/**
 * Controller for Rol
 **/
rolModule.controller('RolCtrl', ['Rol',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Rol, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of rols
    $scope.rols = [];
	// rol to edit
    $scope.rol = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh rols list
     */
    $scope.refreshRolList = function() {
    	try {
			$scope.rols = [];
        	Rol.getAll().then(
				function(success) {
        	        $scope.rols = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh rol
     */
    $scope.refreshRol = function(id) {
    	try {
        	$scope.rol = null;
	        Rol.get(id).then(
				function(success) {
        	        $scope.rol = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the rols list page
     */
    $scope.goToRolList = function() {
        $scope.refreshRolList();
        $location.path('/rol');
    }
    /**
     * Go to the rol edit page
     */
    $scope.goToRol = function(id) {
        $scope.refreshRol(id);
        $location.path('/rol/'+id);
    }

    // Actions

    /**
     * Save rol
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Rol.create;
			} else {
				save = Rol.update;
			}
			save($scope.rol).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.rol = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete rol
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Rol.delete(id).then(
				function(success) {
                	$scope.goToRolList();
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
        $scope.rol = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshRol($routeParams.id);
    } else {
        // List page
        $scope.refreshRolList();
    }
    
    
}]);
