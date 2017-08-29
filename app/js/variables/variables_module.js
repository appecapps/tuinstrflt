'use strict';

/* Module for Variables */

var variablesModule = angular.module('variables.module', ['myApp']);

/**
 * Module for variables
 */
variablesModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/variables',    {templateUrl: 'partials/variables/variables_list.html', controller: 'VariablesCtrl'});
    $routeProvider.when('/variables/new', {templateUrl: 'partials/variables/variables_form.html', controller: 'VariablesCtrl'});
    $routeProvider.when('/variables/:id', {templateUrl: 'partials/variables/variables_form.html', controller: 'VariablesCtrl'});
}]);
