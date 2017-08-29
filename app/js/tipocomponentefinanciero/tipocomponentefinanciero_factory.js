'use strict';

/**
 * Factory for Tipocomponentefinanciero
 */
tipocomponentefinancieroModule.factory('Tipocomponentefinanciero', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage tipocomponentefinanciero
    var entityURL = restURL + '/tipocomponentefinanciero';
	
	/**
     * Validate tipocomponentefinanciero
     * @param tipocomponentefinanciero tipocomponentefinanciero
     * @throws validation exception
     */
	var validate = function (tipocomponentefinanciero) {
		var errors = [];
        if( tipocomponentefinanciero.id == null || tipocomponentefinanciero.id == '' ) {
			errors.push('tipocomponentefinanciero.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all tipocomponentefinancieros as list items
         * @return all tipocomponentefinancieros as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/tipocomponentefinanciero');
    	},

        /**
         * Get all tipocomponentefinancieros
         * @return all tipocomponentefinancieros
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get tipocomponentefinanciero
         * @param id id
         * @return tipocomponentefinanciero
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new tipocomponentefinanciero
         * @param tipocomponentefinanciero tipocomponentefinanciero
         * @return tipocomponentefinanciero saved
         */
		create: function(tipocomponentefinanciero) {
			validate(tipocomponentefinanciero)
			var url = entityURL;
			return $http.post(url, tipocomponentefinanciero);
    	},

        /**
         * Update tipocomponentefinanciero
         * @param tipocomponentefinanciero tipocomponentefinanciero
         * @return tipocomponentefinanciero saved
         */
    	update: function(tipocomponentefinanciero) {
			validate(tipocomponentefinanciero)
			var url = entityURL + '/' + tipocomponentefinanciero.id;
			return $http.put(url, tipocomponentefinanciero);
    	},

		/**
         * Delete tipocomponentefinanciero
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

