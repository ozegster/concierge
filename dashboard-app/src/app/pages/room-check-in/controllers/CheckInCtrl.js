(function () {
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .controller('CheckInCtrl', CheckInCtrl);

    CheckInCtrl.$inject = ['$scope', 'toastr', 'CheckInService', '$uibModal'];
    function CheckInCtrl($scope, toastr, CheckInService, $uibModal) {

        $scope.checkIn = {};

        $scope.getAvailableRooms = function (checkIn) {
            CheckInService.getAvailableRooms(checkIn).then(function (response) {
                $scope.availableRooms = response.data;
                $scope.rowCollection = response.data;
            })
        };

        $scope.submit = function (roomCheckIn) {
            console.log($scope.checkIn);
            if (roomCheckIn.$invalid) {
                return;
            }
            CheckInService.saveCheckIn($scope.checkIn).then(function (response) {

                if (response.status === 200) {
                    // toastr.success(response.data.name + ' has successfully saved', 'Save check in');
                    $scope.message = response.data.name + ' has successfully saved, password:';
                    $scope.bigMessage = response.data.password;
                    $uibModal.open({
                        templateUrl: 'app/theme/components/modal/successModal.html',
                        controller: 'CheckInCtrl',
                        scope: $scope
                    });
                    // roomCheckIn.$setPristine();
                    // roomCheckIn.$setUntouched();
                    // $scope.checkIn = {};
                } else {
                    toastr.error(response.data.error, 'Error');
                }
            }, function (error) {
                console.log(error);
            });
        };
        $scope.openaaaa = function (page, size) {
            $uibModal.open({
                animation: true,
                templateUrl: page,
                size: size,
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });
        };
    }
})();
