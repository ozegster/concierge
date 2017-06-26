(function () {
    'use strict';


    angular.module('ConciergeApp.pages.checkIn', ['ui.select'])
        .config(routeConfig);


    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('check-in', {
                url: '/check-in',
                title: 'Check-in',
                sidebarMeta: {
                    icon: 'fa fa fa-building-o',
                    order: 6
                },
                views: {
                    "": {
                        templateUrl: 'app/pages/room-check-in/views/room-check-in.html',
                        controller: 'CheckInCtrl'
                    }
                }
            })
    }
})();

