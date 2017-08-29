'use strict';

/* Module for Vehiculo */

var vehiculoModule = angular.module('vehiculo.module', ['myApp']);

/**
 * Module for vehiculo
 */
vehiculoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/vehiculo',    {templateUrl: 'partials/vehiculo/vehiculo_list.html', controller: 'VehiculoCtrl'});
    $routeProvider.when('/vehiculo/new', {templateUrl: 'partials/vehiculo/vehiculo_form.html', controller: 'VehiculoCtrl'});
    $routeProvider.when('/vehiculo/:id', {templateUrl: 'partials/vehiculo/vehiculo_form.html', controller: 'VehiculoCtrl'});
}]);
