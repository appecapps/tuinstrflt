'use strict';

/* Module for Clienteviajeservicio */

var clienteviajeservicioModule = angular.module('clienteviajeservicio.module', ['myApp']);

/**
 * Module for clienteviajeservicio
 */
clienteviajeservicioModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/clienteviajeservicio',    {templateUrl: 'partials/clienteviajeservicio/clienteviajeservicio_list.html', controller: 'ClienteviajeservicioCtrl'});
    $routeProvider.when('/clienteviajeservicio/new', {templateUrl: 'partials/clienteviajeservicio/clienteviajeservicio_form.html', controller: 'ClienteviajeservicioCtrl'});
    $routeProvider.when('/clienteviajeservicio/:id', {templateUrl: 'partials/clienteviajeservicio/clienteviajeservicio_form.html', controller: 'ClienteviajeservicioCtrl'});
}]);
