(function () {
    'use strict';

    angular.module('GuestApp.pages.nearby').controller('NearbyCtrl', NearbyCtrl);
    NearbyCtrl.$inject = ['$scope','$window','$uibModal', 'NearbyService'];

    function NearbyCtrl($scope,$window,$uibModal, NearbyService) {

        $scope.keyword = "";
        $scope.places = [];

        $scope.getPlaces = function () {
            NearbyService.getPlaces($scope.keyword).then(function (response) {
                $scope.places = response.data.results;
            }, function (error) {
                console.log('error');
            });
        };

        $scope.setMap = function (lat,lng,name) {
            var myLatLng = {lat: 44.7273374, lng: 18.0773216};
            var placeName = 'Hotel Integra';

            if(lat && lng && name){
                myLatLng.lat = lat;
                myLatLng.lng = lng;
                placeName = name;
            }

            var mapProp= {
                center: myLatLng,
                zoom:15,
            };

            var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
            $scope.customizeMarker(myLatLng, map, placeName);
        },

            $scope.customizeMarker = function (myLatLng, map, placeName) {

            var marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                title: placeName
            });

            $window.scrollTo(0,0)
        };

        $scope.setMap();

        $scope.getImage = function (photo) {
            NearbyService.getImage(photo).then(function(response){
               $scope.openImageInModal(response.data);
            },function(error){
                console.log(error)
            })
        }

        $scope.openImageInModal = function (photo) {
            $scope.placesImage = photo;
            $uibModal.open({
                templateUrl:'app/pages/near-by/view/image-modal.html',
                controller: 'NearbyCtrl',
                scope : $scope
            })

        }
    }
})();
