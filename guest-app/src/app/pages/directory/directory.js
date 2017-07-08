
(function () {
    'use strict';

    angular.module('GuestApp.pages.directory', [])
        .config(routeConfig);

    function routeConfig($stateProvider) {
        $stateProvider
            .state('directory', {
                url: '/directory',
                templateUrl: 'app/pages/directory/view/directory.html',
                controller : 'DirectoryCtrl'
            })
    }
})();