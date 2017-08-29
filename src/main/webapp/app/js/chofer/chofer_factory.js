'use strict';

/**
 * Factory for Chofer
 */
choferModule.factory('Chofer', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage chofer
    var entityURL = restURL + '/chofer';
	
	/**
     * Validate chofer
     * @param chofer chofer
     * @throws validation exception
     */
	var validate = function (chofer) {
		var errors = [];
        if( chofer.id == null || chofer.id == '' ) {
			errors.push('chofer.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all chofers as list items
         * @return all chofers as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/chofer');
    	},

        /**
         * Get all chofers
         * @return all chofers
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get chofer
         * @param id id
         * @return chofer
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new chofer
         * @param chofer chofer
         * @return chofer saved
         */
		create: function(chofer) {
			validate(chofer)
			var url = entityURL;
			return $http.post(url, chofer);
    	},

        /**
         * Update chofer
         * @param chofer chofer
         * @return chofer saved
         */
    	update: function(chofer) {
			validate(chofer)
			var url = entityURL + '/' + chofer.id;
			return $http.put(url, chofer);
    	},

		/**
         * Delete chofer
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

