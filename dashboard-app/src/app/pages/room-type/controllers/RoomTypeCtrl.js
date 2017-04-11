(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);


    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', '$scope'];
    function RoomTypeCtrl(BedTypeService, FeatureService, $scope) {

        $scope.roomType = {};
        $scope.selected = [];

        BedTypeService.getBedTypes().then(function (response) {
            $scope.beds = response.data;
        }, function (error) {
            console.log(error);
        });

        FeatureService.getFeatures().then(function (response) {
            $scope.features = response.data;

        }, function (error) {
            console.log(error);
        });

       $scope.toggleSelection = function (feature) {

         var index = $scope.selected.indexOf(feature);
         if (index === -1) {
            $scope.selected.push(feature);

         } else {
            $scope.selected.splice(index,1);
         }

         $scope.roomType.list = $scope.selected;

                   }
    }
})();
