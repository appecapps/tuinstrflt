'use strict';

/**
 * Factory for Vehiculo
 */
vehiculoModule.factory('Vehiculo', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage vehiculo
    var entityURL = restURL + '/vehiculo';
	
	/**
     * Validate vehiculo
     * @param vehiculo vehiculo
     * @throws validation exception
     */
	var validate = function (vehiculo) {
		var errors = [];
        if( vehiculo.id == null || vehiculo.id == '' ) {
			errors.push('vehiculo.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all vehiculos as list items
         * @return all vehiculos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/vehiculo');
    	},

        /**
         * Get all vehiculos
         * @return all vehiculos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get vehiculo
         * @param id id
         * @return vehiculo
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new vehiculo
         * @param vehiculo vehiculo
         * @return vehiculo saved
         */
		create: function(vehiculo) {
			validate(vehiculo)
			var url = entityURL;
			return $http.post(url, vehiculo);
    	},

        /**
         * Update vehiculo
         * @param vehiculo vehiculo
         * @return vehiculo saved
         */
    	update: function(vehiculo) {
			validate(vehiculo)
			var url = entityURL + '/' + vehiculo.id;
			return $http.put(url, vehiculo);
    	},

		/**
         * Delete vehiculo
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

