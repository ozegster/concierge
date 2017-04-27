(function () {
    angular.module('ConciergeApp.pages.roomType')
        .factory('RoomTypeService', ['$http', 'SERVER_PATH', function ($http, SERVER_PATH) {
            var saveRoomType = function (roomType, file) {
                if (file) {
                    roomType.image = file.name;
                } else {
                    roomType.image = '';
                }
                var fd = new FormData();
                fd.append('roomType', new Blob([JSON.stringify(roomType)], {
                    type: "application/json"
                }));
                fd.append('image', file);
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/room-type',
                    data: fd,
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });

            };

            var isExistingName = function (name) {
                return $http({
                    method: 'GET',
                    url: SERVER_PATH.url + '/room-types/' + name,
                }).then(function (response) {
                    return response.data;
                }, function (error) {
                    return error;
                });

            };

            return {
                saveRoomType: saveRoomType,
                isExistingName: isExistingName
            };
        }])
})();
