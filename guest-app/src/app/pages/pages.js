(function () {
    'use strict';

    angular.module('GuestApp.pages', [
        'ui.router',

        'GuestApp.pages.home',
        'GuestApp.pages.directions',
        'GuestApp.pages.directory',
        'GuestApp.pages.facilities',
        'GuestApp.pages.guestComments',
        'GuestApp.pages.hotelInfo',
        'GuestApp.pages.nearBy',
        'GuestApp.pages.reception',
        'GuestApp.pages.roomInfo',
        'GuestApp.pages.weather',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');

    }
})();
