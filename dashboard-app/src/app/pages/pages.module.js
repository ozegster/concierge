(function () {
    'use strict';

    angular.module('ConciergeApp.pages', [
        'ui.router',

        'ConciergeApp.pages.dashboard',
        'ConciergeApp.pages.empty',
        'ConciergeApp.pages.hotelinfo'

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/dashboard');
    }

})();
