'use strict';

/* Module for Provincia */

var provinciaModule = angular.module('provincia.module', ['myApp']);

/**
 * Module for provincia
 */
provinciaModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/provincia',    {templateUrl: 'partials/provincia/provincia_list.html', controller: 'ProvinciaCtrl'});
    $routeProvider.when('/provincia/new', {templateUrl: 'partials/provincia/provincia_form.html', controller: 'ProvinciaCtrl'});
    $routeProvider.when('/provincia/:id', {templateUrl: 'partials/provincia/provincia_form.html', controller: 'ProvinciaCtrl'});
}]);
