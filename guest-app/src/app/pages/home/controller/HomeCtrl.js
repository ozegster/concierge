(function () {
    'use strict';

    angular.module('GuestApp.pages.home').controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['$scope','HomeService'];
    function HomeCtrl($scope, HomeService) {

        $scope.hotel = {};

        HomeService.getHotel().then(function (response) {
            if(response.data) {
                $scope.hotel = response.data;
                $scope.getImage($scope.hotel.imageLogo);
                $scope.hotel.name = response.data.name;
            } else {
                console.log('Hotel not found')
            }
        }, function () {
            $scope.hotel.name = 'Not available';
        });

        $scope.getImage = function (logoName) {
            HomeService.getLogo(logoName).then(function (data) {
                var logo = angular.element(document.querySelector('.logo-img'));
                logo.attr('src', 'data:image/jpeg;base64,' + data);
            }, function (error) {
                console.log(error);
            });
        }
    }
})();
