(function () {
    'use strict';

    angular.module('ConciergeApp.pages.roomBooking')
        .controller('BookingCtrl', BookingCtrl);

    BookingCtrl.$inject = ['$scope', 'toastr'];
    function BookingCtrl($scope, toastr) {

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
        $scope.te = function () {
            console.log($scope.opened + " ovako");

        }

    }
})();
