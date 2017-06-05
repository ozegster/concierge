(function () {
    'use strict';

    angular.module('GuestApp.pages.directions', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('directions', {
                url: '/directions',
                templateUrl: 'app/pages/directions/view/directions.html'
            })
    }
})();
