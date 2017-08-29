'use strict';

/**
 * Controller for Tipodocumento
 **/
tipodocumentoModule.controller('TipodocumentoCtrl', ['Tipodocumento',  'Tipocartera', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Tipodocumento, Tipocartera, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Tipocartera',     // edition mode
    $scope.mode = null;
    
	// list of tipodocumentos
    $scope.tipodocumentos = [];
	// tipodocumento to edit
    $scope.tipodocumento = null;

	// referencies entities
	$scope.items = {};
    // tipocarteras
	$scope.items.tipocarteras = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Tipocartera.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipocarteras = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh tipodocumentos list
     */
    $scope.refreshTipodocumentoList = function() {
    	try {
			$scope.tipodocumentos = [];
        	Tipodocumento.getAll().then(
				function(success) {
        	        $scope.tipodocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh tipodocumento
     */
    $scope.refreshTipodocumento = function(id) {
    	try {
        	$scope.tipodocumento = null;
	        Tipodocumento.get(id).then(
				function(success) {
        	        $scope.tipodocumento = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the tipodocumentos list page
     */
    $scope.goToTipodocumentoList = function() {
        $scope.refreshTipodocumentoList();
        $location.path('/tipodocumento');
    }
    /**
     * Go to the tipodocumento edit page
     */
    $scope.goToTipodocumento = function(id) {
        $scope.refreshTipodocumento(id);
        $location.path('/tipodocumento/'+id);
    }

    // Actions

    /**
     * Save tipodocumento
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Tipodocumento.create;
			} else {
				save = Tipodocumento.update;
			}
			save($scope.tipodocumento).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.tipodocumento = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete tipodocumento
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Tipodocumento.delete(id).then(
				function(success) {
                	$scope.goToTipodocumentoList();
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
        $scope.tipodocumento = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshTipodocumento($routeParams.id);
    } else {
        // List page
        $scope.refreshTipodocumentoList();
    }
    
    
}]);
