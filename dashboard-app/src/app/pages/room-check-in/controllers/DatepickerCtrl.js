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
            //problem je bio u liniji 33, mislim da je to upozorenje koje datapicker direktiva javlja
            //ti si radio ovo:
            //$scope.checkOutOptions.minDate = checkInDate.setDate(checkInDate.getDate() + 1);
            // kad otprintas ovo u konzoli
            //console.log(typeof  $scope.checkOutOptions.minDate)
            // rezultat je number, a ono upozorenje je trazilo da se koristi Date objekat
            //tako da sam samo odradio "wrapping", odnosno
            //strpao sam $scope.checkOutOptions.minDate unutar new Date() i dobio
            //Date objekat koji se ocekuje
        }
    }
})();
