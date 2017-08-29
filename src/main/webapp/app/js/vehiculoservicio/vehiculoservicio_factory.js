'use strict';

/**
 * Factory for Vehiculoservicio
 */
vehiculoservicioModule.factory('Vehiculoservicio', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage vehiculoservicio
    var entityURL = restURL + '/vehiculoservicio';
	
	/**
     * Validate vehiculoservicio
     * @param vehiculoservicio vehiculoservicio
     * @throws validation exception
     */
	var validate = function (vehiculoservicio) {
		var errors = [];
        if( vehiculoservicio.id == null || vehiculoservicio.id == '' ) {
			errors.push('vehiculoservicio.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all vehiculoservicios as list items
         * @return all vehiculoservicios as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/vehiculoservicio');
    	},

        /**
         * Get all vehiculoservicios
         * @return all vehiculoservicios
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get vehiculoservicio
         * @param id id
         * @return vehiculoservicio
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new vehiculoservicio
         * @param vehiculoservicio vehiculoservicio
         * @return vehiculoservicio saved
         */
		create: function(vehiculoservicio) {
			validate(vehiculoservicio)
			var url = entityURL;
			return $http.post(url, vehiculoservicio);
    	},

        /**
         * Update vehiculoservicio
         * @param vehiculoservicio vehiculoservicio
         * @return vehiculoservicio saved
         */
    	update: function(vehiculoservicio) {
			validate(vehiculoservicio)
			var url = entityURL + '/' + vehiculoservicio.id;
			return $http.put(url, vehiculoservicio);
    	},

		/**
         * Delete vehiculoservicio
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

