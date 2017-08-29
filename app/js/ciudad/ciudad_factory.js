'use strict';

/**
 * Factory for Ciudad
 */
ciudadModule.factory('Ciudad', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage ciudad
    var entityURL = restURL + '/ciudad';
	
	/**
     * Validate ciudad
     * @param ciudad ciudad
     * @throws validation exception
     */
	var validate = function (ciudad) {
		var errors = [];
        if( ciudad.id == null || ciudad.id == '' ) {
			errors.push('ciudad.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all ciudads as list items
         * @return all ciudads as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/ciudad');
    	},

        /**
         * Get all ciudads
         * @return all ciudads
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get ciudad
         * @param id id
         * @return ciudad
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new ciudad
         * @param ciudad ciudad
         * @return ciudad saved
         */
		create: function(ciudad) {
			validate(ciudad)
			var url = entityURL;
			return $http.post(url, ciudad);
    	},

        /**
         * Update ciudad
         * @param ciudad ciudad
         * @return ciudad saved
         */
    	update: function(ciudad) {
			validate(ciudad)
			var url = entityURL + '/' + ciudad.id;
			return $http.put(url, ciudad);
    	},

		/**
         * Delete ciudad
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

