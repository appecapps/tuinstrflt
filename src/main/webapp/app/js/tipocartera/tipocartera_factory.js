'use strict';

/**
 * Factory for Tipocartera
 */
tipocarteraModule.factory('Tipocartera', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage tipocartera
    var entityURL = restURL + '/tipocartera';
	
	/**
     * Validate tipocartera
     * @param tipocartera tipocartera
     * @throws validation exception
     */
	var validate = function (tipocartera) {
		var errors = [];
        if( tipocartera.id == null || tipocartera.id == '' ) {
			errors.push('tipocartera.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all tipocarteras as list items
         * @return all tipocarteras as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/tipocartera');
    	},

        /**
         * Get all tipocarteras
         * @return all tipocarteras
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get tipocartera
         * @param id id
         * @return tipocartera
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new tipocartera
         * @param tipocartera tipocartera
         * @return tipocartera saved
         */
		create: function(tipocartera) {
			validate(tipocartera)
			var url = entityURL;
			return $http.post(url, tipocartera);
    	},

        /**
         * Update tipocartera
         * @param tipocartera tipocartera
         * @return tipocartera saved
         */
    	update: function(tipocartera) {
			validate(tipocartera)
			var url = entityURL + '/' + tipocartera.id;
			return $http.put(url, tipocartera);
    	},

		/**
         * Delete tipocartera
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

