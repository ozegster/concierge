(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo', ['ui.select', 'moment-picker'])
        .config(routeConfig);


    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('hotelinfo', {
                url: '/hotelinfo',
                title: 'Hotel info',
                sidebarMeta: {
                    icon: 'fa fa-info',
                    order: 0
                },
                views: {
                    "": {
                        templateUrl: 'app/pages/hotel-info/views/hotel-info.html',
                        controller: 'HotelCtrl'
                    }
                }
            })
    }

})();
