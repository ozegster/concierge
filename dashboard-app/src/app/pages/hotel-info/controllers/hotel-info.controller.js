(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelinfo')
        .controller('HotelInfoCtrl', HotelInfoCtrl);

    HotelInfoCtrl.$inject = ['hotelInfoService', '$scope'];
    function HotelInfoCtrl(hotelInfoService, $scope) {
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
        hotelInfoService.countries.then(function (data) {
            $scope.countries = data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });

        // TO DO
        $scope.submit = function () {
            console.log($scope.hotel);
        }
    }
})();
