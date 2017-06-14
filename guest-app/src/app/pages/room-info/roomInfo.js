(function () {
    'use strict';

    angular.module('GuestApp.pages.roomInfo', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('roomInfo', {
                url: '/roomInfo',
                templateUrl: 'app/pages/room-info/view/room-info.html'
            })
    }
})();
