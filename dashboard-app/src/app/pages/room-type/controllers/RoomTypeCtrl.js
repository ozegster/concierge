(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);

    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', 'RoomTypeService', '$scope', 'toastr', '$uibModal', '$state', '$window']
    function RoomTypeCtrl(BedTypeService, FeatureService, RoomTypeService, $scope, toastr, $uibModal, $state, $window) {

        $scope.roomType = {};
        $scope.selectedFeatures = [];
        $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();
        $scope.checkbox= [];

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
            console.log($scope.roomType.features)
            RoomTypeService.saveRoomType($scope.roomType, $scope.croppedImg).then(function (response) {
            console.log(response)
                if (response.status === 200 && response.data) {
                    toastr.success(response.data.name + ' has been saved successfully', 'Save Room type');
                    roomTypeForm.$setPristine();
                    roomTypeForm.$setUntouched();
                    $scope.roomType = {};
                    $scope.uncheckFeatures();
                    $scope.selectedFeatures = [];
                    angular.element("input[type='file']").val(null);
                    $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();

                } else {
                    toastr.error(response.data.name + ' has not been saved successfully', 'Save Room type');
                }
            }, function (error) {
                console.log(error);
            })
        }

        $scope.openModal = function () {
            $uibModal.open({
                templateUrl: 'app/theme/components/crop-image/crop-upload-image.html',
                controller: 'ModalCtrl',
                scope:$scope
            })
        };

        $scope.isImageMissing = function () {
            var image = angular.element('#room-type-image').attr('src');
            return image === $scope.imageSrc;
        };

        $scope.updateTableAfterDelete = function (deletedRoomType) {
            var index = $scope.listOfRoomType.indexOf(deletedRoomType);

            if (index > -1) {
                $scope.listOfRoomType.splice(index,1);
            }
        };

        $scope.uncheckFeatures = function () {

            angular.forEach($scope.checkbox, function (value, index) {
                $scope.checkbox[index] = false;
            })
        };

        $scope.checkFeatures = function (selectedRoomType) {

            angular.forEach(selectedRoomType.features, function (value,index) {
                var currentIndex = selectedRoomType.features[index].id;
                $scope.checkbox[currentIndex] = true;
                $scope.selectedFeatures.push(selectedRoomType.features[index]);
            })
        };

        RoomTypeService.getAllRoomTypes().then(function (response) {
            $scope.listOfRoomType = response.data;
            $scope.rowCollection = response.data;
        },  function (error) {
               console.log(error);
        });

        $scope.handleEditButton = function (selectedRoomType) {
            $window.localStorage.setItem('editableRoomType',JSON.stringify(selectedRoomType));
            $state.go('room.roomType');
        };

        $scope.handleAddRoomTypeButton = function () {
            $state.go('room.roomType');
        };

        $scope.deleteRoomType = function (selectedRoomType) {
            RoomTypeService.deleteRoomType(selectedRoomType).then(function (response) {
            if (response.status === 200 && response.data) {
                toastr.success(response.data.name + ' has been deleted successfully', 'Delete Room type');
            }
            $scope.updateTableAfterDelete(selectedRoomType);
            },function (response) {
              console.log(response)
            })
        };

        $scope.getFileFromImage = function (img) {
            var byteArray = $scope.getByteFromBase64(img);
            var fileImg = new File([byteArray],'name.png');
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

        $scope.$on("$stateChangeSuccess",function(){
            if ($state.is('room.roomType') && $window.localStorage.length ) {
                $scope.openEditableRoomTypeForm();
            } else {
                $window.localStorage.clear();
            }
        })
    }
})();
