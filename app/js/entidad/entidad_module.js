'use strict';

/* Module for Entidad */

var entidadModule = angular.module('entidad.module', ['myApp']);

/**
 * Module for entidad
 */
entidadModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/entidad',    {templateUrl: 'partials/entidad/entidad_list.html', controller: 'EntidadCtrl'});
    $routeProvider.when('/entidad/new', {templateUrl: 'partials/entidad/entidad_form.html', controller: 'EntidadCtrl'});
    $routeProvider.when('/entidad/:id', {templateUrl: 'partials/entidad/entidad_form.html', controller: 'EntidadCtrl'});
}]);
