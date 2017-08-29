'use strict';

/**
 * Factory for Servicio
 */
servicioModule.factory('Servicio', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage servicio
    var entityURL = restURL + '/servicio';
	
	/**
     * Validate servicio
     * @param servicio servicio
     * @throws validation exception
     */
	var validate = function (servicio) {
		var errors = [];
        if( servicio.id == null || servicio.id == '' ) {
			errors.push('servicio.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all servicios as list items
         * @return all servicios as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/servicio');
    	},

        /**
         * Get all servicios
         * @return all servicios
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get servicio
         * @param id id
         * @return servicio
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new servicio
         * @param servicio servicio
         * @return servicio saved
         */
		create: function(servicio) {
			validate(servicio)
			var url = entityURL;
			return $http.post(url, servicio);
    	},

        /**
         * Update servicio
         * @param servicio servicio
         * @return servicio saved
         */
    	update: function(servicio) {
			validate(servicio)
			var url = entityURL + '/' + servicio.id;
			return $http.put(url, servicio);
    	},

		/**
         * Delete servicio
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

