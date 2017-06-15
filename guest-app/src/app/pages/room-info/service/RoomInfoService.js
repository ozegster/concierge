(function () {
    'use strict';

    angular.module('GuestApp.pages.roomInfo')
        .factory('RoomInfoService', ['$http', '$q', function ($http, $q) {

            var getRoom = function (roomId) {
                var deferred = $q.defer();
                $http.get(location.protocol + '//' + location.hostname + ':8090' + '/rooms/' + roomId)
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
                getRoom: getRoom
            };
        }])
})();
