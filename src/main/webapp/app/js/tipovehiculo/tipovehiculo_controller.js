'use strict';

/**
 * Controller for Tipovehiculo
 **/
tipovehiculoModule.controller('TipovehiculoCtrl', ['Tipovehiculo',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Tipovehiculo, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of tipovehiculos
    $scope.tipovehiculos = [];
	// tipovehiculo to edit
    $scope.tipovehiculo = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh tipovehiculos list
     */
    $scope.refreshTipovehiculoList = function() {
    	try {
			$scope.tipovehiculos = [];
        	Tipovehiculo.getAll().then(
				function(success) {
        	        $scope.tipovehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh tipovehiculo
     */
    $scope.refreshTipovehiculo = function(id) {
    	try {
        	$scope.tipovehiculo = null;
	        Tipovehiculo.get(id).then(
				function(success) {
        	        $scope.tipovehiculo = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the tipovehiculos list page
     */
    $scope.goToTipovehiculoList = function() {
        $scope.refreshTipovehiculoList();
        $location.path('/tipovehiculo');
    }
    /**
     * Go to the tipovehiculo edit page
     */
    $scope.goToTipovehiculo = function(id) {
        $scope.refreshTipovehiculo(id);
        $location.path('/tipovehiculo/'+id);
    }

    // Actions

    /**
     * Save tipovehiculo
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Tipovehiculo.create;
			} else {
				save = Tipovehiculo.update;
			}
			save($scope.tipovehiculo).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.tipovehiculo = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete tipovehiculo
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Tipovehiculo.delete(id).then(
				function(success) {
                	$scope.goToTipovehiculoList();
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
        $scope.tipovehiculo = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshTipovehiculo($routeParams.id);
    } else {
        // List page
        $scope.refreshTipovehiculoList();
    }
    
    
}]);
