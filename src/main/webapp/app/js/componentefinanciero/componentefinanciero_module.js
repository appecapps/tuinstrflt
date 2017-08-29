'use strict';

/* Module for Componentefinanciero */

var componentefinancieroModule = angular.module('componentefinanciero.module', ['myApp']);

/**
 * Module for componentefinanciero
 */
componentefinancieroModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/componentefinanciero',    {templateUrl: 'partials/componentefinanciero/componentefinanciero_list.html', controller: 'ComponentefinancieroCtrl'});
    $routeProvider.when('/componentefinanciero/new', {templateUrl: 'partials/componentefinanciero/componentefinanciero_form.html', controller: 'ComponentefinancieroCtrl'});
    $routeProvider.when('/componentefinanciero/:id', {templateUrl: 'partials/componentefinanciero/componentefinanciero_form.html', controller: 'ComponentefinancieroCtrl'});
}]);
