'use strict';

/* Module for Destinohorario */

var destinohorarioModule = angular.module('destinohorario.module', ['myApp']);

/**
 * Module for destinohorario
 */
destinohorarioModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/destinohorario',    {templateUrl: 'partials/destinohorario/destinohorario_list.html', controller: 'DestinohorarioCtrl'});
    $routeProvider.when('/destinohorario/new', {templateUrl: 'partials/destinohorario/destinohorario_form.html', controller: 'DestinohorarioCtrl'});
    $routeProvider.when('/destinohorario/:id', {templateUrl: 'partials/destinohorario/destinohorario_form.html', controller: 'DestinohorarioCtrl'});
}]);
