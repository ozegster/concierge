(function () {
  'use strict';

  angular.module('ConciergeApp.theme.components')
      .directive('contentTop', contentTop);

  /** @ngInject */
  function contentTop($state) {
    return {
      restrict: 'E',
      templateUrl: 'app/theme/components/contentTop/contentTop.html',
      link: function($scope) {
        $scope.$watch(function () {
          $scope.activePageTitle = $state.current.title;
        });
      }
    };
  }

})();