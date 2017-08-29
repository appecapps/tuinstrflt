'use strict';

/**
 * Controller for Itemmenu
 **/
itemmenuModule.controller('ItemmenuCtrl', ['Itemmenu',  'Menu', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Itemmenu, Menu, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Menu',     // edition mode
    $scope.mode = null;
    
	// list of itemmenus
    $scope.itemmenus = [];
	// itemmenu to edit
    $scope.itemmenu = null;

	// referencies entities
	$scope.items = {};
    // menus
	$scope.items.menus = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Menu.getAllAsListItems().then(
				function(success) {
        	        $scope.items.menus = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh itemmenus list
     */
    $scope.refreshItemmenuList = function() {
    	try {
			$scope.itemmenus = [];
        	Itemmenu.getAll().then(
				function(success) {
        	        $scope.itemmenus = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh itemmenu
     */
    $scope.refreshItemmenu = function(id) {
    	try {
        	$scope.itemmenu = null;
	        Itemmenu.get(id).then(
				function(success) {
        	        $scope.itemmenu = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the itemmenus list page
     */
    $scope.goToItemmenuList = function() {
        $scope.refreshItemmenuList();
        $location.path('/itemmenu');
    }
    /**
     * Go to the itemmenu edit page
     */
    $scope.goToItemmenu = function(id) {
        $scope.refreshItemmenu(id);
        $location.path('/itemmenu/'+id);
    }

    // Actions

    /**
     * Save itemmenu
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Itemmenu.create;
			} else {
				save = Itemmenu.update;
			}
			save($scope.itemmenu).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.itemmenu = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete itemmenu
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Itemmenu.delete(id).then(
				function(success) {
                	$scope.goToItemmenuList();
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
        $scope.itemmenu = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshItemmenu($routeParams.id);
    } else {
        // List page
        $scope.refreshItemmenuList();
    }
    
    
}]);
