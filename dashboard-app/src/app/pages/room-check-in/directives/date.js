(function () {
    angular.module('ConciergeApp.pages.checkIn')
        .directive('dateCheck', function () {
            return {
                require: '^ngModel',
                link: function ($scope, $element, $attrs, ctrl) {
                    var validate = function (viewValue) {
                        // split the date to get yyyy-mm-dd
                        var dateEndArray = $attrs.dateCheck.split('T')[0].split('-');
                        // get the date to compare against in dd-mm-yyyy format
                        var lowerThan = dateEndArray[2]+'.'+dateEndArray[1]+'.'+dateEndArray[0].replace('"','');
                            ctrl.$setValidity('dateCheck', viewValue > lowerThan);
                        return viewValue;
                    };

                    ctrl.$parsers.unshift(validate);
                    ctrl.$formatters.push(validate);

                    $attrs.$observe('dateCheck', function(lowerThan){
                        return validate(ctrl.$viewValue);
                    });
                }
            };
        })
})();
