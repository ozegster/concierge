(function () {
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .controller('CheckInCtrl', CheckInCtrl);

    CheckInCtrl.$inject = ['$scope', 'toastr', 'CheckInService', '$uibModal'];
    function CheckInCtrl($scope, toastr, CheckInService, $uibModal) {

        $scope.roomCheckIn = {};

        $scope.getAvailableRooms = function () {
            if ($scope.roomCheckIn.checkOut < $scope.roomCheckIn.checkIn) {
                return toastr.error('Room check-out date is less than check-in', 'Error');
            }
            CheckInService.getAvailableRooms($scope.roomCheckIn).then(function (response) {
                $scope.availableRooms = response.data;
                $scope.rowCollection = response.data;
            })
        };

        $scope.submit = function (roomCheckInForm) {
            if (roomCheckInForm.$invalid) {
                return;
            }
            if (!$scope.roomCheckIn.room) {
                return toastr.error('Select available room', 'Error');
            }
            if ($scope.roomCheckIn.checkOut < $scope.roomCheckIn.checkIn) {
                return toastr.error('Room check-out date is less than check-in', 'Error');
            }
            CheckInService.saveCheckIn($scope.roomCheckIn).then(function (response) {
                if (response.status === 200) {
                    $scope.message = response.data.name + ' has successfully saved, password:';
                    $scope.bigMessage = response.data.password;
                    $uibModal.open({
                        templateUrl: 'app/theme/components/modal/successModal.html',
                        controller: 'CheckInCtrl',
                        scope: $scope
                    });
                    roomCheckInForm.$setPristine();
                    roomCheckInForm.$setUntouched();
                    $scope.roomCheckIn = {};
                } else {
                    toastr.error(response.data.error, 'Error');
                }
            }, function (error) {
                console.log(error);
            });
        };
    }
})();
