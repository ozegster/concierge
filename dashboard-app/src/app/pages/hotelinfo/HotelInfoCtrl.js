(function () {
    angular.module('ConciergeApp.pages.hotelinfo')
        .controller('HotelInfoCtrl', HotelInfoCtrl);


    function HotelInfoCtrl() {
        var self = this;

        // dummy countries just to check if the select box works
        self.countries = ["Bosnia and Herzegovina", "Serbia", "Croatia"];

        // TO DO
        self.submit = function () {
            console.log(self.hotel);
        }
    }
})();
