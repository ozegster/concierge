(function () {
    'use strict';
    angular.module('GuestApp.theme')
        .directive("equalsHeight", function () {
            return {
                restrict: 'A',
                link: function (scope, elem, attr, ctrl) {
                    var body = angular.element(document.querySelector('body'));
                    elem.css('height', body[0].scrollHeight + 'px')
                }
            }
        })
})();