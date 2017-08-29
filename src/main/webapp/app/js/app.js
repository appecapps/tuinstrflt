'use strict';

// Add "endsWith" function to the String object
if (typeof String.prototype.endsWith !== 'function') {
    String.prototype.endsWith = function(suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}

// Declare app level module which depends on filters, and services
var myApp = angular.module('myApp', [
   'ngRoute'
  ,'ngResource' 
  ,'ngCookies'
  ,'i18n'
  ,'pascalprecht.translate'
  ,'tmh.dynamicLocale'
  ,'mgcrea.ngStrap.tooltip'
  ,'mgcrea.ngStrap.datepicker'
  ,'myApp.filters'
  ,'myApp.services'
  ,'myApp.directives'
  ,'messageHandler.module'
  ,'chofer.module'
  ,'chofervehiculo.module'
  ,'ciudad.module'
  ,'cliente.module'
  ,'clienteviaje.module'
  ,'clienteviajeservicio.module'
  ,'color.module'
  ,'componentedocumento.module'
  ,'componentefinanciero.module'
  ,'destinohorario.module'
  ,'documento.module'
  ,'entidad.module'
  ,'entidadrol.module'
  ,'estado.module'
  ,'facturaelectronica.module'
  ,'formapago.module'
  ,'formapagodocumento.module'
  ,'horario.module'
  ,'itemmenu.module'
  ,'marca.module'
  ,'menu.module'
  ,'modelo.module'
  ,'provincia.module'
  ,'rol.module'
  ,'rolitemmenu.module'
  ,'servicio.module'
  ,'tipocartera.module'
  ,'tipocomponentefinanciero.module'
  ,'tipodocumento.module'
  ,'tipovehiculo.module'
  ,'variables.module'
  ,'vehiculo.module'
  ,'vehiculoservicio.module'
  ,'viaje.module'
]);

/**
 * Main configuration
 */
myApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/welcome.html'});
  $routeProvider.otherwise({redirectTo: '/'});
}]);
