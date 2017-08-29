'use strict';

/* Module for Viaje */

var viajeModule = angular.module('viaje.module', ['myApp']);

/**
 * Module for viaje
 */
viajeModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/viaje',    {templateUrl: 'partials/viaje/viaje_list.html', controller: 'ViajeCtrl'});
    $routeProvider.when('/viaje/new', {templateUrl: 'partials/viaje/viaje_form.html', controller: 'ViajeCtrl'});
    $routeProvider.when('/viaje/:id', {templateUrl: 'partials/viaje/viaje_form.html', controller: 'ViajeCtrl'});
}]);
