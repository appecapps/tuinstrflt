'use strict';

/* Module for Componentedocumento */

var componentedocumentoModule = angular.module('componentedocumento.module', ['myApp']);

/**
 * Module for componentedocumento
 */
componentedocumentoModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/componentedocumento',    {templateUrl: 'partials/componentedocumento/componentedocumento_list.html', controller: 'ComponentedocumentoCtrl'});
    $routeProvider.when('/componentedocumento/new', {templateUrl: 'partials/componentedocumento/componentedocumento_form.html', controller: 'ComponentedocumentoCtrl'});
    $routeProvider.when('/componentedocumento/:id', {templateUrl: 'partials/componentedocumento/componentedocumento_form.html', controller: 'ComponentedocumentoCtrl'});
}]);
