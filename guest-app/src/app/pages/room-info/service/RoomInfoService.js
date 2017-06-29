(function () {
    'use strict';

    angular.module('GuestApp.pages.roomInfo')
        .factory('RoomInfoService', ['$http', '$q', function ($http, $q) {

            var getRoomCheckIn = function (password) {
                var deferred = $q.defer();
                $http.get(location.protocol + '//' + location.hostname + ':8090' + '/room/' + password)
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
                getRoomCheckIn: getRoomCheckIn
            };
        }])
})();
