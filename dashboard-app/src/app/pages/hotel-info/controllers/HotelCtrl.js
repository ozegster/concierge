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
            $scope.element = '#logo-image';
            $uibModal.open({
                templateUrl: 'app/theme/components/crop-image/crop-upload-imageLogo.html',
                controller: 'ModalCtrl',
                scope: $scope
            })
        };

        HotelService.getHotel().then(function (response) {
            $scope.hotel = response.data;
            if($scope.hotel.imageLogo != null) {
                $scope.loadImage($scope.hotel.imageLogo);
            } else $scope.hotel.imageLogo = $scope.imageSrc;
        }, function (error) {
            console.log(error);
        });

        $scope.closeHotelModal = function () {
            $uibModalStack.dismissAll();
        };

        $scope.submit = function (hotel) {
            if (hotel.$invalid) {
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

        $scope.loadImage = function (image) {
            HotelService.getImageLogo(image).then(function (data) {
                var panelImage = angular.element(document.querySelector('#logo-image'));
                panelImage.attr('src', 'data:image/jpeg;base64,' + data);
                var base64Image = 'data:image/jpeg;base64,' + data;
                $scope.getFileFromImage(base64Image)
            });
        };

        $scope.isImageMissing = function () {
            var image = angular.element('#logo-image').attr('src');
            return image === $scope.imageSrc;
        };

        $scope.getFileFromImage = function (img) {
            var byteArray = $scope.getByteFromBase64(img);
            var fileImg = new File([byteArray], 'name.png');
            var reader = new FileReader();
            $scope.croppedImg = fileImg;

            if ($scope.croppedImg) {
                reader.readAsDataURL($scope.croppedImg);
            }
        };

        $scope.getByteFromBase64 = function (dataURI) {
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
    }
})();
