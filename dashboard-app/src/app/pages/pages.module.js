(function () {
  'use strict';

  angular.module('ConciergeApp.pages', [
    'ui.router',

    'ConciergeApp.pages.dashboard',
    'ConciergeApp.pages.empty'
  ])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($urlRouterProvider) {
    $urlRouterProvider.otherwise('/dashboard');
  }

})();
