(function () {
    'use strict';

    angular.module('GuestApp.pages.home')
        .factory('HomeService', ['$http', '$q', function ($http,$q) {

            var getHotel = function () {
                var deferred = $q.defer();
                $http.get(location.protocol + '//' + location.hostname + ':8090' + '/hotels')
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

            var getLogo = function (logoName) {
                return $http({
                    method: 'GET',
                    url: location.protocol + '//' + location.hostname + ':8090' + '/hotel/imageLogo/' + logoName,
                    responseType: "arraybuffer"
                }).then(function (response) {
                    return arrayBufferToBase64(response.data);
                }, function (error) {
                    return error;
                });
            };

            function arrayBufferToBase64(buffer) {
                var binary = '';
                var bytes = new Uint8Array(buffer);
                var len = bytes.byteLength;
                for (var i = 0; i < len; i++) {
                    binary += String.fromCharCode(bytes[i]);
                }
                return window.btoa(binary);
            }

        return {
            getHotel : getHotel,
            getLogo : getLogo
        };
        }])
})();
