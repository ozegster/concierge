(function () {
    'use strict';
    angular.module('GuestApp.pages.directory')
        .factory('DirectoryService', ['$http', function ($http) {

            var getRooms = function () {
                return $http({
                    method : 'GET',
                    url : location.protocol + '//' + location.hostname + ':8090' + '/rooms'
                }).then(function (response) {
                    return response.data;
                }, function (response) {
                    return response
                })
            };

            var getFacilities = function () {
                return $http({
                    method : 'GET',
                    url : location.protocol + '//' + location.hostname + ':8090' + '/facilities'
                }).then(function (response){
                    return response.data;
                }, function (response) {
                    return response;
                })
            };

            return {
                getRooms : getRooms,
                getFacilities : getFacilities
            }
        }])
})();
