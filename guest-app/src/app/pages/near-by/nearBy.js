(function () {
    'use strict';

    angular.module('GuestApp.pages.nearby', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('nearBy', {
                url: '/nearBy',
                templateUrl: 'app/pages/near-by/view/near-by.html',
                controller: 'NearbyCtrl'
            })
    }
})();

