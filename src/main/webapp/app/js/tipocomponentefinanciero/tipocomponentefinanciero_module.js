'use strict';

/* Module for Tipocomponentefinanciero */

var tipocomponentefinancieroModule = angular.module('tipocomponentefinanciero.module', ['myApp']);

/**
 * Module for tipocomponentefinanciero
 */
tipocomponentefinancieroModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/tipocomponentefinanciero',    {templateUrl: 'partials/tipocomponentefinanciero/tipocomponentefinanciero_list.html', controller: 'TipocomponentefinancieroCtrl'});
    $routeProvider.when('/tipocomponentefinanciero/new', {templateUrl: 'partials/tipocomponentefinanciero/tipocomponentefinanciero_form.html', controller: 'TipocomponentefinancieroCtrl'});
    $routeProvider.when('/tipocomponentefinanciero/:id', {templateUrl: 'partials/tipocomponentefinanciero/tipocomponentefinanciero_form.html', controller: 'TipocomponentefinancieroCtrl'});
}]);
