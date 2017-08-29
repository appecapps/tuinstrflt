'use strict';

/* Module for Facturaelectronica */

var facturaelectronicaModule = angular.module('facturaelectronica.module', ['myApp']);

/**
 * Module for facturaelectronica
 */
facturaelectronicaModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/facturaelectronica',    {templateUrl: 'partials/facturaelectronica/facturaelectronica_list.html', controller: 'FacturaelectronicaCtrl'});
    $routeProvider.when('/facturaelectronica/new', {templateUrl: 'partials/facturaelectronica/facturaelectronica_form.html', controller: 'FacturaelectronicaCtrl'});
    $routeProvider.when('/facturaelectronica/:id', {templateUrl: 'partials/facturaelectronica/facturaelectronica_form.html', controller: 'FacturaelectronicaCtrl'});
}]);
