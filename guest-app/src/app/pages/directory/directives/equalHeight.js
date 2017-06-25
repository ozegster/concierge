(function () {
    'use strict';
    angular.module('GuestApp.pages.directory')
        .directive("equalsHeight",['$timeout', function ($timeout) {
            return {
                restrict: 'A',
                link: function (scope, elem, attr, ctrl) {
                    $timeout(function () {
                        var height = angular.element(document.querySelector('.equals-height-measure'));
                        elem.css('height', height[0].clientHeight + 'px')
                    },0);
                }
            }
        }])
})();