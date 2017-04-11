(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility', ['ui.select'])
        .config(routeConfig);


    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('facility', {
                url: '/facility',
                title: 'Facility',
                sidebarMeta: {
                    icon: 'fa fa fa-book',
                    order: 5
                },
                views: {
                    "": {
                        templateUrl: 'app/pages/facility/views/facility.html',
                        controller: 'FacilityCrtl'
                    }
                }
            })
    }

})();
