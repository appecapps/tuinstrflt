'use strict';

/* Module for Tipocartera */

var tipocarteraModule = angular.module('tipocartera.module', ['myApp']);

/**
 * Module for tipocartera
 */
tipocarteraModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/tipocartera',    {templateUrl: 'partials/tipocartera/tipocartera_list.html', controller: 'TipocarteraCtrl'});
    $routeProvider.when('/tipocartera/new', {templateUrl: 'partials/tipocartera/tipocartera_form.html', controller: 'TipocarteraCtrl'});
    $routeProvider.when('/tipocartera/:id', {templateUrl: 'partials/tipocartera/tipocartera_form.html', controller: 'TipocarteraCtrl'});
}]);
