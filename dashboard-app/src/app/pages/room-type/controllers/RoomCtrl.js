(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomCtrl', RoomCtrl);

    RoomCtrl.$inject = ['RoomTypeService', 'RoomService', '$scope', 'toastr', '$state', '$window'];
    function RoomCtrl(RoomTypeService, RoomService, $scope, toastr, $state, $window) {

        $scope.room = {};

        RoomTypeService.getAllRoomTypes().then(function (response) {
            $scope.roomTypes = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function (roomForm) {
            if (roomForm.$invalid) {
                return;
            }
            RoomService.saveRoom($scope.room).then(function (response) {
                if (response.status === 200) {
                    toastr.success('Room has been saved successfully', 'Save Room');
                    roomForm.$setPristine();
                    roomForm.$setUntouched();
                    $scope.room = {};
                    $scope.selectedRoomType = null;
                    $state.go('room.roomOverview');
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
            })
        };

        $scope.changeRoomType = function (roomType) {
            $scope.selectedRoomType = roomType;
            RoomTypeService.getImage(roomType.image).then(function (data) {
                var canvas = angular.element('#room-type-image-canvas')[0];
                var context = canvas.getContext('2d');
                var img = new Image();
                img.onload = function () {
                    context.drawImage(this, 0, 0, canvas.width, canvas.height);
                };
                img.src = 'data:image/jpeg;base64,' + data;
            });
        };

        RoomService.getAllRooms().then(function (response) {
            $scope.listOfRooms = response.data;
            $scope.roomCollection = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.addNewRoom = function(){
            $state.go('room.room');
        };

        $scope.editRoom = function(room){
            $window.localStorage.setItem('editableRoom', JSON.stringify(room));
            $state.go('room.room');
        };

        $scope.openEditableRoomForm = function () {
            var selectedRoom = JSON.parse($window.localStorage.getItem('editableRoom'));
            $scope.room = selectedRoom;
            $scope.changeRoomType($scope.room.roomType);
        };

        $scope.deleteRoom = function(room){

        };
        $scope.$on("$stateChangeSuccess", function () {
            if ($state.is('room.room') && $window.localStorage.getItem('editableRoom')) {
                $scope.openEditableRoomForm();
            } else {
                $window.localStorage.clear();
            }
        });
    }
})();
