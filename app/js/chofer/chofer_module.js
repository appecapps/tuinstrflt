'use strict';

/* Module for Chofer */

var choferModule = angular.module('chofer.module', ['myApp']);

/**
 * Module for chofer
 */
choferModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/chofer',    {templateUrl: 'partials/chofer/chofer_list.html', controller: 'ChoferCtrl'});
    $routeProvider.when('/chofer/new', {templateUrl: 'partials/chofer/chofer_form.html', controller: 'ChoferCtrl'});
    $routeProvider.when('/chofer/:id', {templateUrl: 'partials/chofer/chofer_form.html', controller: 'ChoferCtrl'});
}]);
