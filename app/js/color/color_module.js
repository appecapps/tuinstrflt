'use strict';

/* Module for Color */

var colorModule = angular.module('color.module', ['myApp']);

/**
 * Module for color
 */
colorModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/color',    {templateUrl: 'partials/color/color_list.html', controller: 'ColorCtrl'});
    $routeProvider.when('/color/new', {templateUrl: 'partials/color/color_form.html', controller: 'ColorCtrl'});
    $routeProvider.when('/color/:id', {templateUrl: 'partials/color/color_form.html', controller: 'ColorCtrl'});
}]);
