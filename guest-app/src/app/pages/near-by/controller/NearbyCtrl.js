(function () {
    'use strict';

    angular.module('GuestApp.pages.nearby').controller('NearbyCtrl', NearbyCtrl);
    NearbyCtrl.$inject = ['$scope','$window', 'NearbyService'];

    function NearbyCtrl($scope,$window, NearbyService) {

        $scope.keyword = "";
        $scope.places = [];
//$scope.image = "";
        $scope.getPlaces = function () {
            NearbyService.getPlaces($scope.keyword).then(function (response) {
                $scope.places = response.data.results;
console.log($scope.places)
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
                response.data;
                var panelImage = angular.element(document.querySelector('#img'));
                panelImage.attr('src',response.data);
            },function(error){
                console.log(error)
            })
        }
    }
})();
