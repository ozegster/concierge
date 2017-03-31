(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .factory('hotelService', ['$http', 'serverPath', function ($http, serverPath) {

            // getting all countries from db
            var getCountries = function () {
                return $http.get('http://localhost:8080/countries')
                    .then(function (response) {
                        return response.data;
                    }, function (error) {
                        return error.message;
                    });
            };
            var saveHotel = function (hotel) {
                return $http({
                    method: 'POST',
                    url: 'http://localhost:8080/hotel/save',
                    data: hotel
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error.message;
                })
            };

            return {
                countries: getCountries(),
                saveHotel: saveHotel
            };

        }])
})();
