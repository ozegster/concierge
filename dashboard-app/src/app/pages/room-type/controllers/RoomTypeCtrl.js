(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);

    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', 'RoomTypeService', '$scope', 'toastr', '$state']
    function RoomTypeCtrl(BedTypeService, FeatureService, RoomTypeService, $scope, toastr, $state) {

        $scope.roomType = {};
        $scope.selected = [];
        $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();
        $scope.checked= [];
        $scope.path = '';

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

        $scope.toggleSelection = function (feature) {
            var index = $scope.selected.indexOf(feature);

            if (index === -1) {
                $scope.selected.push(feature);
            } else {
                $scope.selected.splice(index, 1);
            }
            $scope.roomType.features = $scope.selected;
        }

        $scope.getFileSystem = function () {
            var fileInput = document.getElementById('upload-image');
            fileInput.click();
        }

        $scope.submit = function (roomTypeForm) {
            if (roomTypeForm.$invalid) {
                return;
            }
            RoomTypeService.saveRoomType($scope.roomType, $scope.roomType.image).then(function (response) {
                if (response.status === 200 && response.data) {
                    toastr.success(response.data.name + ' has been saved successfully', 'Save Room type');
                    roomTypeForm.$setPristine();
                    roomTypeForm.$setUntouched();
                    $scope.roomType = {};
                    $scope.uncheckFeatures();
                    angular.element("input[type='file']").val(null);
                    $scope.imageSrc = 'assets/img/placeholder.png?_ts=' + new Date().getTime();

                } else {
                    toastr.error(response.data.name + ' has not been saved successfully', 'Save Room type');
                }
            }, function (error) {
                console.log(error);
            })
        }



        $scope.editRoomType = function (selectedRoomType) {
            $state.go('room.roomType');
            $scope.roomType = selectedRoomType;

            for(var i = 0; i < selectedRoomType.features.length; i++){
                $scope.checked[selectedRoomType.features[i].id -1] = true;
            }
            var img = selectedRoomType.image;
            $scope.imageSrc = $scope.path + img;
        }

        $scope.updateTableAfterDelete = function (deletedRoomType) {
            var index = $scope.listOfRoomType.indexOf(deletedRoomType);

            if (index > -1) {
                $scope.listOfRoomType.splice(index,1);
            }
        }

        $scope.uncheckFeatures = function(){

            for(var i = 0; i < $scope.checked.length;i++){
                $scope.checked[i] = false;
            }
        }

        $scope.openForm = function(){
            $scope.roomType = {};
            $scope.uncheckFeatures();
            $state.go('room.roomType');
        }

        RoomTypeService.getDirectory().then(function (response) {
            $scope.path = response.data;
            },function (response) {
                console.log(response)
        })

        $scope.deleteRoomType = function (selectedRoomType) {
            RoomTypeService.deleteRoomType(selectedRoomType).then(function (response) {
            if (response.status === 200 && response.data) {
                toastr.success(response.data.name + ' has been deleted successfully', 'Delete Room type');
            }

            $scope.updateTableAfterDelete(selectedRoomType)
            },function (response) {
              console.log(response)
            })
        }


    }
})();
