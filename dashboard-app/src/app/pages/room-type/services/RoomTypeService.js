(function () {
    angular.module('ConciergeApp.pages.roomType')
        .factory('RoomTypeService', ['$http', 'SERVER_PATH', function ($http, SERVER_PATH) {
            var saveRoomType = function (roomType, file) {
                var fd = new FormData();
                fd.append('roomType', roomType);
                fd.append('image', file);
                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/roomtype',
                    data: fd,
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (response){
                    return response;
                }, function (error){
                    return error;
                });

            };

            return {
                saveRoomType: saveRoomType
            };
        }])
})();
