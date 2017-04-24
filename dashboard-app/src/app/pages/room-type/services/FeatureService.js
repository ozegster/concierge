(function(){
    angular.module('ConciergeApp.pages.roomType')
        .factory('FeatureService',['$http','SERVER_PATH',function ($http, SERVER_PATH) {

           var getFeatures = function () {
                return $http.get(SERVER_PATH.url + '/features')
                    .then(function (response) {
                        return response;
                    }, function (error) {
                        return error;
                    });
           };

           return {
               getFeatures : getFeatures
           };

        }])
})()