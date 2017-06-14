(function () {
    'use strict';

    angular.module('GuestApp.pages.hotelInfo').controller('HotelInfoCtrl', HotelInfoCtrl);

    HotelInfoCtrl.$inject = ['$scope', 'HomeService'];
    function HotelInfoCtrl($scope, HomeService) {

        $scope.hotel = {};

        HomeService.getHotel().then(function (response) {
            if (response.data) {
                $scope.hotel = response.data;
                var logo = angular.element(document.querySelector('.logo-img'));
                logo.attr('src', $scope.hotel.imageLogo);
            } else {
                console.log('Hotel not found')
            }
        }, function (error) {
            console.log(error);
        });
    };

})();
