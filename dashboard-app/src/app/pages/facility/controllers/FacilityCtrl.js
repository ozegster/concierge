(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .controller('FacilityCtrl', FacilityCtrl);

    FacilityCtrl.$inject = ['FacilityService', '$scope', 'toastr', '$uibModal', '$state', '$window', '$uibModalStack'];
    function FacilityCtrl(FacilityService, $scope, toastr, $uibModal, $state, $window, $uibModalStack) {
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

        FacilityService.getAllFacility().then(function (response) {
            $scope.listOfFacility = response.data;
            $scope.rowCollection = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.toggleSelection = function (feature) {
            var isAlreadyChecked = false;
            var removeIndex = 0;

            angular.forEach($scope.selectedFeatures, function (value, index) {
                if (value.id === feature.id) {
                    isAlreadyChecked = true;
                    removeIndex = index;
                }
            });

            if (isAlreadyChecked) {
                $scope.selectedFeatures.splice(removeIndex, 1);
            } else {
                $scope.selectedFeatures.push(feature);
                $scope.checkbox[feature.id] = true;
            }
            $scope.roomType.features = $scope.selectedFeatures;
        };

        $scope.uncheckFeatures = function () {

            angular.forEach($scope.checkbox, function (value, index) {
                $scope.checkbox[index] = false;
            })
        };

        $scope.checkFeatures = function (selectedFacility) {

            angular.forEach(selectedFacility.features, function (value, index) {
                var currentIndex = selectedFacility.features[index].id;
                $scope.checkbox[currentIndex] = true;
                $scope.selectedFeatures.push(selectedFacility.features[index]);
            });
        };

        $scope.openEditableFacilityForm = function () {
            var selectedFacility = JSON.parse($window.localStorage.getItem('editableFacility'));
            $scope.facility = selectedFacility;
            $scope.facilityType = selectedFacility;
            $scope.loadImage(selectedFacility.image);
        };

        $scope.handleAddFacilityButton = function () {
            $state.go('facility');
        };

        $scope.handleEditButton = function (selectedFacility) {
            $window.localStorage.setItem('editableFacility', JSON.stringify(selectedFacility));
            $state.go('facility');
        };

        $scope.closeFacilityModal = function () {
            $uibModalStack.dismissAll();
        };

        $scope.handleDeleteButton = function (selectedFacility) {
            $scope.selectedFacility = selectedFacility;
            $uibModal.open({
                templateUrl: 'app/pages/facility/views/facility-modal.html',
                controller: 'FacilityCtrl',
                scope: $scope
            });
        };

        $scope.deleteFacility = function (selectedFacility) {

            FacilityService.deleteFacility(selectedFacility.id).then(function (response) {

                if (response.status === 200) {
                    toastr.success(selectedFacility.name + ' has been deleted successfully', 'Delete Facility');

                    $scope.closeFacilityModal();
                    $state.reload();
                } else if (response.status === 500) {
                    toastr.error('Facility ' + selectedFacility.name + ' can not be deleted, it is already in use', 'Delete Facility');
                } else {
                    toastr.error(selectedFacility.name + ' has not been deleted successfully', 'Delete Facility');
                }
            }, function (response) {
                console.log(response)
            });

            $scope.closeFacilityModal();
        };

        $scope.loadImage = function (image) {
            FacilityService.getImage(image).then(function (data) {
                var panelImage = angular.element(document.querySelector('#facility-image'));
                panelImage.attr('src', 'data:image/jpeg;base64,' + data);
                var base64Image = 'data:image/jpeg;base64,' + data;
                $scope.getFileFromImage(base64Image)
            });
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

        $scope.getByteFromBase64 = function (dataURI) {
            var byteString;

            if (dataURI.split(',')[0].indexOf('base64') >= 0) {
                byteString = atob(dataURI.split(',')[1]);
            } else {
                byteString = unescape(dataURI.split(',')[1]);
            }
            var bytes = new Uint8Array(new ArrayBuffer(byteString.length));

            for (var i = 0; i < byteString.length; i++) {
                bytes[i] = byteString.charCodeAt(i);
            }
            return bytes;
        };


        $scope.$on("$stateChangeSuccess", function () {
            if ($state.is('facility') && $window.localStorage.length) {
                $scope.openEditableFacilityForm();
            } else {
                $window.localStorage.clear();
            }
        });

    }
})();
