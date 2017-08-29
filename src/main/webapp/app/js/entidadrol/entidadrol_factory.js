'use strict';

/**
 * Factory for Entidadrol
 */
entidadrolModule.factory('Entidadrol', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage entidadrol
    var entityURL = restURL + '/entidadrol';
	
	/**
     * Validate entidadrol
     * @param entidadrol entidadrol
     * @throws validation exception
     */
	var validate = function (entidadrol) {
		var errors = [];
        if( entidadrol.id == null || entidadrol.id == '' ) {
			errors.push('entidadrol.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all entidadrols as list items
         * @return all entidadrols as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/entidadrol');
    	},

        /**
         * Get all entidadrols
         * @return all entidadrols
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get entidadrol
         * @param id id
         * @return entidadrol
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new entidadrol
         * @param entidadrol entidadrol
         * @return entidadrol saved
         */
		create: function(entidadrol) {
			validate(entidadrol)
			var url = entityURL;
			return $http.post(url, entidadrol);
    	},

        /**
         * Update entidadrol
         * @param entidadrol entidadrol
         * @return entidadrol saved
         */
    	update: function(entidadrol) {
			validate(entidadrol)
			var url = entityURL + '/' + entidadrol.id;
			return $http.put(url, entidadrol);
    	},

		/**
         * Delete entidadrol
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

