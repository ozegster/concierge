(function () {
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .controller('DatepickerCtrl', DatepickerCtrl);

    function DatepickerCtrl($scope, $filter) {

        $scope.checkInOpen = checkInOpen;
        $scope.checkOutOpen = checkOutOpen;
        $scope.checkInOpened = false;
        $scope.checkOutOpened = false;
        $scope.format = 'dd.MM.yyyy';

        $scope.checkInOptions = {
            showWeeks: false,
            minDate: new Date()
        };
        $scope.checkOutOptions = {
            showWeeks: false,
            minDate: new Date()

        };

        function checkInOpen() {
            $scope.checkInOpened = true;
        }

        function checkOutOpen() {
            var from = $filter('date')($scope.roomCheckIn.checkIn, 'dd.MM.yyyy').toString().split(".");
            var checkInDate = new Date(from[2], from[1] - 1, from[0]);
            $scope.checkOutOpened = true;
            $scope.checkOutOptions.minDate = new Date(checkInDate.setDate(checkInDate.getDate() + 1));
        }
    }
})();
