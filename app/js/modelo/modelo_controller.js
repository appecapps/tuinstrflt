'use strict';

/**
 * Controller for Modelo
 **/
modeloModule.controller('ModeloCtrl', ['Modelo',  'Marca', 'Tipovehiculo', '$scope', '$routeParams', '$http', '$location', '$cookies', 'MessageHandler', 'restURL', function(Modelo, Marca, Tipovehiculo, $scope, $routeParams, $http, $location, $cookies, MessageHandler, restURL) {
	 'Marca',  'Tipovehiculo',     // edition mode
    $scope.mode = null;
    
	// list of modelos
    $scope.modelos = [];
	// modelo to edit
    $scope.modelo = null;

	// referencies entities
	$scope.items = {};
    // marcas
	$scope.items.marcas = [];
    // tipovehiculos
	$scope.items.tipovehiculos = [];

    /**
     * Load all referencies entities
     */
	$scope.loadAllReferencies = function() {
		Marca.getAllAsListItems().then(
				function(success) {
        	        $scope.items.marcas = success.data;
            	}, 
	            MessageHandler.manageError);
		Tipovehiculo.getAllAsListItems().then(
				function(success) {
        	        $scope.items.tipovehiculos = success.data;
            	}, 
	            MessageHandler.manageError);
    };
    
    /**
     * Refresh modelos list
     */
    $scope.refreshModeloList = function() {
    	try {
			$scope.modelos = [];
        	Modelo.getAll().then(
				function(success) {
        	        $scope.modelos = success.data;
            	}, 
	            MessageHandler.manageError);
    	} catch(ex) {
    		MessageHandler.manageException(ex);
    	}
    }
    /**
     * Refresh modelo
     */
    $scope.refreshModelo = function(id) {
    	try {
        	$scope.modelo = null;
	        Modelo.get(id).then(
				function(success) {
        	        $scope.modelo = success.data;
            	}, 
	            MessageHandler.manageError);
    	  } catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    }

    /**
     * Go to the modelos list page
     */
    $scope.goToModeloList = function() {
        $scope.refreshModeloList();
        $location.path('/modelo');
    }
    /**
     * Go to the modelo edit page
     */
    $scope.goToModelo = function(id) {
        $scope.refreshModelo(id);
        $location.path('/modelo/'+id);
    }

    // Actions

    /**
     * Save modelo
     */
    $scope.save = function() {
    	try {
			MessageHandler.cleanMessage();
			var save;
			if( $scope.mode === 'create' ) {
        		save = Modelo.create;
			} else {
				save = Modelo.update;
			}
			save($scope.modelo).then(
    	        function(success) {
	                MessageHandler.addSuccess('save ok');
                	$scope.modelo = success.data;
            	},
        	    MessageHandler.manageError);
    	} catch(ex) {
        	MessageHandler.manageException(ex);
    	}
    };
    /**
     * Delete modelo
     */
    $scope.delete = function(id) {
	    try {
			MessageHandler.cleanMessage();
    	    Modelo.delete(id).then(
				function(success) {
                	$scope.goToModeloList();
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
        $scope.modelo = {};
        $scope.mode = 'create';
		$scope.loadAllReferencies();
        $scope.bookorderitem = null;
    } else if( $routeParams.id != null ) {
        // Edit page
		$scope.loadAllReferencies();
		$scope.refreshModelo($routeParams.id);
    } else {
        // List page
        $scope.refreshModeloList();
    }
    
    
}]);
