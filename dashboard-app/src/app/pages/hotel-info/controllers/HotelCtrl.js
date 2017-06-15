(function () {
    'use strict';

    angular.module('ConciergeApp.pages.hotelInfo')
        .controller('HotelCtrl', HotelCtrl);

    HotelCtrl.$inject = ['HotelService', '$scope', 'toastr', '$uibModal', '$uibModalStack'];
    function HotelCtrl(HotelService, $scope, toastr, $uibModal, $uibModalStack) {
        $scope.hotel = {};
        $scope.imageSrc = 'assets/img/placeholder-hotel-logo.jpg?_ts=' + new Date().getTime();

        // getting countries from database to show in select box
        HotelService.getCountries().then(function (response) {
            $scope.countries = response.data;
        }, function (errorMessage) {
            console.log(errorMessage);
        });

        $scope.openModal = function () {
            $scope.element = '#hotel-logo';
            $uibModal.open({
                templateUrl: 'app/theme/components/crop-image/crop-upload-imageLogo.html',
                controller: 'ModalCtrl',
                scope: $scope
            })
        };

        HotelService.getHotel().then(function (response) {
            $scope.hotel = response.data;
            if($scope.hotel.imageLogo != null) {
                var panelImage = angular.element(document.querySelector('#hotel-logo'));
                panelImage.attr('src', $scope.hotel.imageLogo);
                $scope.croppedImg = response.data.imageLogo;
            } else {
                $scope.hotel.imageLogo = $scope.imageSrc;
            }
        }, function (error) {
            console.log(error);
        });

        $scope.closeHotelModal = function () {
            $uibModalStack.dismissAll();
        };

        $scope.submit = function (hotel) {
            if (hotel.$invalid || $scope.isImageMissing()) {
                return;
            }
            HotelService.saveHotel($scope.hotel, $scope.croppedImg).then(function (response) {
                if (response.status === 200) {
                    toastr.success(response.data.name + ' has successfully saved', 'Save Hotel');
                    hotel.$setPristine();
                } else {
                    if (response.data.errors) {
                        angular.forEach(response.data.errors, function (value, key) {
                            toastr.error(response.data.errors[key].defaultMessage, 'Error');
                        });
                    } else {
                        toastr.error(response.data.error, 'Error');
                    }
                }
            }, function (error) {
                console.log(error);
            });

            $scope.closeHotelModal();
        };

        $scope.isImageMissing = function () {
            var image = angular.element('#logo-image').attr('src');
            return image === $scope.imageSrc;
        };
    }
})();
