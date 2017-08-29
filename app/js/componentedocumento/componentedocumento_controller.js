'use strict';

/**
 * Controller for Componentedocumento
 **/
componentedocumentoModule.controller('ComponentedocumentoCtrl', ['Componentedocumento',  'Componentefinanciero', 'Documento', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Componentedocumento, Componentefinanciero, Documento, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Componentefinanciero',  'Documento',     // edition mode
    $scope.mode = null;
    
	// list of componentedocumentos
    $scope.componentedocumentos = [];
	// componentedocumento to edit
    $scope.componentedocumento = null;

	// referencies entities
	$scope.items = {};
    // componentefinancieros
	$scope.items.componentefinancieros = [];
    // documentos
	$scope.items.documentos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Componentefinanciero.getAllAsListItems().then(
				function(success) {
        	        $scope.items.componentefinancieros = success.data;
            	}, 
	            MessageHandler.manageError);
		Documento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.documentos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh componentedocumentos list
     */
    $scope.refreshComponentedocumentoList = function() {
    	try {
			$scope.componentedocumentos = [];
        	Componentedocumento.getAll().then(
				function(success) {
        	        $scope.componentedocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh componentedocumento
     */
    $scope.refreshComponentedocumento = function(id) {
    	try {
        	$scope.componentedocumento = null;
	        Componentedocumento.get(id).then(
				function(success) {
        	        $scope.componentedocumento = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the componentedocumentos list page
     */
    $scope.goToComponentedocumentoList = function() {
        $scope.refreshComponentedocumentoList();
        $location.path('/componentedocumento');
    }
    /**
     * Go to the componentedocumento edit page
     */
    $scope.goToComponentedocumento = function(id) {
        $scope.refreshComponentedocumento(id);
        $location.path('/componentedocumento/'+id);
    }

    // Actions

    /**
     * Save componentedocumento
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Componentedocumento.create;
			} else {
				save = Componentedocumento.update;
			}
			save($scope.componentedocumento).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.componentedocumento = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete componentedocumento
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Componentedocumento.delete(id).then(
				function(success) {
                	$scope.goToComponentedocumentoList();
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
        $scope.componentedocumento = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshComponentedocumento($routeParams.id);
    } else {
        // List page
        $scope.refreshComponentedocumentoList();
    }
    
    
}]);
