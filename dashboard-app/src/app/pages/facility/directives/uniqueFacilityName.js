(function () {
    angular.module('ConciergeApp.pages.facility')
        .directive('uniqueName',['FacilityService', function (FacilityService) {
            return {
                require: '^ngModel',
                restrict: 'A',
                link : function (scope, elem, attr, ctrl) {
                    elem.on('keyup', function () {
                        FacilityService.isExistingName(elem.val()).then(function (response){
                            ctrl.$setValidity('uniqueName', !response.data);
                        }, function (response) {
                            console.log(response)
                        })
                    })
                }
            }
        }])
})();
