'use strict';

/**
 * Factory for Tipovehiculo
 */
tipovehiculoModule.factory('Tipovehiculo', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage tipovehiculo
    var entityURL = restURL + '/tipovehiculo';
	
	/**
     * Validate tipovehiculo
     * @param tipovehiculo tipovehiculo
     * @throws validation exception
     */
	var validate = function (tipovehiculo) {
		var errors = [];
        if( tipovehiculo.id == null || tipovehiculo.id == '' ) {
			errors.push('tipovehiculo.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all tipovehiculos as list items
         * @return all tipovehiculos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/tipovehiculo');
    	},

        /**
         * Get all tipovehiculos
         * @return all tipovehiculos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get tipovehiculo
         * @param id id
         * @return tipovehiculo
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new tipovehiculo
         * @param tipovehiculo tipovehiculo
         * @return tipovehiculo saved
         */
		create: function(tipovehiculo) {
			validate(tipovehiculo)
			var url = entityURL;
			return $http.post(url, tipovehiculo);
    	},

        /**
         * Update tipovehiculo
         * @param tipovehiculo tipovehiculo
         * @return tipovehiculo saved
         */
    	update: function(tipovehiculo) {
			validate(tipovehiculo)
			var url = entityURL + '/' + tipovehiculo.id;
			return $http.put(url, tipovehiculo);
    	},

		/**
         * Delete tipovehiculo
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

