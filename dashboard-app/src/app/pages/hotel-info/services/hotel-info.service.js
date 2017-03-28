(function () {
    'use strict';
    angular.module('ConciergeApp.pages.hotelinfo')
        .factory('HotelInfoService', ['$http', function ($http) {
            return {
                getCountries: (function () {
                    return $http.get('http://localhost:8080/countries')
                        .then(function (response) {
                            return response.data;
                        }, function (error) {
                            return error.message;
                        });
                })
            };

        }])
})();
