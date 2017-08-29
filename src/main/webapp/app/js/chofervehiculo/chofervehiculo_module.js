'use strict';

/* Module for Chofervehiculo */

var chofervehiculoModule = angular.module('chofervehiculo.module', ['myApp']);

/**
 * Module for chofervehiculo
 */
chofervehiculoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/chofervehiculo',    {templateUrl: 'partials/chofervehiculo/chofervehiculo_list.html', controller: 'ChofervehiculoCtrl'});
    $routeProvider.when('/chofervehiculo/new', {templateUrl: 'partials/chofervehiculo/chofervehiculo_form.html', controller: 'ChofervehiculoCtrl'});
    $routeProvider.when('/chofervehiculo/:id', {templateUrl: 'partials/chofervehiculo/chofervehiculo_form.html', controller: 'ChofervehiculoCtrl'});
}]);
