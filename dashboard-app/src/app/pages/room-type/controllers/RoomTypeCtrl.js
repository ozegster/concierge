(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .controller('RoomTypeCtrl', RoomTypeCtrl);

    RoomTypeCtrl.$inject = ['BedTypeService', 'FeatureService', 'RoomTypeService', '$scope'];
    function RoomTypeCtrl(BedTypeService, FeatureService, RoomTypeService, $scope) {

        $scope.roomType = {};
        $scope.selected = [];


        BedTypeService.getBedTypes().then(function (response) {
            $scope.beds = response.data;
        }, function (error) {
            console.log(error);
        });

        FeatureService.getFeatures().then(function (response) {
            $scope.listOfFeatures = response.data;
        }, function (error) {
            console.log(error);
        });

        $scope.toggleSelection = function (feature) {
            var index = $scope.selected.indexOf(feature);

            if (index === -1) {
                $scope.selected.push(feature);
            } else {
                $scope.selected.splice(index, 1);
            }
            $scope.roomType.features = $scope.selected;
        }

        $scope.submit = function () {
            RoomTypeService.saveRoomType($scope.roomType, $scope.roomType.image).then(function (response) {
                console.log(response);
            }, function (error) {
                console.log(error);
            })
        }

        $scope.$on('$viewContentLoaded', function () {

            var canvas = document.getElementById('canvas');
            var context=canvas.getContext("2d");
            var img = new Image();
            img.onload = function () {
                context.drawImage(img, 0, 0, img.width,img.height,0, 0, canvas.width, canvas.height);

            };
            img.src = "http://focusyouronlinemarketing.com/heating/wp-content/uploads/2013/12/default_image_01-1024x1024-960x720.png"

       });

       $scope.getImages = function () {
           var fileInput = document.getElementById('uploadFile');
               fileInput.click();
       }

    }
})();
