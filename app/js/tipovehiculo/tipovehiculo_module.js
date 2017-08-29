'use strict';

/* Module for Tipovehiculo */

var tipovehiculoModule = angular.module('tipovehiculo.module', ['myApp']);

/**
 * Module for tipovehiculo
 */
tipovehiculoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/tipovehiculo',    {templateUrl: 'partials/tipovehiculo/tipovehiculo_list.html', controller: 'TipovehiculoCtrl'});
    $routeProvider.when('/tipovehiculo/new', {templateUrl: 'partials/tipovehiculo/tipovehiculo_form.html', controller: 'TipovehiculoCtrl'});
    $routeProvider.when('/tipovehiculo/:id', {templateUrl: 'partials/tipovehiculo/tipovehiculo_form.html', controller: 'TipovehiculoCtrl'});
}]);
