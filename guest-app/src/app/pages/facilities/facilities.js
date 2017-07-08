(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('facilities', {
                url: '/facilities',
                templateUrl: 'app/pages/facilities/view/facilities.html',
                title:'Facilities',
                controller:'FacilitiesCtrl'
            });

    }
})();

