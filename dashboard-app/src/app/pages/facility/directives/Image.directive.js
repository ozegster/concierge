(function () {
    'use strict';

    angular.module('ConciergeApp.pages.facility')
        .directive('fileModel', fileModel);

    /** @ngInject */
    function fileModel($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);

                        var preview = document.querySelector('img');
                        var file = document.querySelector('input[type=file]').files[0];
                        var reader = new FileReader();

                        reader.addEventListener("load", function () {
                            preview.src = reader.result;
                        }, false);

                        if (file) {
                            reader.readAsDataURL(file);
                        }
                    });
                });
            }
        };
    }

})();