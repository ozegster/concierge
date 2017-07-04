(function () {
    'use strict';

    angular.module('GuestApp.pages.roomInfo').controller('RoomInfoCtrl', RoomInfoCtrl);

    RoomInfoCtrl.$inject = ['$scope', 'RoomInfoService'];
    function RoomInfoCtrl($scope, RoomInfoService) {

        $scope.roomCheckIn = {};
        $scope.air = false;

        RoomInfoService.getRoomCheckIn(7368).then(function (response) {
            if (response.data) {
                $scope.roomCheckIn = response.data;
            } else {
                console.log('Room not found')
            }
        }, function () {
            $scope.roomCheckIn.room.number = 'Not available';
        });

    }
})();