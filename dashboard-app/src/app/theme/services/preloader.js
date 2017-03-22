(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
            .service('preloader', preloader);

    /** @ngInject */
    function preloader($q) {
        return {
            loadImg: function (src) {
                var d = $q.defer();
                var img = new Image();
                img.src = src;
                img.onload = function(){
                    d.resolve();
                };
                return d.promise;
            }
        }
    }

})();