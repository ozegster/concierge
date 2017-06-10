(function () {
    angular.module('ConciergeApp.pages.roomType')
        .factory('RoomTypeService', ['$http', 'SERVER_PATH', function ($http, SERVER_PATH) {
            var saveRoomType = function (roomType, image) {
                var byte = [];
                var fd = new FormData();

                if (typeof image == 'string') {
                    byte = getBlobFromBase64(image);
                    roomType.image = 'name';
                    fd.append('image', new Blob([byte]));
                } else {
                    fd.append('image',image);
                    roomType.image = image.name;
                }

                fd.append('roomType', new Blob([JSON.stringify(roomType)], {
                    type: "application/json"
                }));

                return $http({
                    method: 'POST',
                    url: SERVER_PATH.url + '/room-types',
                    data: fd,
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (response) {
                    return response;
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

            var getAllRoomTypes = function () {
                return $http({
                    method: 'GET',
                    url: SERVER_PATH.url + '/room-types'
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };

            var deleteRoomType = function (selectedRoomTypeId) {
                return $http({
                    method: 'DELETE',
                    url: SERVER_PATH.url + '/room-type/' + selectedRoomTypeId
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                });
            };

            return {
                saveRoomType: saveRoomType,
                getAllRoomTypes: getAllRoomTypes,
                deleteRoomType: deleteRoomType,
            };
        }])
})();
