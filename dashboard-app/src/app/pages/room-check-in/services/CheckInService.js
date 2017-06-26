(function () {
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .factory('CheckInService', ['$http', 'SERVER_PATH', '$q', function ($http, SERVER_PATH, $q) {

            var getAvailableRooms = function (checkInRequest) {
                var deferred = $q.defer();
                $http.put(SERVER_PATH.url + '/get/available/rooms', checkInRequest)
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

            var saveCheckIn = function (checkIn) {
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/room/check-in',
                    data: checkIn
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error;
                })
            };

            return {
                getAvailableRooms: getAvailableRooms,
                saveCheckIn: saveCheckIn

            };

        }])
})();
