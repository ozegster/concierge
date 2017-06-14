(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
        .controller('ModalCtrl', ModalCtrl);

    function ModalCtrl ($scope,$uibModalInstance) {
        $scope.cropper = {};

        $scope.getFileSystem = function () {
            var fileInput = document.getElementById('upload-image');
            fileInput.click();
        };

        $scope.getCroppedImage = function () {
            if($scope.cropper.croppedImage === null){
               return;
            }
            $scope.imageSrc = angular.element(document.querySelector($scope.element));
            $scope.imageSrc.attr('src',$scope.cropper.croppedImage);
            $scope.getFileFromCroppedImage();
            $uibModalInstance.dismiss();
        };

        $scope.closeModal = function () {
            $uibModalInstance.dismiss();
        };

        $scope.getFileFromCroppedImage = function () {
            var byteArray = $scope.getBlobFromBase64($scope.cropper.croppedImage);
            var fileImg = new File([byteArray],"image.png");
            var reader = new FileReader();
            $scope.$parent.croppedImg = fileImg;

            if ($scope.$parent.croppedImg) {
                reader.readAsDataURL($scope.$parent.croppedImg);
            }
        };

        $scope.getBlobFromBase64 = function (dataURI) {
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