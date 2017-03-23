(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
            .config(config);

    /** @ngInject */
    function config($provide) {
        $provide.decorator('$uiViewScroll', uiViewScrollDecorator);
    }

    /** @ngInject */
    function uiViewScrollDecorator($delegate, $anchorScroll, util) {
        return function (uiViewElement) {
            if (util.hasAttr(uiViewElement, "autoscroll-body-top")) {
                $anchorScroll();
            } else {
                $delegate(uiViewElement);
            }
        };
    }
})();