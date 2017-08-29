'use strict';

/**
 * Factory for Viaje
 */
viajeModule.factory('Viaje', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage viaje
    var entityURL = restURL + '/viaje';
	
	/**
     * Validate viaje
     * @param viaje viaje
     * @throws validation exception
     */
	var validate = function (viaje) {
		var errors = [];
        if( viaje.id == null || viaje.id == '' ) {
			errors.push('viaje.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all viajes as list items
         * @return all viajes as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/viaje');
    	},

        /**
         * Get all viajes
         * @return all viajes
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get viaje
         * @param id id
         * @return viaje
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new viaje
         * @param viaje viaje
         * @return viaje saved
         */
		create: function(viaje) {
			validate(viaje)
			var url = entityURL;
			return $http.post(url, viaje);
    	},

        /**
         * Update viaje
         * @param viaje viaje
         * @return viaje saved
         */
    	update: function(viaje) {
			validate(viaje)
			var url = entityURL + '/' + viaje.id;
			return $http.put(url, viaje);
    	},

		/**
         * Delete viaje
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

