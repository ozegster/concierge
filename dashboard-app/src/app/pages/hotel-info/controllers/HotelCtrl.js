(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .controller('HotelCtrl', HotelCtrl);

    HotelCtrl.$inject = ['HotelService', '$scope'];
    function HotelCtrl(HotelService, $scope) {
        $scope.hotel = {};

        // getting countries from database to show in select box
        HotelService.countries.then(function (data) {
            $scope.countries = data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });


        $scope.submit = function () {
            HotelService.saveHotel($scope.hotel);
            $scope.hotel = [];
        }
    }
})();
