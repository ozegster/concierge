(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomCtrl', RoomCtrl);

    RoomCtrl.$inject = ['RoomTypeService', 'RoomService', '$scope', 'toastr'];
    function RoomCtrl(RoomTypeService, RoomService, $scope, toastr) {

        $scope.room = {};

        RoomTypeService.getRoomTypes().then(function (response) {
            $scope.roomTypes = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.submit = function () {
            console.log($scope.room);
            RoomService.saveRoom($scope.room).then(function (response) {
                console.log(response)
            }, function (error) {
                console.log(error);
            })
        }

        $scope.changeRoomType = function (roomType) {
            $scope.selectedRoomType = roomType;
            RoomTypeService.getImage(roomType.image).then(function (data) {
                var canvas = document.getElementById("room-type-image");
                var context = canvas.getContext('2d');
                var img = new Image();
                img.onload = function () {
                    context.drawImage(this, 0, 0, canvas.width, canvas.height);
                }
                img.src = 'data:image/jpeg;base64,' + data;
            });
        }
    }
})();
