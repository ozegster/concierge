(function () {
    'use strict';

    angular.module('GuestApp.pages.guestComments', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('guestComments', {
                url: '/guestComments',
                templateUrl: 'app/pages/guest-comments/view/guest-comments.html'
            })
    }
})();

