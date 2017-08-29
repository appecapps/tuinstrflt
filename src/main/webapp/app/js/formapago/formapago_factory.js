'use strict';

/**
 * Factory for Formapago
 */
formapagoModule.factory('Formapago', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage formapago
    var entityURL = restURL + '/formapago';
	
	/**
     * Validate formapago
     * @param formapago formapago
     * @throws validation exception
     */
	var validate = function (formapago) {
		var errors = [];
        if( formapago.id == null || formapago.id == '' ) {
			errors.push('formapago.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all formapagos as list items
         * @return all formapagos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/formapago');
    	},

        /**
         * Get all formapagos
         * @return all formapagos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get formapago
         * @param id id
         * @return formapago
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new formapago
         * @param formapago formapago
         * @return formapago saved
         */
		create: function(formapago) {
			validate(formapago)
			var url = entityURL;
			return $http.post(url, formapago);
    	},

        /**
         * Update formapago
         * @param formapago formapago
         * @return formapago saved
         */
    	update: function(formapago) {
			validate(formapago)
			var url = entityURL + '/' + formapago.id;
			return $http.put(url, formapago);
    	},

		/**
         * Delete formapago
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

