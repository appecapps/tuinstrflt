'use strict';

/**
 * Factory for Provincia
 */
provinciaModule.factory('Provincia', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage provincia
    var entityURL = restURL + '/provincia';
	
	/**
     * Validate provincia
     * @param provincia provincia
     * @throws validation exception
     */
	var validate = function (provincia) {
		var errors = [];
        if( provincia.id == null || provincia.id == '' ) {
			errors.push('provincia.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all provincias as list items
         * @return all provincias as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/provincia');
    	},

        /**
         * Get all provincias
         * @return all provincias
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get provincia
         * @param id id
         * @return provincia
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new provincia
         * @param provincia provincia
         * @return provincia saved
         */
		create: function(provincia) {
			validate(provincia)
			var url = entityURL;
			return $http.post(url, provincia);
    	},

        /**
         * Update provincia
         * @param provincia provincia
         * @return provincia saved
         */
    	update: function(provincia) {
			validate(provincia)
			var url = entityURL + '/' + provincia.id;
			return $http.put(url, provincia);
    	},

		/**
         * Delete provincia
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

