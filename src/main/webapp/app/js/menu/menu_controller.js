'use strict';

/**
 * Controller for Menu
 **/
menuModule.controller('MenuCtrl', ['Menu',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Menu, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of menus
    $scope.menus = [];
	// menu to edit
    $scope.menu = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh menus list
     */
    $scope.refreshMenuList = function() {
    	try {
			$scope.menus = [];
        	Menu.getAll().then(
				function(success) {
        	        $scope.menus = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh menu
     */
    $scope.refreshMenu = function(id) {
    	try {
        	$scope.menu = null;
	        Menu.get(id).then(
				function(success) {
        	        $scope.menu = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the menus list page
     */
    $scope.goToMenuList = function() {
        $scope.refreshMenuList();
        $location.path('/menu');
    }
    /**
     * Go to the menu edit page
     */
    $scope.goToMenu = function(id) {
        $scope.refreshMenu(id);
        $location.path('/menu/'+id);
    }

    // Actions

    /**
     * Save menu
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Menu.create;
			} else {
				save = Menu.update;
			}
			save($scope.menu).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.menu = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete menu
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Menu.delete(id).then(
				function(success) {
                	$scope.goToMenuList();
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
        $scope.menu = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshMenu($routeParams.id);
    } else {
        // List page
        $scope.refreshMenuList();
    }
    
    
}]);
