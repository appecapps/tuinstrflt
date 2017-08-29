'use strict';

/* Module for Tipodocumento */

var tipodocumentoModule = angular.module('tipodocumento.module', ['myApp']);

/**
 * Module for tipodocumento
 */
tipodocumentoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/tipodocumento',    {templateUrl: 'partials/tipodocumento/tipodocumento_list.html', controller: 'TipodocumentoCtrl'});
    $routeProvider.when('/tipodocumento/new', {templateUrl: 'partials/tipodocumento/tipodocumento_form.html', controller: 'TipodocumentoCtrl'});
    $routeProvider.when('/tipodocumento/:id', {templateUrl: 'partials/tipodocumento/tipodocumento_form.html', controller: 'TipodocumentoCtrl'});
}]);
