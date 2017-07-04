(function () {
    angular.module('ConciergeApp.pages.roomType')
        .directive('existingName', ['RoomTypeService', function (RoomTypeService) {

            return {
                restrict: 'A',
                require: 'ngModel',
                link : function (scope, elem, attr, ctrl) {
                    elem.on('keyup', function () {
                        RoomTypeService.isExistingName(elem.val()).then(function (response){
                            ctrl.$setValidity('unique', !response.data);
                        }, function (response) {
                            console.log(response)
                        })
                    })
                }
            };
        }]);
})();
