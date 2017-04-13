(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .controller('FacilityCrtl', FacilityCrtl);

    FacilityCrtl.$inject = ['FacilityService', '$scope'];
    function FacilityCrtl(FacilityService, $scope) {
        $scope.facility = {};
        $scope.floors = [-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

        FacilityService.getFacilityType().then(function (response) {
            $scope.facilityTypes = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (facility) {
            if (facility.$invalid) {
                return;
            }
            FacilityService.saveFacility(facility).then(function (response) {
                if (response.status === 200) {
                    $scope.facility = {};
                }
            }, function (error) {
                console.log(error);
            });
        };

        $scope.uploadPicture = function () {
            var fileInput = document.getElementById('uploadFile');
            fileInput.click();
        };
    }
})();
