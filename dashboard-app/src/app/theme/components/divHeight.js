(function () {
    angular.module('ConciergeApp.theme')
        .directive('divHeight', function () {
            return {
                restrict: 'A',
                link: function (scope, element) {
                    element.css('height', 270 + 'px');
                }
            };
        });
})();