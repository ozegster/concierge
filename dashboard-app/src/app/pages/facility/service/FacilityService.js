(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .factory('FacilityService', ['$http', 'SERVER_PATH', '$q', function ($http, SERVER_PATH) {

            var getFacilityType = function () {
                return $http.get(SERVER_PATH.url + '/facilities/types')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            var saveFacility = function (facility, image) {
                if (image) {
                    facility.image = image.name;
                } else {
                    facility.image = '';
                }
                var fd = new FormData();
                fd.append('facility', new Blob([JSON.stringify(facility)], {
                    type: "application/json"
                }));
                fd.append('image', image);
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/facilities',
                    data: fd,
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error;
                });
            };

            var getImage = function (imageName) {
                return $http({
                    method: 'GET',
                    url: SERVER_PATH.url + '/facility/image/' + imageName,
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

            var getAllFacility = function () {
                return $http({
                    method: 'GET',
                    url: SERVER_PATH.url + '/facilities'
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };

            var deleteFacility = function (selectedFacilityId) {
                return $http({
                    method: 'DELETE',
                    url: SERVER_PATH.url + '/facilities/' + selectedFacilityId
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };

            return {
                getFacilityType: getFacilityType,
                saveFacility: saveFacility,
                getAllFacility: getAllFacility,
                deleteFacility: deleteFacility,
                getImage: getImage
            };

        }
        ]);
})();
