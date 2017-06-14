(function () {
    'use strict';

    angular.module('GuestApp.pages.hotelInfo', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('hotelInfo', {
                url: '/hotelInfo',
                templateUrl: 'app/pages/hotel-info/view/hotel-info.html'
            })
    }
})();

