'use strict';

/**
 * Factory for Componentedocumento
 */
componentedocumentoModule.factory('Componentedocumento', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage componentedocumento
    var entityURL = restURL + '/componentedocumento';
	
	/**
     * Validate componentedocumento
     * @param componentedocumento componentedocumento
     * @throws validation exception
     */
	var validate = function (componentedocumento) {
		var errors = [];
        if( componentedocumento.id == null || componentedocumento.id == '' ) {
			errors.push('componentedocumento.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all componentedocumentos as list items
         * @return all componentedocumentos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/componentedocumento');
    	},

        /**
         * Get all componentedocumentos
         * @return all componentedocumentos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get componentedocumento
         * @param id id
         * @return componentedocumento
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new componentedocumento
         * @param componentedocumento componentedocumento
         * @return componentedocumento saved
         */
		create: function(componentedocumento) {
			validate(componentedocumento)
			var url = entityURL;
			return $http.post(url, componentedocumento);
    	},

        /**
         * Update componentedocumento
         * @param componentedocumento componentedocumento
         * @return componentedocumento saved
         */
    	update: function(componentedocumento) {
			validate(componentedocumento)
			var url = entityURL + '/' + componentedocumento.id;
			return $http.put(url, componentedocumento);
    	},

		/**
         * Delete componentedocumento
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

