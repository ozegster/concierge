(function () {
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .controller('CheckInCtrl', CheckInCtrl);

    CheckInCtrl.$inject = ['$scope', 'toastr', 'CheckInService'];
    function CheckInCtrl($scope, toastr, CheckInService) {

        $scope.roomCheckIn = {};
        $scope.open = open;
        $scope.opened = false;
        $scope.formats = 'dd.MM.yyyy';
        $scope.options = {
            showWeeks: false
        };

        function open() {
            console.log($scope.opened);
            $scope.opened = true;
        }

        $scope.getAvailableRooms = function (checkIn) {
            var checkInRequest = {
                numberOfKids: 3,
                numberOfAdults: 3
            };
            CheckInService.getAvailableRooms(checkIn).then(function (response) {
                $scope.availableRooms = response.data;
            })
        }
    }
})();
