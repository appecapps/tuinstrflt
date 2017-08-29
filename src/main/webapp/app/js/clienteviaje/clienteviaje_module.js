'use strict';

/* Module for Clienteviaje */

var clienteviajeModule = angular.module('clienteviaje.module', ['myApp']);

/**
 * Module for clienteviaje
 */
clienteviajeModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/clienteviaje',    {templateUrl: 'partials/clienteviaje/clienteviaje_list.html', controller: 'ClienteviajeCtrl'});
    $routeProvider.when('/clienteviaje/new', {templateUrl: 'partials/clienteviaje/clienteviaje_form.html', controller: 'ClienteviajeCtrl'});
    $routeProvider.when('/clienteviaje/:id', {templateUrl: 'partials/clienteviaje/clienteviaje_form.html', controller: 'ClienteviajeCtrl'});
}]);
