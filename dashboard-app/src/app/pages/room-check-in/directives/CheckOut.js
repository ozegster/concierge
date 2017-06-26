(function () {
    angular.module('ConciergeApp.pages.checkIn')
        .directive('dateCheckOut', ['$filter', function ($filter) {
            return {
                require: '^ngModel',
                link: function ($scope, $element, $attrs, ctrl) {
                    var validate = function (viewValue) {
                        var comparisonModel = $attrs.dateCheckOut;
                        var checkInDate, checkOutDate;
                        ctrl.$setValidity('dateCheck', true);
                        if (comparisonModel) {
                            var to = comparisonModel.toString().split(".");
                            checkInDate = new Date(to[2], to[1] - 1, to[0]);
                        }
                        if (viewValue) {
                            var from = viewValue.toString().split(".");
                            checkOutDate = new Date(from[2], from[1] - 1, from[0]);
                        }
                        if (checkInDate >= checkOutDate) {
                            ctrl.$setValidity('dateCheck', false);
                        }

                        return viewValue;
                    };

                    ctrl.$parsers.unshift(validate);
                }
            };
        }])
})();
