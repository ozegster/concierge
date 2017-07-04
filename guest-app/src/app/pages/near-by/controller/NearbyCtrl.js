(function () {
    'use strict';

    angular.module('GuestApp.pages.nearby').controller('NearbyCtrl', NearbyCtrl);
    NearbyCtrl.$inject = ['$scope', '$window', '$uibModal', 'toastr', 'NearbyService'];

    function NearbyCtrl($scope, $window, $uibModal, toastr, NearbyService) {

        $scope.keyword = "";
        $scope.radius = "";
        $scope.places = [];

        $scope.getPlaces = function (form) {

            if(form.$invalid){
                return;
            }
            NearbyService.getPlaces($scope.keyword, $scope.radius).then(function (response) {
                $scope.places = response.data.results;
                if($scope.places.length === 0){
                    toastr.info('No Results Found',{
                        timeOut: 4000,
                        closeButton: true
                    });
                }
            }, function (error) {
                console.log('error');
            });
        };

        $scope.setMap = function (lat, lng, name) {
            var myLatLng = {lat: 44.7273374, lng: 18.0773216};
            var placeName = 'Hotel Integra';

            if (lat && lng && name) {
                myLatLng.lat = lat;
                myLatLng.lng = lng;
                placeName = name;
            }

            var mapProp = {
                center: myLatLng,
                zoom: 15,
            };

            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
            $scope.customizeMarker(myLatLng, map, placeName);
        },

            $scope.customizeMarker = function (myLatLng, map, placeName) {

                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    title: placeName
                });

                $window.scrollTo(0, 0)
            };

        $scope.setMap();

        $scope.getImage = function (photo) {

            if (!photo) {
                $scope.openImageInModal(photo);
            } else {
                NearbyService.getImage(photo).then(function (response) {
                    $scope.openImageInModal(response.data);
                }, function (error) {
                    console.log(error)
                })
            }
        };

        $scope.openImageInModal = function (photo) {

            if (!photo) {
                $scope.placesImage = 'assets/img/noimage.jpg';
            }else{
                $scope.placesImage = photo;
            }

            $uibModal.open({
                templateUrl: 'app/pages/near-by/view/image-modal.html',
                controller: 'NearbyCtrl',
                scope: $scope
            })

        }
    }
})();
