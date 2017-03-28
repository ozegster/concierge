(function () {
    angular.module('ConciergeApp.pages.hotelinfo')
        .controller('HotelInfoCtrl', ['HotelInfoService', function (HotelInfoService) {
            var self = this;

            // getting countries from database to show in select box
            HotelInfoService.getCountries().then(function (data) {
                self.countries = data;
            }, function (errorMessage) {
                console.log(errorMessage);
            });

            // TO DO
            self.submit = function () {
                console.log(self.hotel);
            }
        }])
})();
