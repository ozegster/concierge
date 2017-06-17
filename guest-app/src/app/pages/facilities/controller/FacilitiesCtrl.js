(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities').controller('FacilitiesCtrl', FacilitiesCtrl);

    FacilitiesCtrl.$inject = ['$scope', 'FacilitiesService'];
    function FacilitiesCtrl($scope, FacilitiesService) {

        $scope.message = '';

        FacilitiesService.getAllFacilities().then(function (response) {
            if(response.data) {
                $scope.listOfFacilities = response.data;
                $scope.rowCollection = response.data;
                var image = angular.element(document.querySelector('.facility-image'));
            } else {
                $scope.message = 'not available';
            }
        }, function (error) {
            console.log(error);
        });
    }
})();
