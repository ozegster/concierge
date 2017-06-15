(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .factory('HotelService', ['$http', 'SERVER_PATH', '$q', function ($http, SERVER_PATH, $q) {

            // getting all countries from db
            var getCountries = function () {
                return $http.get(SERVER_PATH.url + '/countries')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            var saveHotel = function (hotel, image) {
                var byte = [];
                var fd = new FormData();

                if (typeof image == 'string') {
                    byte = getBlobFromBase64(image);
                    hotel.imageLogo = "name";
                    fd.append('image', new Blob([byte]));
                } else {
                    hotel.imageLogo = image.name;
                    fd.append('image',image);
                }

                fd.append('hotel', new Blob([JSON.stringify(hotel)], {
                    type: "application/json"
                }));

                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/hotels',
                    data: fd,
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error;
                })
            };

            function getBlobFromBase64(dataURI) {
                var byteString;

                if (dataURI.split(',')[0].indexOf('base64') >= 0) {
                    byteString = atob(dataURI.split(',')[1]);
                } else {
                    byteString = unescape(dataURI.split(',')[1]);
                }
                var bytes = new Uint8Array(new ArrayBuffer(byteString.length));

                for (var i = 0; i < byteString.length; i++) {
                    bytes[i] = byteString.charCodeAt(i);
                }
                return bytes;
            };

            var getHotel = function () {
                var deferred = $q.defer();
                $http.get(SERVER_PATH.url + '/hotels')
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
