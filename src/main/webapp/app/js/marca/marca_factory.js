'use strict';

/**
 * Factory for Marca
 */
marcaModule.factory('Marca', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage marca
    var entityURL = restURL + '/marca';
	
	/**
     * Validate marca
     * @param marca marca
     * @throws validation exception
     */
	var validate = function (marca) {
		var errors = [];
        if( marca.id == null || marca.id == '' ) {
			errors.push('marca.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all marcas as list items
         * @return all marcas as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/marca');
    	},

        /**
         * Get all marcas
         * @return all marcas
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get marca
         * @param id id
         * @return marca
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new marca
         * @param marca marca
         * @return marca saved
         */
		create: function(marca) {
			validate(marca)
			var url = entityURL;
			return $http.post(url, marca);
    	},

        /**
         * Update marca
         * @param marca marca
         * @return marca saved
         */
    	update: function(marca) {
			validate(marca)
			var url = entityURL + '/' + marca.id;
			return $http.put(url, marca);
    	},

		/**
         * Delete marca
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

