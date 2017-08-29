'use strict';

/* Module for Documento */

var documentoModule = angular.module('documento.module', ['myApp']);

/**
 * Module for documento
 */
documentoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/documento',    {templateUrl: 'partials/documento/documento_list.html', controller: 'DocumentoCtrl'});
    $routeProvider.when('/documento/new', {templateUrl: 'partials/documento/documento_form.html', controller: 'DocumentoCtrl'});
    $routeProvider.when('/documento/:id', {templateUrl: 'partials/documento/documento_form.html', controller: 'DocumentoCtrl'});
}]);
