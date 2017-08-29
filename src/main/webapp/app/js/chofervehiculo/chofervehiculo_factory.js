'use strict';

/**
 * Factory for Chofervehiculo
 */
chofervehiculoModule.factory('Chofervehiculo', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage chofervehiculo
    var entityURL = restURL + '/chofervehiculo';
	
	/**
     * Validate chofervehiculo
     * @param chofervehiculo chofervehiculo
     * @throws validation exception
     */
	var validate = function (chofervehiculo) {
		var errors = [];
        if( chofervehiculo.id == null || chofervehiculo.id == '' ) {
			errors.push('chofervehiculo.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all chofervehiculos as list items
         * @return all chofervehiculos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/chofervehiculo');
    	},

        /**
         * Get all chofervehiculos
         * @return all chofervehiculos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get chofervehiculo
         * @param id id
         * @return chofervehiculo
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new chofervehiculo
         * @param chofervehiculo chofervehiculo
         * @return chofervehiculo saved
         */
		create: function(chofervehiculo) {
			validate(chofervehiculo)
			var url = entityURL;
			return $http.post(url, chofervehiculo);
    	},

        /**
         * Update chofervehiculo
         * @param chofervehiculo chofervehiculo
         * @return chofervehiculo saved
         */
    	update: function(chofervehiculo) {
			validate(chofervehiculo)
			var url = entityURL + '/' + chofervehiculo.id;
			return $http.put(url, chofervehiculo);
    	},

		/**
         * Delete chofervehiculo
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

