(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .factory('FacilityService', ['$http', 'SERVER_PATH', '$q', function ($http, SERVER_PATH, $q) {

            var getFacilityType = function () {
                return $http.get(SERVER_PATH.url + '/facilities/types')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            var saveFacility = function (facility, image) {
                console.log(SERVER_PATH);
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
                })
            };

            var isExistingName = function (name) {
                return $http({
                    method: 'GET',
                    params : { name : name},
                    url: SERVER_PATH.url + '/facilities'
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };


            return {
                getFacilityType: getFacilityType,
                saveFacility: saveFacility,
                isExistingName: isExistingName

            };

        }])
})();
