'use strict';

/**
 * Controller for Tipocomponentefinanciero
 **/
tipocomponentefinancieroModule.controller('TipocomponentefinancieroCtrl', ['Tipocomponentefinanciero',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Tipocomponentefinanciero, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of tipocomponentefinancieros
    $scope.tipocomponentefinancieros = [];
	// tipocomponentefinanciero to edit
    $scope.tipocomponentefinanciero = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh tipocomponentefinancieros list
     */
    $scope.refreshTipocomponentefinancieroList = function() {
    	try {
			$scope.tipocomponentefinancieros = [];
        	Tipocomponentefinanciero.getAll().then(
				function(success) {
        	        $scope.tipocomponentefinancieros = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh tipocomponentefinanciero
     */
    $scope.refreshTipocomponentefinanciero = function(id) {
    	try {
        	$scope.tipocomponentefinanciero = null;
	        Tipocomponentefinanciero.get(id).then(
				function(success) {
        	        $scope.tipocomponentefinanciero = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the tipocomponentefinancieros list page
     */
    $scope.goToTipocomponentefinancieroList = function() {
        $scope.refreshTipocomponentefinancieroList();
        $location.path('/tipocomponentefinanciero');
    }
    /**
     * Go to the tipocomponentefinanciero edit page
     */
    $scope.goToTipocomponentefinanciero = function(id) {
        $scope.refreshTipocomponentefinanciero(id);
        $location.path('/tipocomponentefinanciero/'+id);
    }

    // Actions

    /**
     * Save tipocomponentefinanciero
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Tipocomponentefinanciero.create;
			} else {
				save = Tipocomponentefinanciero.update;
			}
			save($scope.tipocomponentefinanciero).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.tipocomponentefinanciero = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete tipocomponentefinanciero
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Tipocomponentefinanciero.delete(id).then(
				function(success) {
                	$scope.goToTipocomponentefinancieroList();
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
        $scope.tipocomponentefinanciero = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshTipocomponentefinanciero($routeParams.id);
    } else {
        // List page
        $scope.refreshTipocomponentefinancieroList();
    }
    
    
}]);
