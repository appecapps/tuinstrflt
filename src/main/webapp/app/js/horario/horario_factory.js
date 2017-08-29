'use strict';

/**
 * Factory for Horario
 */
horarioModule.factory('Horario', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage horario
    var entityURL = restURL + '/horario';
	
	/**
     * Validate horario
     * @param horario horario
     * @throws validation exception
     */
	var validate = function (horario) {
		var errors = [];
        if( horario.id == null || horario.id == '' ) {
			errors.push('horario.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all horarios as list items
         * @return all horarios as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/horario');
    	},

        /**
         * Get all horarios
         * @return all horarios
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get horario
         * @param id id
         * @return horario
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new horario
         * @param horario horario
         * @return horario saved
         */
		create: function(horario) {
			validate(horario)
			var url = entityURL;
			return $http.post(url, horario);
    	},

        /**
         * Update horario
         * @param horario horario
         * @return horario saved
         */
    	update: function(horario) {
			validate(horario)
			var url = entityURL + '/' + horario.id;
			return $http.put(url, horario);
    	},

		/**
         * Delete horario
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

