(function () {
    angular.module('ConciergeApp.pages.checkIn')
        .directive('dateCheckIn', function () {
            return {
                require: '^ngModel',
                link: function ($scope, $element, $attrs, ctrl) {
                    var validate = function (viewValue) {
                        var comparisonModel = $attrs.dateCheckIn;
                        var checkInDate, checkOutDate;

                        if (comparisonModel) {
                            var to = comparisonModel.toString().split(".");
                            checkOutDate = new Date(to[2], to[1] - 1, to[0]);
                        }
                        if (viewValue) {
                            var from = viewValue.toString().split(".");
                            checkInDate = new Date(from[2], from[1] - 1, from[0]);
                        }
                        ctrl.$setValidity('dateCheckIn', checkOutDate >= checkInDate);
                        return viewValue;
                    };

                    ctrl.$parsers.unshift(validate);
                }
            };
        })
})();
