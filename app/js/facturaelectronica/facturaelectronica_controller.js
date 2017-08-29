'use strict';

/**
 * Controller for Facturaelectronica
 **/
facturaelectronicaModule.controller('FacturaelectronicaCtrl', ['Facturaelectronica',  'Estado', 'Documento', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Facturaelectronica, Estado, Documento, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Estado',  'Documento',     // edition mode
    $scope.mode = null;
    
	// list of facturaelectronicas
    $scope.facturaelectronicas = [];
	// facturaelectronica to edit
    $scope.facturaelectronica = null;

	// referencies entities
	$scope.items = {};
    // estados
	$scope.items.estados = [];
    // documentos
	$scope.items.documentos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Estado.getAllAsListItems().then(
				function(success) {
        	        $scope.items.estados = success.data;
            	}, 
	            MessageHandler.manageError);
		Documento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.documentos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh facturaelectronicas list
     */
    $scope.refreshFacturaelectronicaList = function() {
    	try {
			$scope.facturaelectronicas = [];
        	Facturaelectronica.getAll().then(
				function(success) {
        	        $scope.facturaelectronicas = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh facturaelectronica
     */
    $scope.refreshFacturaelectronica = function(id) {
    	try {
        	$scope.facturaelectronica = null;
	        Facturaelectronica.get(id).then(
				function(success) {
        	        $scope.facturaelectronica = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the facturaelectronicas list page
     */
    $scope.goToFacturaelectronicaList = function() {
        $scope.refreshFacturaelectronicaList();
        $location.path('/facturaelectronica');
    }
    /**
     * Go to the facturaelectronica edit page
     */
    $scope.goToFacturaelectronica = function(id) {
        $scope.refreshFacturaelectronica(id);
        $location.path('/facturaelectronica/'+id);
    }

    // Actions

    /**
     * Save facturaelectronica
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Facturaelectronica.create;
			} else {
				save = Facturaelectronica.update;
			}
			save($scope.facturaelectronica).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.facturaelectronica = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete facturaelectronica
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Facturaelectronica.delete(id).then(
				function(success) {
                	$scope.goToFacturaelectronicaList();
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
        $scope.facturaelectronica = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshFacturaelectronica($routeParams.id);
    } else {
        // List page
        $scope.refreshFacturaelectronicaList();
    }
    
    
}]);
