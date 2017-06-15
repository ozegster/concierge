(function () {
    'use strict';

    angular.module('GuestApp.pages.facilities')
        .factory('FacilitiesService', ['$http', function ($http) {

            var getAllFacilities = function () {
                return $http({
                    method: 'GET',
                    url:location.protocol + '//' + location.hostname + ':8090' + '/facilities'
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };

            return {
                getAllFacilities : getAllFacilities
            };
        }])
})();