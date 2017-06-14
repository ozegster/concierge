(function () {
    'use strict';

    angular.module('GuestApp.pages.home').controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['$scope','HomeService'];
    function HomeCtrl($scope, HomeService) {

        $scope.hotel = {};

        HomeService.getHotel().then(function (response) {
            if(response.data) {
                $scope.hotel = response.data;
                var logo = angular.element(document.querySelector('.logo-img'));
                logo.attr('src', $scope.hotel.imageLogo);
                $scope.hotel.name = response.data.name;
            } else {
                console.log('Hotel not found')
            }
        }, function () {
            $scope.hotel.name = 'Not available';
        });
    }
})();
