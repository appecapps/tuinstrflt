'use strict';

/* Module for Formapago */

var formapagoModule = angular.module('formapago.module', ['myApp']);

/**
 * Module for formapago
 */
formapagoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/formapago',    {templateUrl: 'partials/formapago/formapago_list.html', controller: 'FormapagoCtrl'});
    $routeProvider.when('/formapago/new', {templateUrl: 'partials/formapago/formapago_form.html', controller: 'FormapagoCtrl'});
    $routeProvider.when('/formapago/:id', {templateUrl: 'partials/formapago/formapago_form.html', controller: 'FormapagoCtrl'});
}]);
