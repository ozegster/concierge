(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities').controller('FacilitiesCtrl', FacilitiesCtrl);

    FacilitiesCtrl.$inject = ['$scope', 'FacilitiesService', '$uibModal'];
    function FacilitiesCtrl($scope, FacilitiesService, $uibModals) {

        $scope.message = '';

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

        $scope.getSelectedFacility = function (facility) {
                $scope.facility = facility;
            $uibModal.open({
                templateUrl: 'app/pages/facilities/view/facility-modal.html',
                controller: 'FacilitiesCtrl',
                scope: $scope
            });
        };
    }

})();
