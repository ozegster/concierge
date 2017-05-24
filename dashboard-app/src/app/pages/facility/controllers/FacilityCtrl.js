(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .controller('FacilityCtrl', FacilityCtrl);

    FacilityCtrl.$inject = ['FacilityService', '$scope', 'toastr', '$uibModal'];
    function FacilityCtrl(FacilityService, $scope, toastr, $uibModal) {
        $scope.facility = {};
        $scope.floors = [-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
        $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();

        FacilityService.getFacilityType().then(function (response) {
            $scope.facilityTypes = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (facility) {
            if (facility.$invalid) {
                return;
            }
            FacilityService.saveFacility($scope.facility, $scope.croppedImg).then(function (response) {

                if (response.status === 200) {
                    toastr.success(response.data.facilityName + ' has successfully saved', 'Save Facility');
                    facility.$setPristine();
                    facility.$setUntouched();
                    $scope.facility = {};
                    $scope.featureBox = false;
                    angular.element("input[type='file']").val(null);
                    $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();
                } else {
                    if (response.data.errors) {
                        angular.forEach(response.data.errors, function (value, key) {
                            toastr.error(response.data.errors[key].defaultMessage, 'Error');
                        });
                    } else {
                        toastr.error(response.data.error, 'Error');
                    }

                }
            }, function (error) {
                console.log(error);
            });
        };

        $scope.openImageCrop = function () {
            $scope.element = '#facility-image';
            $uibModal.open({
                templateUrl: 'app/theme/components/crop-image/crop-upload-image.html',
                controller: 'ModalCtrl',
                scope: $scope
            })
        };

        $scope.isImageMissing = function () {
            var image = angular.element('#facility-image').attr('src');
            return image === $scope.imageSrc;
        };

        $scope.getFileFromImage = function (img) {
            var byteArray = $scope.getByteFromBase64(img);
            var fileImg = new File([byteArray], 'name.png');
            var reader = new FileReader();
            $scope.croppedImg = fileImg;

            if ($scope.croppedImg) {
                reader.readAsDataURL($scope.croppedImg);
            }
        };
    }
})();
