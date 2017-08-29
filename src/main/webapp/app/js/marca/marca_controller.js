'use strict';

/**
 * Controller for Marca
 **/
marcaModule.controller('MarcaCtrl', ['Marca',  '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Marca, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	    // edition mode
    $scope.mode = null;
    
	// list of marcas
    $scope.marcas = [];
	// marca to edit
    $scope.marca = null;

	// referencies entities
	$scope.items = {};

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
    };
    
    /**
     * Refresh marcas list
     */
    $scope.refreshMarcaList = function() {
    	try {
			$scope.marcas = [];
        	Marca.getAll().then(
				function(success) {
        	        $scope.marcas = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh marca
     */
    $scope.refreshMarca = function(id) {
    	try {
        	$scope.marca = null;
	        Marca.get(id).then(
				function(success) {
        	        $scope.marca = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the marcas list page
     */
    $scope.goToMarcaList = function() {
        $scope.refreshMarcaList();
        $location.path('/marca');
    }
    /**
     * Go to the marca edit page
     */
    $scope.goToMarca = function(id) {
        $scope.refreshMarca(id);
        $location.path('/marca/'+id);
    }

    // Actions

    /**
     * Save marca
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Marca.create;
			} else {
				save = Marca.update;
			}
			save($scope.marca).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.marca = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete marca
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Marca.delete(id).then(
				function(success) {
                	$scope.goToMarcaList();
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
        $scope.marca = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshMarca($routeParams.id);
    } else {
        // List page
        $scope.refreshMarcaList();
    }
    
    
}]);
