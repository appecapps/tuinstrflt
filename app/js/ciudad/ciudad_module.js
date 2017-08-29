'use strict';

/* Module for Ciudad */

var ciudadModule = angular.module('ciudad.module', ['myApp']);

/**
 * Module for ciudad
 */
ciudadModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/ciudad',    {templateUrl: 'partials/ciudad/ciudad_list.html', controller: 'CiudadCtrl'});
    $routeProvider.when('/ciudad/new', {templateUrl: 'partials/ciudad/ciudad_form.html', controller: 'CiudadCtrl'});
    $routeProvider.when('/ciudad/:id', {templateUrl: 'partials/ciudad/ciudad_form.html', controller: 'CiudadCtrl'});
}]);
