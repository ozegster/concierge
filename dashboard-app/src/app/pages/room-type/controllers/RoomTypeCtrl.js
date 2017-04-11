(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);

    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', 'RoomTypeService', '$scope'];
    function RoomTypeCtrl(BedTypeService, FeatureService, RoomTypeService, $scope) {

        $scope.roomType = {};

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

        $scope.submit = function () {
            RoomTypeService.saveRoomType($scope.roomType,$scope.image).then(function (response) {
                console.log(response);
            }, function (error) {
                console.log(error);
            })
        }
    }
})();
