(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility', ['ui.select'])
        .config(routeConfig);


    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('facilityOverview', {
                url: '/facilityOverview',
                templateUrl: 'app/pages/facility/views/facility-overview.html',
                title: 'Facility',
                sidebarMeta: {
                    icon: 'fa fa fa-book',
                    order: 0
                },
                controller: 'FacilityCtrl'
            })

            .state('facility', {
                url: '/facility',
                title: 'New Facility',
                views: {
                    "": {
                        templateUrl: 'app/pages/facility/views/facility.html',
                        controller: 'FacilityCtrl'
                    }
                }
            });
    }

})();
