(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities').controller('FacilitiesCtrl', FacilitiesCtrl);

    FacilitiesCtrl.$inject = ['$scope', 'FacilitiesService'];
    function FacilitiesCtrl($scope, FacilitiesService) {


        FacilitiesService.getAllFacilities().then(function (response) {
            if(response.data) {
                $scope.listOfFacilities = response.data;
                var image = angular.element(document.querySelector('.facility-image'));
                image.attr('src', $scope.listOfFacilities.image);
            } else {
                console.log('Facilities not found')
            }
        }, function (error) {
            console.log(error);
        });

    }
})();
