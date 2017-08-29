'use strict';

/* Module for Estado */

var estadoModule = angular.module('estado.module', ['myApp']);

/**
 * Module for estado
 */
estadoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/estado',    {templateUrl: 'partials/estado/estado_list.html', controller: 'EstadoCtrl'});
    $routeProvider.when('/estado/new', {templateUrl: 'partials/estado/estado_form.html', controller: 'EstadoCtrl'});
    $routeProvider.when('/estado/:id', {templateUrl: 'partials/estado/estado_form.html', controller: 'EstadoCtrl'});
}]);
