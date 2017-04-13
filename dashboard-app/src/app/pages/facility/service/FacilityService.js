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

            var saveFacility = function (facility) {
                facility.image = "http://focusyouronlinemarketing.com/heating/wp-content/uploads/2013/12/default_image_01-1024x1024-960x720.png";
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/facilities',
                    data: facility
                }).then(function (result) {
                    return result;
                }, function (error) {
                    return error;
                })
            };

            return {
                getFacilityType: getFacilityType,
                saveFacility: saveFacility

            };

        }])
})();
