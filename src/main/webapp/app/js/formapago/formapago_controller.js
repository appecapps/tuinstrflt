'use strict';

/**
 * Controller for Formapago
 **/
formapagoModule.controller('FormapagoCtrl', ['Formapago',  'Tipodocumento', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Formapago, Tipodocumento, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Tipodocumento',     // edition mode
    $scope.mode = null;
    
	// list of formapagos
    $scope.formapagos = [];
	// formapago to edit
    $scope.formapago = null;

	// referencies entities
	$scope.items = {};
    // tipodocumentos
	$scope.items.tipodocumentos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Tipodocumento.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipodocumentos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh formapagos list
     */
    $scope.refreshFormapagoList = function() {
    	try {
			$scope.formapagos = [];
        	Formapago.getAll().then(
				function(success) {
        	        $scope.formapagos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh formapago
     */
    $scope.refreshFormapago = function(id) {
    	try {
        	$scope.formapago = null;
	        Formapago.get(id).then(
				function(success) {
        	        $scope.formapago = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the formapagos list page
     */
    $scope.goToFormapagoList = function() {
        $scope.refreshFormapagoList();
        $location.path('/formapago');
    }
    /**
     * Go to the formapago edit page
     */
    $scope.goToFormapago = function(id) {
        $scope.refreshFormapago(id);
        $location.path('/formapago/'+id);
    }

    // Actions

    /**
     * Save formapago
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Formapago.create;
			} else {
				save = Formapago.update;
			}
			save($scope.formapago).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.formapago = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete formapago
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Formapago.delete(id).then(
				function(success) {
                	$scope.goToFormapagoList();
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
        $scope.formapago = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshFormapago($routeParams.id);
    } else {
        // List page
        $scope.refreshFormapagoList();
    }
    
    
}]);
