'use strict';

/**
 * Factory for Destinohorario
 */
destinohorarioModule.factory('Destinohorario', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage destinohorario
    var entityURL = restURL + '/destinohorario';
	
	/**
     * Validate destinohorario
     * @param destinohorario destinohorario
     * @throws validation exception
     */
	var validate = function (destinohorario) {
		var errors = [];
        if( destinohorario.id == null || destinohorario.id == '' ) {
			errors.push('destinohorario.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all destinohorarios as list items
         * @return all destinohorarios as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/destinohorario');
    	},

        /**
         * Get all destinohorarios
         * @return all destinohorarios
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get destinohorario
         * @param id id
         * @return destinohorario
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new destinohorario
         * @param destinohorario destinohorario
         * @return destinohorario saved
         */
		create: function(destinohorario) {
			validate(destinohorario)
			var url = entityURL;
			return $http.post(url, destinohorario);
    	},

        /**
         * Update destinohorario
         * @param destinohorario destinohorario
         * @return destinohorario saved
         */
    	update: function(destinohorario) {
			validate(destinohorario)
			var url = entityURL + '/' + destinohorario.id;
			return $http.put(url, destinohorario);
    	},

		/**
         * Delete destinohorario
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

