'use strict';

/**
 * Factory for Rol
 */
rolModule.factory('Rol', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage rol
    var entityURL = restURL + '/rol';
	
	/**
     * Validate rol
     * @param rol rol
     * @throws validation exception
     */
	var validate = function (rol) {
		var errors = [];
        if( rol.id == null || rol.id == '' ) {
			errors.push('rol.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all rols as list items
         * @return all rols as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/rol');
    	},

        /**
         * Get all rols
         * @return all rols
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get rol
         * @param id id
         * @return rol
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new rol
         * @param rol rol
         * @return rol saved
         */
		create: function(rol) {
			validate(rol)
			var url = entityURL;
			return $http.post(url, rol);
    	},

        /**
         * Update rol
         * @param rol rol
         * @return rol saved
         */
    	update: function(rol) {
			validate(rol)
			var url = entityURL + '/' + rol.id;
			return $http.put(url, rol);
    	},

		/**
         * Delete rol
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

