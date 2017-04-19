(function () {
    'use strict';

    angular.module('ConciergeApp.pages', [
        'ui.router',

        'ConciergeApp.pages.dashboard',
        'ConciergeApp.pages.hotelInfo',
        'ConciergeApp.pages.facility',
        'ConciergeApp.pages.roomType'

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/dashboard');

       }

})();
