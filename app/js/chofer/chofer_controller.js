'use strict';

/**
 * Controller for Chofer
 **/
choferModule.controller('ChoferCtrl', ['Chofer',  'Entidad', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Chofer, Entidad, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Entidad',     // edition mode
    $scope.mode = null;
    
	// list of chofers
    $scope.chofers = [];
	// chofer to edit
    $scope.chofer = null;

	// referencies entities
	$scope.items = {};
    // entidads
	$scope.items.entidads = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Entidad.getAllAsListItems().then(
				function(success) {
        	        $scope.items.entidads = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh chofers list
     */
    $scope.refreshChoferList = function() {
    	try {
			$scope.chofers = [];
        	Chofer.getAll().then(
				function(success) {
        	        $scope.chofers = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh chofer
     */
    $scope.refreshChofer = function(id) {
    	try {
        	$scope.chofer = null;
	        Chofer.get(id).then(
				function(success) {
        	        $scope.chofer = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the chofers list page
     */
    $scope.goToChoferList = function() {
        $scope.refreshChoferList();
        $location.path('/chofer');
    }
    /**
     * Go to the chofer edit page
     */
    $scope.goToChofer = function(id) {
        $scope.refreshChofer(id);
        $location.path('/chofer/'+id);
    }

    // Actions

    /**
     * Save chofer
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Chofer.create;
			} else {
				save = Chofer.update;
			}
			save($scope.chofer).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.chofer = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete chofer
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Chofer.delete(id).then(
				function(success) {
                	$scope.goToChoferList();
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
        $scope.chofer = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshChofer($routeParams.id);
    } else {
        // List page
        $scope.refreshChoferList();
    }
    
    
}]);
