'use strict';

/**
 * Factory for Documento
 */
documentoModule.factory('Documento', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage documento
    var entityURL = restURL + '/documento';
	
	/**
     * Validate documento
     * @param documento documento
     * @throws validation exception
     */
	var validate = function (documento) {
		var errors = [];
        if( documento.id == null || documento.id == '' ) {
			errors.push('documento.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all documentos as list items
         * @return all documentos as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/documento');
    	},

        /**
         * Get all documentos
         * @return all documentos
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get documento
         * @param id id
         * @return documento
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new documento
         * @param documento documento
         * @return documento saved
         */
		create: function(documento) {
			validate(documento)
			var url = entityURL;
			return $http.post(url, documento);
    	},

        /**
         * Update documento
         * @param documento documento
         * @return documento saved
         */
    	update: function(documento) {
			validate(documento)
			var url = entityURL + '/' + documento.id;
			return $http.put(url, documento);
    	},

		/**
         * Delete documento
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

