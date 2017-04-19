(function () {
    angular.module('ConciergeApp.pages.roomType')
        .factory('BedTypeService', ['$http', 'SERVER_PATH', function ($http, SERVER_PATH) {

            var getBedTypes = function () {
                return $http.get(SERVER_PATH.url + '/beds')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
            };

            return {
                getBedTypes: getBedTypes
            };
        }])
})();
