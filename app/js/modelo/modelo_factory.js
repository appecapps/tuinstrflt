'use strict';

/**
 * Factory for Modelo
 */
modeloModule.factory('Modelo', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage modelo
    var entityURL = restURL + '/modelo';
	
	/**
     * Validate modelo
     * @param modelo modelo
     * @throws validation exception
     */
	var validate = function (modelo) {
		var errors = [];
        if( modelo.id == null || modelo.id == '' ) {
			errors.push('modelo.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all modelos as list items
         * @return all modelos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/modelo');
    	},

        /**
         * Get all modelos
         * @return all modelos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get modelo
         * @param id id
         * @return modelo
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new modelo
         * @param modelo modelo
         * @return modelo saved
         */
		create: function(modelo) {
			validate(modelo)
			var url = entityURL;
			return $http.post(url, modelo);
    	},

        /**
         * Update modelo
         * @param modelo modelo
         * @return modelo saved
         */
    	update: function(modelo) {
			validate(modelo)
			var url = entityURL + '/' + modelo.id;
			return $http.put(url, modelo);
    	},

		/**
         * Delete modelo
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

