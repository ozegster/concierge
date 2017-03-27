(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelinfo', ['ui.select', 'ngSanitize'])
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
                    "hotelinfo@ConciergeApp": {
                        templateUrl: 'app/pages/hotel-info/views/hotel-info.html',
                        controller: 'HotelInfoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })
    }

})();
