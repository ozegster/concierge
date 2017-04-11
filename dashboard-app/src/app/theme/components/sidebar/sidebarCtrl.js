(function () {
  'use strict';

  angular.module('ConciergeApp.theme.components')
    .controller('sidebarCtrl', sidebarCtrl);

  /** @ngInject */
  function sidebarCtrl($scope, sidebarService) {

    $scope.menuItems = sidebarService.getMenuItems();
    $scope.defaultSidebarState = $scope.menuItems[0].stateRef;

    $scope.hoverItem = function ($event) {
      $scope.showHoverElem = true;
      $scope.hoverElemHeight =  $event.currentTarget.clientHeight;
      var menuTopValue = 66;
      $scope.hoverElemTop = $event.currentTarget.getBoundingClientRect().top - menuTopValue;
    };

    $scope.$on('$stateChangeSuccess', function () {
      if (sidebarService.canSidebarBeHidden()) {
        sidebarService.setMenuCollapsed(true);
      }
    });
  }
})();