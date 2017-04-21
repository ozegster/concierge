(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
        .controller('ModalCtrl', ModalCtrl);

    function ModalCtrl ($scope,$timeout,$uibModalInstance,$rootScope) {
        $scope.cropper = {};

        $scope.getFileSystem = function () {
             var fileInput = document.getElementById('upload-image');
             fileInput.click();
        }

        $scope.getCroppedImage = function () {
            if($scope.cropper.croppedImage == null){
               return;
            }
            $scope.imageSrc = angular.element(document.querySelector('#room-type-image'));
            $scope.imageSrc.attr('src',$scope.cropper.croppedImage);
            $scope.getFileFromCroppedImage();
            $uibModalInstance.dismiss();
        }

        $scope.closeModal = function () {
            $uibModalInstance.dismiss();
        }

        $scope.getFileFromCroppedImage = function () {
            var selectedImg = document.querySelector('input[type=file]').files[0];
            var reader = new FileReader();
            $rootScope.croppedImg = new File([$scope.cropper.croppedImage],selectedImg.name);
            if ($rootScope.croppedImg) {
                reader.readAsDataURL($rootScope.croppedImg);
            }
        }
    }
})()