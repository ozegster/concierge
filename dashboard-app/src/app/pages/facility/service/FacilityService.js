(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .factory('FacilityService', ['$http', 'SERVER_PATH', '$q', function ($http, SERVER_PATH, $q) {

            // getting all countries from db
            var getFacilityType = function () {
                return $http.get(SERVER_PATH.url + '/facility/type')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            var saveFacility = function (hotel) {
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/facility',
                    data: hotel
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error.message;
                })
            };

            var getFacility = function () {
                var deferred = $q.defer();
                $http.get(SERVER_PATH.url + '/facility')
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
                getFacilityType: getFacilityType,
                getFacility: getFacility,
                saveFacility: saveFacility

            };

        }])
})();