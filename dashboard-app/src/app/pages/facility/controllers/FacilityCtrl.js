(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .controller('FacilityCtrl', FacilityCtrl);

    FacilityCtrl.$inject = ['FacilityService', '$scope', 'toastr'];
    function FacilityCtrl(FacilityService, $scope, toastr) {
        $scope.facility = {};
        $scope.floors = [-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

        FacilityService.getFacilityType().then(function (response) {
            $scope.facilityTypes = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (facility) {
            if (facility.$invalid) {
                return;
            }
            FacilityService.saveFacility($scope.facility, $scope.facility.image).then(function (response) {

                if (response.status === 200) {
                    toastr.success(response.data.facilityName + ' has successfully saved', 'Save Facility');
                    facility.$setPristine();
                    facility.$setUntouched();
                    $scope.facility = {};
                }
            }, function (error) {
                console.log(error);
            });
        };

        $scope.uploadPicture = function () {
            var fileInput = document.getElementById('upload-image');
            fileInput.click();
        };
    }
})();
