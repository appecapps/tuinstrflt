'use strict';

/**
 * Factory for Clienteviaje
 */
clienteviajeModule.factory('Clienteviaje', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage clienteviaje
    var entityURL = restURL + '/clienteviaje';
	
	/**
     * Validate clienteviaje
     * @param clienteviaje clienteviaje
     * @throws validation exception
     */
	var validate = function (clienteviaje) {
		var errors = [];
        if( clienteviaje.id == null || clienteviaje.id == '' ) {
			errors.push('clienteviaje.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all clienteviajes as list items
         * @return all clienteviajes as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/clienteviaje');
    	},

        /**
         * Get all clienteviajes
         * @return all clienteviajes
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get clienteviaje
         * @param id id
         * @return clienteviaje
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new clienteviaje
         * @param clienteviaje clienteviaje
         * @return clienteviaje saved
         */
		create: function(clienteviaje) {
			validate(clienteviaje)
			var url = entityURL;
			return $http.post(url, clienteviaje);
    	},

        /**
         * Update clienteviaje
         * @param clienteviaje clienteviaje
         * @return clienteviaje saved
         */
    	update: function(clienteviaje) {
			validate(clienteviaje)
			var url = entityURL + '/' + clienteviaje.id;
			return $http.put(url, clienteviaje);
    	},

		/**
         * Delete clienteviaje
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

