(function () {
    angular.module('GuestApp.pages.nearby')
        .factory('NearbyService', ['$http', function ($http) {

            var url = location.protocol + '//' + location.hostname + ':8090';

            var getPlaces = function (keyword, radius) {

                return $http.get(url + '/places', {params: {"keyword": keyword,"radius":radius}})
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    })
            };

            var getImage = function (photo) {
                return $http({
                    method: 'POST',
                    url: url + '/images',
                    data: photo
                }).then(function (response) {
                    return response;
                }, function (error) {
                    return error;
                })
            };

            return {
                getPlaces: getPlaces,
                getImage: getImage
            }

        }])
})();
