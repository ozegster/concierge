(function () {
    angular.module('ConciergeApp.pages.roomType')
        .directive('existingName', ['RoomTypeService', function (RoomTypeService) {

            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, element, attrs, ngModel) {
                    scope.$watch(attrs.ngModel, function (value) {
                        if (value) {
                            RoomTypeService.isExistingName(value)
                                .then(function (data) {
                                    if (data === true) {
                                        ngModel.$setValidity('unique', false);
                                    } else {
                                        ngModel.$setValidity('unique', true);
                                    }
                                });
                        } else {
                            ngModel.$setValidity('unique', false);
                        }
                    });
                }
            };
        }]);
})();
