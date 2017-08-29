'use strict';

/**
 * Controller for Destinohorario
 **/
destinohorarioModule.controller('DestinohorarioCtrl', ['Destinohorario',  'Ciudad', 'Horario', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Destinohorario, Ciudad, Horario, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Ciudad',  'Horario',     // edition mode
    $scope.mode = null;
    
	// list of destinohorarios
    $scope.destinohorarios = [];
	// destinohorario to edit
    $scope.destinohorario = null;

	// referencies entities
	$scope.items = {};
    // ciudads
	$scope.items.ciudads = [];
    // horarios
	$scope.items.horarios = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Ciudad.getAllAsListItems().then(
				function(success) {
        	        $scope.items.ciudads = success.data;
            	}, 
	            MessageHandler.manageError);
		Horario.getAllAsListItems().then(
				function(success) {
        	        $scope.items.horarios = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh destinohorarios list
     */
    $scope.refreshDestinohorarioList = function() {
    	try {
			$scope.destinohorarios = [];
        	Destinohorario.getAll().then(
				function(success) {
        	        $scope.destinohorarios = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh destinohorario
     */
    $scope.refreshDestinohorario = function(id) {
    	try {
        	$scope.destinohorario = null;
	        Destinohorario.get(id).then(
				function(success) {
        	        $scope.destinohorario = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the destinohorarios list page
     */
    $scope.goToDestinohorarioList = function() {
        $scope.refreshDestinohorarioList();
        $location.path('/destinohorario');
    }
    /**
     * Go to the destinohorario edit page
     */
    $scope.goToDestinohorario = function(id) {
        $scope.refreshDestinohorario(id);
        $location.path('/destinohorario/'+id);
    }

    // Actions

    /**
     * Save destinohorario
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Destinohorario.create;
			} else {
				save = Destinohorario.update;
			}
			save($scope.destinohorario).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.destinohorario = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete destinohorario
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Destinohorario.delete(id).then(
				function(success) {
                	$scope.goToDestinohorarioList();
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
        $scope.destinohorario = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshDestinohorario($routeParams.id);
    } else {
        // List page
        $scope.refreshDestinohorarioList();
    }
    
    
}]);
