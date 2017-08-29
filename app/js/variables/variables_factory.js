'use strict';

/**
 * Factory for Variables
 */
variablesModule.factory('Variables', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage variables
    var entityURL = restURL + '/variables';
	
	/**
     * Validate variables
     * @param variables variables
     * @throws validation exception
     */
	var validate = function (variables) {
		var errors = [];
        if( variables.id == null || variables.id == '' ) {
			errors.push('variables.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all variabless as list items
         * @return all variabless as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/variables');
    	},

        /**
         * Get all variabless
         * @return all variabless
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get variables
         * @param id id
         * @return variables
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new variables
         * @param variables variables
         * @return variables saved
         */
		create: function(variables) {
			validate(variables)
			var url = entityURL;
			return $http.post(url, variables);
    	},

        /**
         * Update variables
         * @param variables variables
         * @return variables saved
         */
    	update: function(variables) {
			validate(variables)
			var url = entityURL + '/' + variables.id;
			return $http.put(url, variables);
    	},

		/**
         * Delete variables
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

