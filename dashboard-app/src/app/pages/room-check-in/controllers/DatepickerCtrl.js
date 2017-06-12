(function(){
    'use strict';

    angular.module('ConciergeApp.pages.checkIn')
        .controller('DatepickerCtrl', DatepickerCtrl);

    function DatepickerCtrl($scope) {

        $scope.open = open;
        $scope.opened = false;
        $scope.format = 'dd.MM.yyyy';
        $scope.options = {
            showWeeks: false,
            minDate: new Date()
        };

        function open() {
            $scope.opened = true;
        }
    }
})();
