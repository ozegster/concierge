(function () {
    'use strict';

    angular.module('GuestApp.pages.home')
        .factory('HomeService', ['$http', '$q', function ($http,$q) {

            var getHotel = function () {
                var deferred = $q.defer();
                $http.get(location.protocol + '//' + location.hostname + ':8090' + '/hotels')
                    .then(function (response) {
                        if (!response.data) {
                            deferred.reject("There isn't a hotel in db");
                        } else {
                            deferred.resolve(response);
                        }
                    }, function (error) {
                        deferred.reject(error);
                    });
                return deferred.promise;
            };


            return {
                getHotel : getHotel
            };
        }])
})();
