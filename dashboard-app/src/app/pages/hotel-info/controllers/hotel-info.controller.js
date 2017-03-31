(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .controller('AdminCtrl', AdminCtrl);

    AdminCtrl.$inject = ['hotelService', '$scope'];
    function AdminCtrl(hotelService, $scope) {
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
        hotelService.countries.then(function (data) {
            $scope.countries = data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });

        // TO DO
        $scope.submit = function () {
            hotelService.saveHotel($scope.hotel);
            $scope.hotel = [];
        }
    }
})();
