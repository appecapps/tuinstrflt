'use strict';

/* Module for Rolitemmenu */

var rolitemmenuModule = angular.module('rolitemmenu.module', ['myApp']);

/**
 * Module for rolitemmenu
 */
rolitemmenuModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/rolitemmenu',    {templateUrl: 'partials/rolitemmenu/rolitemmenu_list.html', controller: 'RolitemmenuCtrl'});
    $routeProvider.when('/rolitemmenu/new', {templateUrl: 'partials/rolitemmenu/rolitemmenu_form.html', controller: 'RolitemmenuCtrl'});
    $routeProvider.when('/rolitemmenu/:id', {templateUrl: 'partials/rolitemmenu/rolitemmenu_form.html', controller: 'RolitemmenuCtrl'});
}]);
