'use strict';

/* Module for Menu */

var menuModule = angular.module('menu.module', ['myApp']);

/**
 * Module for menu
 */
menuModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/menu',    {templateUrl: 'partials/menu/menu_list.html', controller: 'MenuCtrl'});
    $routeProvider.when('/menu/new', {templateUrl: 'partials/menu/menu_form.html', controller: 'MenuCtrl'});
    $routeProvider.when('/menu/:id', {templateUrl: 'partials/menu/menu_form.html', controller: 'MenuCtrl'});
}]);
