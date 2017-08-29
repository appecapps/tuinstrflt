'use strict';

/**
 * Factory for Facturaelectronica
 */
facturaelectronicaModule.factory('Facturaelectronica', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage facturaelectronica
    var entityURL = restURL + '/facturaelectronica';
	
	/**
     * Validate facturaelectronica
     * @param facturaelectronica facturaelectronica
     * @throws validation exception
     */
	var validate = function (facturaelectronica) {
		var errors = [];
        if( facturaelectronica.id == null || facturaelectronica.id == '' ) {
			errors.push('facturaelectronica.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all facturaelectronicas as list items
         * @return all facturaelectronicas as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/facturaelectronica');
    	},

        /**
         * Get all facturaelectronicas
         * @return all facturaelectronicas
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get facturaelectronica
         * @param id id
         * @return facturaelectronica
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new facturaelectronica
         * @param facturaelectronica facturaelectronica
         * @return facturaelectronica saved
         */
		create: function(facturaelectronica) {
			validate(facturaelectronica)
			var url = entityURL;
			return $http.post(url, facturaelectronica);
    	},

        /**
         * Update facturaelectronica
         * @param facturaelectronica facturaelectronica
         * @return facturaelectronica saved
         */
    	update: function(facturaelectronica) {
			validate(facturaelectronica)
			var url = entityURL + '/' + facturaelectronica.id;
			return $http.put(url, facturaelectronica);
    	},

		/**
         * Delete facturaelectronica
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

