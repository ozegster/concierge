(function () {
    'use strict';

    angular.module('GuestApp.pages.roomInfo').controller('RoomInfoCtrl', RoomInfoCtrl);

    RoomInfoCtrl.$inject = ['$scope','RoomInfoService'];
    function RoomInfoCtrl($scope, RoomInfoService) {

        $scope.room = {};
        $scope.air = false;

        RoomInfoService.getRoom(1).then(function (response) {
            if(response.data) {
                $scope.room = response.data;
            } else {
                console.log('Room not found')
            }
        }, function () {
            $scope.room.number = 'Not available';
        });
    }
})();