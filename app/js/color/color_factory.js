'use strict';

/**
 * Factory for Color
 */
colorModule.factory('Color', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage color
    var entityURL = restURL + '/color';
	
	/**
     * Validate color
     * @param color color
     * @throws validation exception
     */
	var validate = function (color) {
		var errors = [];
        if( color.id == null || color.id == '' ) {
			errors.push('color.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all colors as list items
         * @return all colors as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/color');
    	},

        /**
         * Get all colors
         * @return all colors
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get color
         * @param id id
         * @return color
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new color
         * @param color color
         * @return color saved
         */
		create: function(color) {
			validate(color)
			var url = entityURL;
			return $http.post(url, color);
    	},

        /**
         * Update color
         * @param color color
         * @return color saved
         */
    	update: function(color) {
			validate(color)
			var url = entityURL + '/' + color.id;
			return $http.put(url, color);
    	},

		/**
         * Delete color
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

