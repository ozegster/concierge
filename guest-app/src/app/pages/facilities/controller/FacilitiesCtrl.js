(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities').controller('FacilitiesCtrl', FacilitiesCtrl);

    FacilitiesCtrl.$inject = ['$scope', 'FacilitiesService', 'HomeService'];
    function FacilitiesCtrl($scope, FacilitiesService, HomeService) {

        $scope.message = '';
        $scope.selected = false;


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

        FacilitiesService.getAllFacilities().then(function (response) {
            if (response.data) {
                $scope.listOfFacilities = response.data;
                var image = angular.element(document.querySelector('.facility-image'));
            } else {
                $scope.message = 'not available';
            }
        }, function (error) {
            console.log(error);
        });

        $scope.selectedDetails = function () {
            $scope.selected = !$scope.selected;
        };

        $scope.getMoreDetails = function (facilityName) {
             $scope.selectedFacility = facilityName;
        };


    }
})();
