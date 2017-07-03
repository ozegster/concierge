(function () {
    angular.module('GuestApp.pages.nearby')
        .factory('NearbyService', ['$http', function ($http) {

            var getPlaces = function (keyword) {

                return $http.get(location.protocol + '//' + location.hostname + ':8090' + '/places', {params:{"data" : keyword}}

                ).then(function (response) {
                    return response;
                },function (error){
                    return error;
                })
            };

            return {
                getPlaces : getPlaces
            }

        }])
})();
