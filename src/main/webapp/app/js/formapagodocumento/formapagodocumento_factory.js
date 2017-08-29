'use strict';

/**
 * Factory for Formapagodocumento
 */
formapagodocumentoModule.factory('Formapagodocumento', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage formapagodocumento
    var entityURL = restURL + '/formapagodocumento';
	
	/**
     * Validate formapagodocumento
     * @param formapagodocumento formapagodocumento
     * @throws validation exception
     */
	var validate = function (formapagodocumento) {
		var errors = [];
        if( formapagodocumento.id == null || formapagodocumento.id == '' ) {
			errors.push('formapagodocumento.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all formapagodocumentos as list items
         * @return all formapagodocumentos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/formapagodocumento');
    	},

        /**
         * Get all formapagodocumentos
         * @return all formapagodocumentos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get formapagodocumento
         * @param id id
         * @return formapagodocumento
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new formapagodocumento
         * @param formapagodocumento formapagodocumento
         * @return formapagodocumento saved
         */
		create: function(formapagodocumento) {
			validate(formapagodocumento)
			var url = entityURL;
			return $http.post(url, formapagodocumento);
    	},

        /**
         * Update formapagodocumento
         * @param formapagodocumento formapagodocumento
         * @return formapagodocumento saved
         */
    	update: function(formapagodocumento) {
			validate(formapagodocumento)
			var url = entityURL + '/' + formapagodocumento.id;
			return $http.put(url, formapagodocumento);
    	},

		/**
         * Delete formapagodocumento
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

