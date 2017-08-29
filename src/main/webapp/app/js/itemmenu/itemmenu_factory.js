'use strict';

/**
 * Factory for Itemmenu
 */
itemmenuModule.factory('Itemmenu', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage itemmenu
    var entityURL = restURL + '/itemmenu';
	
	/**
     * Validate itemmenu
     * @param itemmenu itemmenu
     * @throws validation exception
     */
	var validate = function (itemmenu) {
		var errors = [];
        if( itemmenu.id == null || itemmenu.id == '' ) {
			errors.push('itemmenu.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all itemmenus as list items
         * @return all itemmenus as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/itemmenu');
    	},

        /**
         * Get all itemmenus
         * @return all itemmenus
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get itemmenu
         * @param id id
         * @return itemmenu
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new itemmenu
         * @param itemmenu itemmenu
         * @return itemmenu saved
         */
		create: function(itemmenu) {
			validate(itemmenu)
			var url = entityURL;
			return $http.post(url, itemmenu);
    	},

        /**
         * Update itemmenu
         * @param itemmenu itemmenu
         * @return itemmenu saved
         */
    	update: function(itemmenu) {
			validate(itemmenu)
			var url = entityURL + '/' + itemmenu.id;
			return $http.put(url, itemmenu);
    	},

		/**
         * Delete itemmenu
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

