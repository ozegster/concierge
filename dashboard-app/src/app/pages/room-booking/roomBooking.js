(function () {
    'use strict';


    angular.module('ConciergeApp.pages.roomBooking', ['ui.select'])
        .config(routeConfig);


    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('booking', {
                url: '/booking',
                title: 'Booking',
                sidebarMeta: {
                    icon: 'fa fa fa-building-o',
                    order: 6
                },
                views: {
                    "": {
                        templateUrl: 'app/pages/room-booking/views/room-booking.html',
                        controller: 'BookingCtrl'
                    }
                }
            })
    }
})();

