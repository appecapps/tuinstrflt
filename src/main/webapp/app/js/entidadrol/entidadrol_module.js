'use strict';

/* Module for Entidadrol */

var entidadrolModule = angular.module('entidadrol.module', ['myApp']);

/**
 * Module for entidadrol
 */
entidadrolModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/entidadrol',    {templateUrl: 'partials/entidadrol/entidadrol_list.html', controller: 'EntidadrolCtrl'});
    $routeProvider.when('/entidadrol/new', {templateUrl: 'partials/entidadrol/entidadrol_form.html', controller: 'EntidadrolCtrl'});
    $routeProvider.when('/entidadrol/:id', {templateUrl: 'partials/entidadrol/entidadrol_form.html', controller: 'EntidadrolCtrl'});
}]);
