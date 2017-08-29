'use strict';

/**
 * Controller for Provincia
 **/
provinciaModule.controller('ProvinciaCtrl', ['Provincia',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Provincia, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of provincias
    $scope.provincias = [];
	// provincia to edit
    $scope.provincia = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh provincias list
     */
    $scope.refreshProvinciaList = function() {
    	try {
			$scope.provincias = [];
        	Provincia.getAll().then(
				function(success) {
        	        $scope.provincias = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh provincia
     */
    $scope.refreshProvincia = function(id) {
    	try {
        	$scope.provincia = null;
	        Provincia.get(id).then(
				function(success) {
        	        $scope.provincia = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the provincias list page
     */
    $scope.goToProvinciaList = function() {
        $scope.refreshProvinciaList();
        $location.path('/provincia');
    }
    /**
     * Go to the provincia edit page
     */
    $scope.goToProvincia = function(id) {
        $scope.refreshProvincia(id);
        $location.path('/provincia/'+id);
    }

    // Actions

    /**
     * Save provincia
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Provincia.create;
			} else {
				save = Provincia.update;
			}
			save($scope.provincia).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.provincia = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete provincia
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Provincia.delete(id).then(
				function(success) {
                	$scope.goToProvinciaList();
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
        $scope.provincia = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshProvincia($routeParams.id);
    } else {
        // List page
        $scope.refreshProvinciaList();
    }
    
    
}]);
