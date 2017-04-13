(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .controller('FacilityCrtl', FacilityCrtl);

    FacilityCrtl.$inject = ['FacilityService', '$scope'];
    function FacilityCrtl(FacilityService, $scope) {
        $scope.facility = {};
        $scope.floors = [-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
        $scope.imageScr = "http://focusyouronlinemarketing.com/heating/wp-content/uploads/2013/12/default_image_01-1024x1024-960x720.png";

        FacilityService.getFacilityType().then(function (response) {
            $scope.facilityType = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (facility) {
            FacilityService.saveFacility(facility).then(function (response) {
                console.log(response);
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
