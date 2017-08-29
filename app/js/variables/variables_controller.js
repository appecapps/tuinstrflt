'use strict';

/**
 * Controller for Variables
 **/
variablesModule.controller('VariablesCtrl', ['Variables',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Variables, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of variabless
    $scope.variabless = [];
	// variables to edit
    $scope.variables = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh variabless list
     */
    $scope.refreshVariablesList = function() {
    	try {
			$scope.variabless = [];
        	Variables.getAll().then(
				function(success) {
        	        $scope.variabless = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh variables
     */
    $scope.refreshVariables = function(id) {
    	try {
        	$scope.variables = null;
	        Variables.get(id).then(
				function(success) {
        	        $scope.variables = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the variabless list page
     */
    $scope.goToVariablesList = function() {
        $scope.refreshVariablesList();
        $location.path('/variables');
    }
    /**
     * Go to the variables edit page
     */
    $scope.goToVariables = function(id) {
        $scope.refreshVariables(id);
        $location.path('/variables/'+id);
    }

    // Actions

    /**
     * Save variables
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Variables.create;
			} else {
				save = Variables.update;
			}
			save($scope.variables).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.variables = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete variables
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Variables.delete(id).then(
				function(success) {
                	$scope.goToVariablesList();
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
        $scope.variables = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshVariables($routeParams.id);
    } else {
        // List page
        $scope.refreshVariablesList();
    }
    
    
}]);
