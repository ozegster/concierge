(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .factory('HotelService', ['$http', 'serverPath', '$q', function ($http, serverPath, $q) {

            // getting all countries from db
            var getCountries = function () {
                return $http.get(serverPath.api + '/countries')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            var saveHotel = function (hotel) {
                return $http({
                    method: 'POST',
                    url: serverPath.api + '/hotel',
                    data: hotel
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error.message;
                })
            };

            var getHotel = function () {
                var deferred = $q.defer();
                $http.get(serverPath.api + '/hotel')
                    .then(function (response) {
                        if (!response.data) {
                            deferred.reject("There isn't a hotel in db");
                        } else {
                            deferred.resolve(response);
                        }
                    }, function (error) {
                        deferred.reject(error);
                    });
                return deferred.promise;
            };
            return {
                getCountries: getCountries,
                getHotel: getHotel,
                saveHotel: saveHotel

            };

        }])
})();
