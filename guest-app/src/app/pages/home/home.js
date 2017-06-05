(function () {
    'use strict';

    angular.module('GuestApp.pages.home', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'app/pages/home/view/home.html',
                title: 'Home',
                controller:'HomeCtrl'
            })
    }
})();
