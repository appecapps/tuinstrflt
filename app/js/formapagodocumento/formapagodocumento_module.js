'use strict';

/* Module for Formapagodocumento */

var formapagodocumentoModule = angular.module('formapagodocumento.module', ['myApp']);

/**
 * Module for formapagodocumento
 */
formapagodocumentoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/formapagodocumento',    {templateUrl: 'partials/formapagodocumento/formapagodocumento_list.html', controller: 'FormapagodocumentoCtrl'});
    $routeProvider.when('/formapagodocumento/new', {templateUrl: 'partials/formapagodocumento/formapagodocumento_form.html', controller: 'FormapagodocumentoCtrl'});
    $routeProvider.when('/formapagodocumento/:id', {templateUrl: 'partials/formapagodocumento/formapagodocumento_form.html', controller: 'FormapagodocumentoCtrl'});
}]);
