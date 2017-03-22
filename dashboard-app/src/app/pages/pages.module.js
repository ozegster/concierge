(function () {
  'use strict';

  angular.module('ConciergeApp.pages', [
    'ui.router',

    'ConciergeApp.pages.dashboard'
  ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($urlRouterProvider) {
    $urlRouterProvider.otherwise('/dashboard');
  }

})();
