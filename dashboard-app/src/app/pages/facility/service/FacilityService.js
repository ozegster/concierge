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
                var byte = [];
                var fd = new FormData();

                if (typeof image == 'string') {
                    byte = getBlobFromBase64(image);
                    facility.image = 'name';
                    fd.append('image', new Blob([byte]));
                } else {
                    fd.append('image',image);
                    facility.image = image.name;
                }

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

            var getAllFacilities = function () {
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

            var isExistingName = function (name) {
                return $http({
                    method: 'GET',
                    params : { name : name},
                    url: SERVER_PATH.url + '/facility'
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };


            return {
                getFacilityType: getFacilityType,
                saveFacility: saveFacility,
                isExistingName: isExistingName,
                getAllFacilities: getAllFacilities,
                deleteFacility: deleteFacility
            };
        }
        ]);
})();
