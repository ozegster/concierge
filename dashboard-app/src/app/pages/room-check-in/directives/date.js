(function () {
    angular.module('ConciergeApp.pages.checkIn')
        .directive('dateCheck', function () {
            return {
                require: '^ngModel',
                link: function ($scope, $element, $attrs, ctrl) {
                    var validate = function (viewValue) {
                        var comparisonModel = $attrs.dateCheck;
                        var t, f;

                        if (!viewValue || !comparisonModel) {
                            // It's valid because we have nothing to compare against
                            ctrl.$setValidity('dateCheck', true);
                        }
                        if (comparisonModel) {
                            var to = comparisonModel.split(".");
                            t = new Date(to[2], to[1] - 1, to[0]);
                        }
                        if (viewValue) {
                            var from = viewValue.split(".");
                            f = new Date(from[2], from[1] - 1, from[0]);
                        }

                        ctrl.$setValidity('dateCheck', Date.parse(t) > Date.parse(f));
                        // console.log(Date.parse(t));
                        // if ($attrs.dateCheck > viewValue) {
                        //     ctrl.$setValidity('dateCheck', false);
                        // }
                        // It's valid if model is lower than the model we're comparing against

                        return viewValue;
                    };
                    //ctrl.$parsers.unshift(validate);
                    ctrl.$formatters.push(validate);
                    // console.debug($attrs.ngModel.$viewValue);
                    //     ctrl.$setValidity('dateCheck', false);
                }
            };
        })
})();
