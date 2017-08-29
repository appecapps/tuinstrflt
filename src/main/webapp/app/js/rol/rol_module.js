'use strict';

/* Module for Rol */

var rolModule = angular.module('rol.module', ['myApp']);

/**
 * Module for rol
 */
rolModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/rol',    {templateUrl: 'partials/rol/rol_list.html', controller: 'RolCtrl'});
    $routeProvider.when('/rol/new', {templateUrl: 'partials/rol/rol_form.html', controller: 'RolCtrl'});
    $routeProvider.when('/rol/:id', {templateUrl: 'partials/rol/rol_form.html', controller: 'RolCtrl'});
}]);
