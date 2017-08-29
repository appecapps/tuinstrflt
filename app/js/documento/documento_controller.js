'use strict';

/**
 * Controller for Documento
 **/
documentoModule.controller('DocumentoCtrl', ['Documento',  'Estado', 'Tipodocumento', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Documento, Estado, Tipodocumento, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Estado',  'Tipodocumento',     // edition mode
    $scope.mode = null;
    
	// list of documentos
    $scope.documentos = [];
	// documento to edit
    $scope.documento = null;

	// referencies entities
	$scope.items = {};
    // estados
	$scope.items.estados = [];
    // tipodocumentos
	$scope.items.tipodocumentos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Estado.getAllAsListItems().then(
				function(success) {
        	        $scope.items.estados = success.data;
            	}, 
	            MessageHandler.manageError);
		Tipodocumento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipodocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh documentos list
     */
    $scope.refreshDocumentoList = function() {
    	try {
			$scope.documentos = [];
        	Documento.getAll().then(
				function(success) {
        	        $scope.documentos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh documento
     */
    $scope.refreshDocumento = function(id) {
    	try {
        	$scope.documento = null;
	        Documento.get(id).then(
				function(success) {
        	        $scope.documento = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the documentos list page
     */
    $scope.goToDocumentoList = function() {
        $scope.refreshDocumentoList();
        $location.path('/documento');
    }
    /**
     * Go to the documento edit page
     */
    $scope.goToDocumento = function(id) {
        $scope.refreshDocumento(id);
        $location.path('/documento/'+id);
    }

    // Actions

    /**
     * Save documento
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Documento.create;
			} else {
				save = Documento.update;
			}
			save($scope.documento).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.documento = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete documento
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Documento.delete(id).then(
				function(success) {
                	$scope.goToDocumentoList();
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
        $scope.documento = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshDocumento($routeParams.id);
    } else {
        // List page
        $scope.refreshDocumentoList();
    }
    
    
}]);
