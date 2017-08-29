'use strict';

/**
 * Factory for Clienteviajeservicio
 */
clienteviajeservicioModule.factory('Clienteviajeservicio', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage clienteviajeservicio
    var entityURL = restURL + '/clienteviajeservicio';
	
	/**
     * Validate clienteviajeservicio
     * @param clienteviajeservicio clienteviajeservicio
     * @throws validation exception
     */
	var validate = function (clienteviajeservicio) {
		var errors = [];
        if( clienteviajeservicio.id == null || clienteviajeservicio.id == '' ) {
			errors.push('clienteviajeservicio.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all clienteviajeservicios as list items
         * @return all clienteviajeservicios as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/clienteviajeservicio');
    	},

        /**
         * Get all clienteviajeservicios
         * @return all clienteviajeservicios
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get clienteviajeservicio
         * @param id id
         * @return clienteviajeservicio
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new clienteviajeservicio
         * @param clienteviajeservicio clienteviajeservicio
         * @return clienteviajeservicio saved
         */
		create: function(clienteviajeservicio) {
			validate(clienteviajeservicio)
			var url = entityURL;
			return $http.post(url, clienteviajeservicio);
    	},

        /**
         * Update clienteviajeservicio
         * @param clienteviajeservicio clienteviajeservicio
         * @return clienteviajeservicio saved
         */
    	update: function(clienteviajeservicio) {
			validate(clienteviajeservicio)
			var url = entityURL + '/' + clienteviajeservicio.id;
			return $http.put(url, clienteviajeservicio);
    	},

		/**
         * Delete clienteviajeservicio
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

