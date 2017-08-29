'use strict';

/**
 * Factory for Menu
 */
menuModule.factory('Menu', ['$http', 'restURL', function($http, restURL) {

	// REST Service URL to manage menu
    var entityURL = restURL + '/menu';
	
	/**
     * Validate menu
     * @param menu menu
     * @throws validation exception
     */
	var validate = function (menu) {
		var errors = [];
        if( menu.id == null || menu.id == '' ) {
			errors.push('menu.id.not.defined');
		}
		if(errors.length > 0) {
			throw errors;
		}
    };
	
	return {
        /**
         * Get all menus as list items
         * @return all menus as list items
         */
    	getAllAsListItems: function() {
        	return $http.get(restURL + '/items/menu');
    	},

        /**
         * Get all menus
         * @return all menus
         */
    	getAll: function() {
        	return $http.get(entityURL);
    	},

        /**
         * Get menu
         * @param id id
         * @return menu
         */
    	get: function(id) {
    	    var url = entityURL + '/' + id;
        	return $http.get(url);
    	},

        /**
         * Create a new menu
         * @param menu menu
         * @return menu saved
         */
		create: function(menu) {
			validate(menu)
			var url = entityURL;
			return $http.post(url, menu);
    	},

        /**
         * Update menu
         * @param menu menu
         * @return menu saved
         */
    	update: function(menu) {
			validate(menu)
			var url = entityURL + '/' + menu.id;
			return $http.put(url, menu);
    	},

		/**
         * Delete menu
         * @param id id
         */
    	delete: function(id) {
        	var url = entityURL + '/' + id;
        	return $http.delete(url);
    	}
	};
	return $this;
}]);

