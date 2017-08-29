'use strict';

/* Module for Itemmenu */

var itemmenuModule = angular.module('itemmenu.module', ['myApp']);

/**
 * Module for itemmenu
 */
itemmenuModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/itemmenu',    {templateUrl: 'partials/itemmenu/itemmenu_list.html', controller: 'ItemmenuCtrl'});
    $routeProvider.when('/itemmenu/new', {templateUrl: 'partials/itemmenu/itemmenu_form.html', controller: 'ItemmenuCtrl'});
    $routeProvider.when('/itemmenu/:id', {templateUrl: 'partials/itemmenu/itemmenu_form.html', controller: 'ItemmenuCtrl'});
}]);
