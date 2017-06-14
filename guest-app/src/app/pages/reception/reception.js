(function () {
    'use strict';

    angular.module('GuestApp.pages.reception', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('reception', {
                url: '/reception',
                templateUrl: 'app/pages/reception/view/reception.html'
            })
    }
})();

