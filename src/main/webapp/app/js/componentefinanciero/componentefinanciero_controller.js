'use strict';

/**
 * Controller for Componentefinanciero
 **/
componentefinancieroModule.controller('ComponentefinancieroCtrl', ['Componentefinanciero',  'Tipodocumento', 'Tipocomponentefinanciero', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Componentefinanciero, Tipodocumento, Tipocomponentefinanciero, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Tipodocumento',  'Tipocomponentefinanciero',     // edition mode
    $scope.mode = null;
    
	// list of componentefinancieros
    $scope.componentefinancieros = [];
	// componentefinanciero to edit
    $scope.componentefinanciero = null;

	// referencies entities
	$scope.items = {};
    // tipodocumentos
	$scope.items.tipodocumentos = [];
    // tipocomponentefinancieros
	$scope.items.tipocomponentefinancieros = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Tipodocumento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipodocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
		Tipocomponentefinanciero.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipocomponentefinancieros = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh componentefinancieros list
     */
    $scope.refreshComponentefinancieroList = function() {
    	try {
			$scope.componentefinancieros = [];
        	Componentefinanciero.getAll().then(
				function(success) {
        	        $scope.componentefinancieros = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh componentefinanciero
     */
    $scope.refreshComponentefinanciero = function(id) {
    	try {
        	$scope.componentefinanciero = null;
	        Componentefinanciero.get(id).then(
				function(success) {
        	        $scope.componentefinanciero = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the componentefinancieros list page
     */
    $scope.goToComponentefinancieroList = function() {
        $scope.refreshComponentefinancieroList();
        $location.path('/componentefinanciero');
    }
    /**
     * Go to the componentefinanciero edit page
     */
    $scope.goToComponentefinanciero = function(id) {
        $scope.refreshComponentefinanciero(id);
        $location.path('/componentefinanciero/'+id);
    }

    // Actions

    /**
     * Save componentefinanciero
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Componentefinanciero.create;
			} else {
				save = Componentefinanciero.update;
			}
			save($scope.componentefinanciero).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.componentefinanciero = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete componentefinanciero
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Componentefinanciero.delete(id).then(
				function(success) {
                	$scope.goToComponentefinancieroList();
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
        $scope.componentefinanciero = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshComponentefinanciero($routeParams.id);
    } else {
        // List page
        $scope.refreshComponentefinancieroList();
    }
    
    
}]);
