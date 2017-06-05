(function () {
    'use strict';

    angular.module('GuestApp.pages.home').controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['$scope','HomeService'];
    function HomeCtrl($scope, HomeService) {

        HomeService.getHotel().then(function (response) {
            $scope.hotelName = response.data.name;
        }, function () {
            $scope.hotelName = 'Not available';
        });
    }
})();
