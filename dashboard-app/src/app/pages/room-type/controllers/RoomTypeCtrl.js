(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);

    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', '$scope'];
    function RoomTypeCtrl(BedTypeService, FeatureService, $scope) {

        BedTypeService.getBedTypes().then(function (response) {
            $scope.beds = response.data;
        }, function (error) {
            console.log(error);
        });

        FeatureService.getFeatures().then(function (response) {
            $scope.features = response.data;
            console.log($scope.features)
        }, function (error) {
            console.log(error);
        });
    }
})();
