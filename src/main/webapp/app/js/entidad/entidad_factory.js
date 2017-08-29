'use strict';

/**
 * Factory for Entidad
 */
entidadModule.factory('Entidad', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage entidad
    var entityURL = restURL + '/entidad';
	
	/**
     * Validate entidad
     * @param entidad entidad
     * @throws validation exception
     */
	var validate = function (entidad) {
		var errors = [];
        if( entidad.id == null || entidad.id == '' ) {
			errors.push('entidad.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all entidads as list items
         * @return all entidads as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/entidad');
    	},

        /**
         * Get all entidads
         * @return all entidads
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get entidad
         * @param id id
         * @return entidad
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new entidad
         * @param entidad entidad
         * @return entidad saved
         */
		create: function(entidad) {
			validate(entidad)
			var url = entityURL;
			return $http.post(url, entidad);
    	},

        /**
         * Update entidad
         * @param entidad entidad
         * @return entidad saved
         */
    	update: function(entidad) {
			validate(entidad)
			var url = entityURL + '/' + entidad.id;
			return $http.put(url, entidad);
    	},

		/**
         * Delete entidad
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

