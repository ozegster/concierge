(function () {
    'use strict';

    angular.module('GuestApp.pages.hotelInfo').controller('HotelInfoCtrl', HotelInfoCtrl);

    HotelInfoCtrl.$inject = ['$scope', 'HomeService'];
    function HotelInfoCtrl($scope, HomeService) {

        $scope.hotel = {};
        $scope.range = {};
        HomeService.getHotel().then(function (response) {
            if (response.data) {
                $scope.hotel = response.data;
                $scope.range = new Array($scope.hotel.rating);
                var logo = angular.element(document.querySelector('.hotel-logo'));
                logo.attr('src', $scope.hotel.imageLogo);
            } else {
                console.log('Hotel not found')
            }
        }, function (error) {
            console.log(error);
        });
    };

})();
