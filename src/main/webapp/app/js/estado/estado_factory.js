'use strict';

/**
 * Factory for Estado
 */
estadoModule.factory('Estado', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage estado
    var entityURL = restURL + '/estado';
	
	/**
     * Validate estado
     * @param estado estado
     * @throws validation exception
     */
	var validate = function (estado) {
		var errors = [];
        if( estado.id == null || estado.id == '' ) {
			errors.push('estado.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all estados as list items
         * @return all estados as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/estado');
    	},

        /**
         * Get all estados
         * @return all estados
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get estado
         * @param id id
         * @return estado
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new estado
         * @param estado estado
         * @return estado saved
         */
		create: function(estado) {
			validate(estado)
			var url = entityURL;
			return $http.post(url, estado);
    	},

        /**
         * Update estado
         * @param estado estado
         * @return estado saved
         */
    	update: function(estado) {
			validate(estado)
			var url = entityURL + '/' + estado.id;
			return $http.put(url, estado);
    	},

		/**
         * Delete estado
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

