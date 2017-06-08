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
                if (image) {
                    hotel.imageLogo = image.name;
                } else {
                    hotel.imageLogo = '';
                }
                var fd = new FormData();
                fd.append('hotel', new Blob([JSON.stringify(hotel)], {
                    type: "application/json"
                }));
                fd.append('image', image);

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

            var getImageLogo = function (imageName) {
                return $http({
                    method: 'GET',
                    url: SERVER_PATH.url + '/hotel/imageLogo/' + imageName,
                    responseType: "arraybuffer"
                }).then(function (response) {
                    var base64Image = arrayBufferToBase64(response.data);

                    return base64Image;
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
                getCountries: getCountries,
                getHotel: getHotel,
                saveHotel: saveHotel,
                getImageLogo: getImageLogo

            };

        }])
})();
