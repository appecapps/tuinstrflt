'use strict';

/* Module for Servicio */

var servicioModule = angular.module('servicio.module', ['myApp']);

/**
 * Module for servicio
 */
servicioModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/servicio',    {templateUrl: 'partials/servicio/servicio_list.html', controller: 'ServicioCtrl'});
    $routeProvider.when('/servicio/new', {templateUrl: 'partials/servicio/servicio_form.html', controller: 'ServicioCtrl'});
    $routeProvider.when('/servicio/:id', {templateUrl: 'partials/servicio/servicio_form.html', controller: 'ServicioCtrl'});
}]);
