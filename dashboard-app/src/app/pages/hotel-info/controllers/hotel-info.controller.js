(function () {
    angular.module('ConciergeApp.pages.hotelinfo')
        .controller('HotelInfoCtrl', ['HotelInfoService','$scope', function (HotelInfoService,$scope) {
            $scope.hotel = {
                name: '',
                rating: '',
                address: '',
                zip: '',
                city: '',
                country: {},
                phone: '',
                fax: '',
                email: '',
                website: '',
                description: ''
            }

            // getting countries from database to show in select box
            HotelInfoService.getCountries().then(function (data) {
                $scope.countries = data;
            }, function (errorMessage) {
                console.log(errorMessage);
            });

            // TO DO
            $scope.submit = function () {
                console.log($scope.hotel);
            }
        }])
})();
