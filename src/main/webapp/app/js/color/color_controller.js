'use strict';

/**
 * Controller for Color
 **/
colorModule.controller('ColorCtrl', ['Color',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Color, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of colors
    $scope.colors = [];
	// color to edit
    $scope.color = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh colors list
     */
    $scope.refreshColorList = function() {
    	try {
			$scope.colors = [];
        	Color.getAll().then(
				function(success) {
        	        $scope.colors = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh color
     */
    $scope.refreshColor = function(id) {
    	try {
        	$scope.color = null;
	        Color.get(id).then(
				function(success) {
        	        $scope.color = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the colors list page
     */
    $scope.goToColorList = function() {
        $scope.refreshColorList();
        $location.path('/color');
    }
    /**
     * Go to the color edit page
     */
    $scope.goToColor = function(id) {
        $scope.refreshColor(id);
        $location.path('/color/'+id);
    }

    // Actions

    /**
     * Save color
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Color.create;
			} else {
				save = Color.update;
			}
			save($scope.color).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.color = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete color
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Color.delete(id).then(
				function(success) {
                	$scope.goToColorList();
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
        $scope.color = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshColor($routeParams.id);
    } else {
        // List page
        $scope.refreshColorList();
    }
    
    
}]);
