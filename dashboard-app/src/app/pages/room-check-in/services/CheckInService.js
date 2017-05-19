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

            return {
                getAvailableRooms: getAvailableRooms

            };

        }])
})();
