(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
            .run(themeRun);

    /** @ngInject */
    function themeRun($timeout, $rootScope, layoutPaths, sidebarService, themeLayoutSettings) {
        var theme = themeLayoutSettings;
        if (theme.blur) {
            if (theme.mobile) {
                preloader.loadImg(layoutPaths.images.root + 'blur-bg-mobile.jpg');
            } else {
                preloader.loadImg(layoutPaths.images.root + 'blur-bg.jpg');
                preloader.loadImg(layoutPaths.images.root + 'blur-bg-blurred.jpg');
            }
        }

        $timeout(function () {
            if (!$rootScope.$pageFinishedLoading) {
                $rootScope.$pageFinishedLoading = true;
            }
        }, 7000);

        $rootScope.$sidebarService = sidebarService;
    }

})();