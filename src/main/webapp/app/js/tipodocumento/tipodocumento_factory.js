'use strict';

/**
 * Factory for Tipodocumento
 */
tipodocumentoModule.factory('Tipodocumento', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage tipodocumento
    var entityURL = restURL + '/tipodocumento';
	
	/**
     * Validate tipodocumento
     * @param tipodocumento tipodocumento
     * @throws validation exception
     */
	var validate = function (tipodocumento) {
		var errors = [];
        if( tipodocumento.id == null || tipodocumento.id == '' ) {
			errors.push('tipodocumento.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all tipodocumentos as list items
         * @return all tipodocumentos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/tipodocumento');
    	},

        /**
         * Get all tipodocumentos
         * @return all tipodocumentos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get tipodocumento
         * @param id id
         * @return tipodocumento
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new tipodocumento
         * @param tipodocumento tipodocumento
         * @return tipodocumento saved
         */
		create: function(tipodocumento) {
			validate(tipodocumento)
			var url = entityURL;
			return $http.post(url, tipodocumento);
    	},

        /**
         * Update tipodocumento
         * @param tipodocumento tipodocumento
         * @return tipodocumento saved
         */
    	update: function(tipodocumento) {
			validate(tipodocumento)
			var url = entityURL + '/' + tipodocumento.id;
			return $http.put(url, tipodocumento);
    	},

		/**
         * Delete tipodocumento
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

