(function () {
    'use strict';

    angular.module('GuestApp.pages.hotelInfo').controller('HotelInfoCtrl', HotelInfoCtrl);

    HotelInfoCtrl.$inject = ['$scope', 'HomeService'];
    function HotelInfoCtrl($scope, HomeService) {

        $scope.hotel = {};

        HomeService.getHotel().then(function (response) {
            if (response.data) {
                $scope.hotel = response.data;
                $scope.hotel.name = response.data.name;
            } else {
                console.log('Hotel not found')
            }
        }, function (error) {
            console.log(error);
        });
    };

})();
