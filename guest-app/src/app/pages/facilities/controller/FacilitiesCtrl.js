(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities').controller('FacilitiesCtrl', FacilitiesCtrl);

    FacilitiesCtrl.$inject = ['$scope', 'FacilitiesService'];
    function FacilitiesCtrl($scope, FacilitiesService) {

        $scope.facility = '';

        FacilitiesService.getAllFacilities().then(function (response) {
            if(response.data) {
                $scope.listOfFacilities = response.data;
                var image = angular.element(document.querySelector('.facility-image'));
            } else {
                $scope.facility = 'not available';
            }
        }, function (error) {
            console.log(error);
        });
    }
})();
