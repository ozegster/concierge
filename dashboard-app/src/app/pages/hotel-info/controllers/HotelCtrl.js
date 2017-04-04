(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .controller('HotelCtrl', HotelCtrl);

    HotelCtrl.$inject = ['HotelService', '$scope'];
    function HotelCtrl(HotelService, $scope) {
        $scope.hotel = {};

        // getting countries from database to show in select box
        HotelService.getCountries().then(function (response) {
            $scope.countries = response.data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });

        HotelService.getHotel().then(function (response) {
            $scope.hotel = response.data;
        }, function () {
            $scope.hotel = {};
        });

        $scope.submit = function (hotelForm) {

            if (hotelForm.$invalid) {
                return;
            }
            HotelService.saveHotel($scope.hotel).then(function (response) {
                $scope.hotel = response.data;
            });

        }


    }
})();
