'use strict';

/* Module for Vehiculoservicio */

var vehiculoservicioModule = angular.module('vehiculoservicio.module', ['myApp']);

/**
 * Module for vehiculoservicio
 */
vehiculoservicioModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/vehiculoservicio',    {templateUrl: 'partials/vehiculoservicio/vehiculoservicio_list.html', controller: 'VehiculoservicioCtrl'});
    $routeProvider.when('/vehiculoservicio/new', {templateUrl: 'partials/vehiculoservicio/vehiculoservicio_form.html', controller: 'VehiculoservicioCtrl'});
    $routeProvider.when('/vehiculoservicio/:id', {templateUrl: 'partials/vehiculoservicio/vehiculoservicio_form.html', controller: 'VehiculoservicioCtrl'});
}]);
