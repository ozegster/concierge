(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .controller('HotelCtrl', HotelCtrl);

    HotelCtrl.$inject = ['HotelService', '$scope'];
    function HotelCtrl(HotelService, $scope) {
        $scope.hotel = {};

        // getting countries from database to show in select box
        HotelService.countries().then(function (response) {
            $scope.countries = response.data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });

        HotelService.getHotel().then(function(response){
            $scope.hotel = response.data;
        },function (error) {
            $scope.hotel={};
        });

        $scope.submit = function () {
            HotelService.saveHotel($scope.hotel).then(function(response){
                $scope.hotel = response.data;
            });

        }
    }
})();
