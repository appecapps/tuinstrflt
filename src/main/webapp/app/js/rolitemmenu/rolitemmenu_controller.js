'use strict';

/**
 * Controller for Rolitemmenu
 **/
rolitemmenuModule.controller('RolitemmenuCtrl', ['Rolitemmenu',  'Itemmenu', 'Rol', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Rolitemmenu, Itemmenu, Rol, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Itemmenu',  'Rol',     // edition mode
    $scope.mode = null;
    
	// list of rolitemmenus
    $scope.rolitemmenus = [];
	// rolitemmenu to edit
    $scope.rolitemmenu = null;

	// referencies entities
	$scope.items = {};
    // itemmenus
	$scope.items.itemmenus = [];
    // rols
	$scope.items.rols = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Itemmenu.getAllAsListItems().then(
				function(success) {
        	        $scope.items.itemmenus = success.data;
            	}, 
	            MessageHandler.manageError);
		Rol.getAllAsListItems().then(
				function(success) {
        	        $scope.items.rols = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh rolitemmenus list
     */
    $scope.refreshRolitemmenuList = function() {
    	try {
			$scope.rolitemmenus = [];
        	Rolitemmenu.getAll().then(
				function(success) {
        	        $scope.rolitemmenus = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh rolitemmenu
     */
    $scope.refreshRolitemmenu = function(id) {
    	try {
        	$scope.rolitemmenu = null;
	        Rolitemmenu.get(id).then(
				function(success) {
        	        $scope.rolitemmenu = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the rolitemmenus list page
     */
    $scope.goToRolitemmenuList = function() {
        $scope.refreshRolitemmenuList();
        $location.path('/rolitemmenu');
    }
    /**
     * Go to the rolitemmenu edit page
     */
    $scope.goToRolitemmenu = function(id) {
        $scope.refreshRolitemmenu(id);
        $location.path('/rolitemmenu/'+id);
    }

    // Actions

    /**
     * Save rolitemmenu
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Rolitemmenu.create;
			} else {
				save = Rolitemmenu.update;
			}
			save($scope.rolitemmenu).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.rolitemmenu = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete rolitemmenu
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Rolitemmenu.delete(id).then(
				function(success) {
                	$scope.goToRolitemmenuList();
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
        $scope.rolitemmenu = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshRolitemmenu($routeParams.id);
    } else {
        // List page
        $scope.refreshRolitemmenuList();
    }
    
    
}]);
