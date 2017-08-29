'use strict';

/**
 * Controller for Tipocartera
 **/
tipocarteraModule.controller('TipocarteraCtrl', ['Tipocartera',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Tipocartera, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of tipocarteras
    $scope.tipocarteras = [];
	// tipocartera to edit
    $scope.tipocartera = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh tipocarteras list
     */
    $scope.refreshTipocarteraList = function() {
    	try {
			$scope.tipocarteras = [];
        	Tipocartera.getAll().then(
				function(success) {
        	        $scope.tipocarteras = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh tipocartera
     */
    $scope.refreshTipocartera = function(id) {
    	try {
        	$scope.tipocartera = null;
	        Tipocartera.get(id).then(
				function(success) {
        	        $scope.tipocartera = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the tipocarteras list page
     */
    $scope.goToTipocarteraList = function() {
        $scope.refreshTipocarteraList();
        $location.path('/tipocartera');
    }
    /**
     * Go to the tipocartera edit page
     */
    $scope.goToTipocartera = function(id) {
        $scope.refreshTipocartera(id);
        $location.path('/tipocartera/'+id);
    }

    // Actions

    /**
     * Save tipocartera
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Tipocartera.create;
			} else {
				save = Tipocartera.update;
			}
			save($scope.tipocartera).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.tipocartera = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete tipocartera
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Tipocartera.delete(id).then(
				function(success) {
                	$scope.goToTipocarteraList();
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
        $scope.tipocartera = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshTipocartera($routeParams.id);
    } else {
        // List page
        $scope.refreshTipocarteraList();
    }
    
    
}]);
