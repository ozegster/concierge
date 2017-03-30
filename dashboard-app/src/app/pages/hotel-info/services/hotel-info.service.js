(function () {
    'use strict';
    
    angular.module('ConciergeApp.pages.hotelInfo')
        .factory('hotelService', ['$http', function ($http) {

            // getting all countries from db
            var getCountries = function () {
                return $http.get('http://localhost:8080/countries')
                    .then(function (response) {
                        return response.data;
                    }, function (error) {
                        return error.message;
                    });
            }

            return {
                countries: getCountries()
            };

        }])
})();
