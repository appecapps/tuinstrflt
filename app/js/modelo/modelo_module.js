'use strict';

/* Module for Modelo */

var modeloModule = angular.module('modelo.module', ['myApp']);

/**
 * Module for modelo
 */
modeloModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/modelo',    {templateUrl: 'partials/modelo/modelo_list.html', controller: 'ModeloCtrl'});
    $routeProvider.when('/modelo/new', {templateUrl: 'partials/modelo/modelo_form.html', controller: 'ModeloCtrl'});
    $routeProvider.when('/modelo/:id', {templateUrl: 'partials/modelo/modelo_form.html', controller: 'ModeloCtrl'});
}]);
