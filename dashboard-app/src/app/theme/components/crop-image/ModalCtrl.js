(function () {
    'use strict';

    angular.module('ConciergeApp.theme')
        .controller('ModalCtrl', ModalCtrl);

    function ModalCtrl ($scope,$timeout,$uibModalInstance) {
        $scope.cropper = {};

        $scope.getFileSystem = function () {
           $timeout (function () {
               var fileInput = document.getElementById('upload-image');
               fileInput.click();
           });
        }

        $scope.getCroppedImage = function () {
            $scope.imageSrc = angular.element(document.querySelector('#room-type-image'));
            $scope.imageSrc.attr('src',$scope.cropper.croppedImage);
            $uibModalInstance.dismiss();
        }

        $scope.closeModal = function () {
            $uibModalInstance.dismiss();
        }

    }
})()