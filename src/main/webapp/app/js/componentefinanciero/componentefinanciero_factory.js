'use strict';

/**
 * Factory for Componentefinanciero
 */
componentefinancieroModule.factory('Componentefinanciero', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage componentefinanciero
    var entityURL = restURL + '/componentefinanciero';
	
	/**
     * Validate componentefinanciero
     * @param componentefinanciero componentefinanciero
     * @throws validation exception
     */
	var validate = function (componentefinanciero) {
		var errors = [];
        if( componentefinanciero.id == null || componentefinanciero.id == '' ) {
			errors.push('componentefinanciero.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all componentefinancieros as list items
         * @return all componentefinancieros as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/componentefinanciero');
    	},

        /**
         * Get all componentefinancieros
         * @return all componentefinancieros
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get componentefinanciero
         * @param id id
         * @return componentefinanciero
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new componentefinanciero
         * @param componentefinanciero componentefinanciero
         * @return componentefinanciero saved
         */
		create: function(componentefinanciero) {
			validate(componentefinanciero)
			var url = entityURL;
			return $http.post(url, componentefinanciero);
    	},

        /**
         * Update componentefinanciero
         * @param componentefinanciero componentefinanciero
         * @return componentefinanciero saved
         */
    	update: function(componentefinanciero) {
			validate(componentefinanciero)
			var url = entityURL + '/' + componentefinanciero.id;
			return $http.put(url, componentefinanciero);
    	},

		/**
         * Delete componentefinanciero
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

