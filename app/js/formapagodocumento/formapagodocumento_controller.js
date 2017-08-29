'use strict';

/**
 * Controller for Formapagodocumento
 **/
formapagodocumentoModule.controller('FormapagodocumentoCtrl', ['Formapagodocumento',  'Documento', 'Formapago', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Formapagodocumento, Documento, Formapago, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Documento',  'Formapago',     // edition mode
    $scope.mode = null;
    
	// list of formapagodocumentos
    $scope.formapagodocumentos = [];
	// formapagodocumento to edit
    $scope.formapagodocumento = null;

	// referencies entities
	$scope.items = {};
    // documentos
	$scope.items.documentos = [];
    // formapagos
	$scope.items.formapagos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Documento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.documentos = success.data;
            	}, 
	            MessageHandler.manageError);
		Formapago.getAllAsListItems().then(
				function(success) {
        	        $scope.items.formapagos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh formapagodocumentos list
     */
    $scope.refreshFormapagodocumentoList = function() {
    	try {
			$scope.formapagodocumentos = [];
        	Formapagodocumento.getAll().then(
				function(success) {
        	        $scope.formapagodocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh formapagodocumento
     */
    $scope.refreshFormapagodocumento = function(id) {
    	try {
        	$scope.formapagodocumento = null;
	        Formapagodocumento.get(id).then(
				function(success) {
        	        $scope.formapagodocumento = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the formapagodocumentos list page
     */
    $scope.goToFormapagodocumentoList = function() {
        $scope.refreshFormapagodocumentoList();
        $location.path('/formapagodocumento');
    }
    /**
     * Go to the formapagodocumento edit page
     */
    $scope.goToFormapagodocumento = function(id) {
        $scope.refreshFormapagodocumento(id);
        $location.path('/formapagodocumento/'+id);
    }

    // Actions

    /**
     * Save formapagodocumento
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Formapagodocumento.create;
			} else {
				save = Formapagodocumento.update;
			}
			save($scope.formapagodocumento).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.formapagodocumento = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete formapagodocumento
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Formapagodocumento.delete(id).then(
				function(success) {
                	$scope.goToFormapagodocumentoList();
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
        $scope.formapagodocumento = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshFormapagodocumento($routeParams.id);
    } else {
        // List page
        $scope.refreshFormapagodocumentoList();
    }
    
    
}]);
