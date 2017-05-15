(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);


    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', 'RoomTypeService', '$scope', 'toastr', '$uibModal', '$state', '$window', '$uibModalStack'];
    function RoomTypeCtrl(BedTypeService, FeatureService, RoomTypeService, $scope, toastr, $uibModal, $state, $window, $uibModalStack) {

        $scope.roomType = {};
        $scope.selectedFeatures = [];
        $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();
        $scope.checkbox = [];

        BedTypeService.getBedTypes().then(function (response) {
            $scope.beds = response.data;
        }, function (error) {
            console.log(error);
        });

        FeatureService.getFeatures().then(function (response) {
            $scope.listOfFeatures = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (roomTypeForm) {
            if (roomTypeForm.$invalid || $scope.isImageMissing()) {
                return;
            }

            RoomTypeService.saveRoomType($scope.roomType, $scope.croppedImg).then(function (response) {

                if (response.status === 200 && response.data) {
                    toastr.success(response.data.name + ' has been saved successfully', 'Save Room type');
                    $scope.selectedFeatures = [];
                    $scope.uncheckFeatures();
                    $state.go('room.roomTypeOverview');

                } else {
                    toastr.error(response.data.name + ' has not been saved successfully', 'Save Room type');
                }
            }, function (error) {
                console.log(error);
            });
        };

        $scope.openModal = function () {
            $uibModal.open({
                templateUrl: 'app/theme/components/crop-image/crop-upload-image.html',
                controller: 'ModalCtrl',
                scope: $scope
            })
        };

        $scope.isImageMissing = function () {
            var image = angular.element('#room-type-image').attr('src');
            return image === $scope.imageSrc;
        };

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

        $scope.checkFeatures = function (selectedRoomType) {

            angular.forEach(selectedRoomType.features, function (value, index) {
                var currentIndex = selectedRoomType.features[index].id;
                $scope.checkbox[currentIndex] = true;
                $scope.selectedFeatures.push(selectedRoomType.features[index]);
            });
        };

        RoomTypeService.getAllRoomTypes().then(function (response) {
            $scope.listOfRoomType = response.data;
            $scope.rowCollection = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.openEditableRoomTypeForm = function () {
            var selectedRoomType = JSON.parse($window.localStorage.getItem('editableRoomType'));
            $scope.roomType = selectedRoomType;
            $scope.loadImage(selectedRoomType.image);
            $scope.checkFeatures(selectedRoomType)
        };

        $scope.handleEditButton = function (selectedRoomType) {
            $window.localStorage.setItem('editableRoomType', JSON.stringify(selectedRoomType));
            $state.go('room.roomType');
        };

        $scope.handleAddRoomTypeButton = function () {
            $state.go('room.roomType');
        };

        $scope.handleDeleteButton = function (selectedRoomType) {
            $scope.selectedRoomType = selectedRoomType;
            $uibModal.open({
                templateUrl: 'app/pages/room-type/views/room-type-modal.html',
                controller: 'RoomTypeCtrl',
                scope: $scope
            });
        };

        $scope.closeRoomTypeModal = function () {
            $uibModalStack.dismissAll();
        };

        $scope.deleteRoomType = function (selectedRoomType) {

            RoomTypeService.deleteRoomType(selectedRoomType.id).then(function (response) {

                if (response.status === 200) {
                    toastr.success(selectedRoomType.name + ' has been deleted successfully', 'Delete Room type');

                    $scope.closeRoomTypeModal();
                    $state.reload();
                } else if (response.status === 500) {
                    toastr.error('Room type ' + selectedRoomType.name + ' can not be deleted, it is already in use', 'Delete Room type');
                } else {
                    toastr.error(selectedRoomType.name + ' has not been deleted successfully', 'Delete Room type');
                }
            }, function (response) {
                console.log(response)
            });

            $scope.closeRoomTypeModal();
        };

        $scope.loadImage = function (image) {
            RoomTypeService.getImage(image).then(function (data) {
                var panelImage = angular.element(document.querySelector('#room-type-image'));
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
            if ($state.is('room.roomType') && $window.localStorage.length) {
                $scope.openEditableRoomTypeForm();
            } else {
                $window.localStorage.clear();
            }
        })
    }
})();
