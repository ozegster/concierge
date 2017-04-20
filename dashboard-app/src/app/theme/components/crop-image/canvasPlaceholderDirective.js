(function (){
    angular.module('ConciergeApp.theme')
        .directive('canvasPlaceholder', function () {
            return {
                restrict: 'A',
                link: function(scope, element, attrs) {
                    element.ready(function () {
                        var canvas = document.getElementById('canvas')
                        var context = canvas.getContext('2d');
                        var image = new Image();
                        image.onload = function () {
                            context.drawImage(image,0,0,image.width,image.height,0,0,canvas.width,canvas.height);
                        }
                        image.src = 'assets/img/placeholder.png';
                    })
                }
            };
        });
})();