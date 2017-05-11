(function () {
    'use strict';
    angular.module('ConciergeApp.pages.roomType')
        .factory('RoomService', ['$http', 'SERVER_PATH', function ($http, SERVER_PATH) {
            var saveRoom = function (room) {
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/rooms',
                    data: room
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });

            };

            return {
                saveRoom: saveRoom
            };
        }])
})();
