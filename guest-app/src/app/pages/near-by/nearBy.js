(function () {
    'use strict';

    angular.module('GuestApp.pages.nearBy', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('nearBy', {
                url: '/nearBy',
                templateUrl: 'app/pages/near-by/view/near-by.html'
            })
    }
})();

