'use strict';

/**
 * Factory for Rolitemmenu
 */
rolitemmenuModule.factory('Rolitemmenu', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage rolitemmenu
    var entityURL = restURL + '/rolitemmenu';
	
	/**
     * Validate rolitemmenu
     * @param rolitemmenu rolitemmenu
     * @throws validation exception
     */
	var validate = function (rolitemmenu) {
		var errors = [];
        if( rolitemmenu.id == null || rolitemmenu.id == '' ) {
			errors.push('rolitemmenu.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all rolitemmenus as list items
         * @return all rolitemmenus as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/rolitemmenu');
    	},

        /**
         * Get all rolitemmenus
         * @return all rolitemmenus
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get rolitemmenu
         * @param id id
         * @return rolitemmenu
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new rolitemmenu
         * @param rolitemmenu rolitemmenu
         * @return rolitemmenu saved
         */
		create: function(rolitemmenu) {
			validate(rolitemmenu)
			var url = entityURL;
			return $http.post(url, rolitemmenu);
    	},

        /**
         * Update rolitemmenu
         * @param rolitemmenu rolitemmenu
         * @return rolitemmenu saved
         */
    	update: function(rolitemmenu) {
			validate(rolitemmenu)
			var url = entityURL + '/' + rolitemmenu.id;
			return $http.put(url, rolitemmenu);
    	},

		/**
         * Delete rolitemmenu
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

