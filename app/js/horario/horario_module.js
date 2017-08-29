'use strict';

/* Module for Horario */

var horarioModule = angular.module('horario.module', ['myApp']);

/**
 * Module for horario
 */
horarioModule.config(['$routeProvider', function($routeProvider) {
    // Pages routes
    $routeProvider.when('/horario',    {templateUrl: 'partials/horario/horario_list.html', controller: 'HorarioCtrl'});
    $routeProvider.when('/horario/new', {templateUrl: 'partials/horario/horario_form.html', controller: 'HorarioCtrl'});
    $routeProvider.when('/horario/:id', {templateUrl: 'partials/horario/horario_form.html', controller: 'HorarioCtrl'});
}]);
