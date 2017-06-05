(function () {
    'use strict';

    angular.module('GuestApp.pages.weather', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('weather', {
                url: '/weather',
                templateUrl: 'app/pages/weather/view/weather.html'
            })
    }
})();

