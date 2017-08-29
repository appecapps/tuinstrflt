'use strict';

/* Module for Marca */

var marcaModule = angular.module('marca.module', ['myApp']);

/**
 * Module for marca
 */
marcaModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/marca',    {templateUrl: 'partials/marca/marca_list.html', controller: 'MarcaCtrl'});
    $routeProvider.when('/marca/new', {templateUrl: 'partials/marca/marca_form.html', controller: 'MarcaCtrl'});
    $routeProvider.when('/marca/:id', {templateUrl: 'partials/marca/marca_form.html', controller: 'MarcaCtrl'});
}]);
