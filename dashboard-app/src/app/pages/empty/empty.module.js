(function () {
    'use strict';

    angular.module('ConciergeApp.pages.empty', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('empty', {
                url: '/empty',
                templateUrl: 'app/pages/empty/empty.html',
                title: 'Empty',
                sidebarMeta: {
                    icon: 'ion-compose',
                    order: 250
                }
            });
    }

})();