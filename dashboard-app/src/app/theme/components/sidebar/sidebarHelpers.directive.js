(function () {
  'use strict';

  angular.module('ConciergeApp.theme.components')
      .directive('sidebarToggleMenu', sidebarToggleMenu)
      .directive('sidebarCollapseMenu', sidebarCollapseMenu)
      .directive('sidebarTogglingItem', sidebarTogglingItem)
      .controller('sidebarTogglingItemCtrl', sidebarTogglingItemCtrl)
      .directive('uiSrefTogglingSubmenu', uiSrefTogglingSubmenu)
      .directive('uiSrefToggler', uiSrefToggler);

  /** @ngInject */
  function sidebarToggleMenu(sidebarService) {
    return {
      restrict: 'A',
      link: function(scope, elem) {
        elem.on('click', function($evt) {
          $evt.originalEvent.$sidebarEventProcessed = true;
          scope.$apply(function() {
            sidebarService.toggleMenuCollapsed();
          });
        });
      }
    };
  }

  /** @ngInject */
  function sidebarCollapseMenu(sidebarService) {
    return {
      restrict: 'A',
      link: function(scope, elem) {
        elem.on('click', function($evt) {
          $evt.originalEvent.$sidebarEventProcessed = true;
          if (!sidebarService.isMenuCollapsed()) {
            scope.$apply(function() {
              sidebarService.setMenuCollapsed(true);
            });
          }
        });
      }
    };
  }

  /** @ngInject */
  function sidebarTogglingItem() {
    return {
      restrict: 'A',
      controller: 'sidebarTogglingItemCtrl'
    };
  }

  /** @ngInject */
  function sidebarTogglingItemCtrl($scope, $element, $attrs, $state, sidebarService) {
    var vm = this;
    var menuItem = vm.$$menuItem = $scope.$eval($attrs.sidebarTogglingItem);
    if (menuItem && menuItem.subMenu && menuItem.subMenu.length) {
      vm.$$expandSubmenu = function() { console.warn('$$expandMenu should be overwritten by uiSrefTogglingSubmenu') };
      vm.$$collapseSubmenu = function() { console.warn('$$collapseSubmenu should be overwritten by uiSrefTogglingSubmenu') };

      var subItemsStateRefs = sidebarService.getAllStateRefsRecursive(menuItem);

      vm.$expand = function() {
        vm.$$expandSubmenu();
        $element.addClass('sidebar-item-expanded');
      };

      vm.$collapse = function() {
        vm.$$collapseSubmenu();
        $element.removeClass('sidebar-item-expanded');
      };

      vm.$toggle = function() {
        $element.hasClass('sidebar-item-expanded') ?
            vm.$collapse() :
            vm.$expand();
      };

      if (_isState($state.current)) {
        $element.addClass('sidebar-item-expanded');
      }

      $scope.$on('$stateChangeStart', function (event, toState) {
        if (!_isState(toState) && $element.hasClass('sidebar-item-expanded')) {
          vm.$collapse();
          $element.removeClass('sidebar-item-expanded');
        }
      });

      $scope.$on('$stateChangeSuccess', function (event, toState) {
        if (_isState(toState) && !$element.hasClass('sidebar-item-expanded')) {
          vm.$expand();
          $element.addClass('sidebar-item-expanded');
        }
      });
    }

    function _isState(state) {
      return state && subItemsStateRefs.some(function(subItemState) {
            return state.name.indexOf(subItemState) == 0;
          });
    }
  }

  /** @ngInject */
  function uiSrefTogglingSubmenu() {
    return {
      restrict: 'A',
      require: '^sidebarTogglingItem',
      link: function(scope, el, attrs, sidebarTogglingItem) {
        sidebarTogglingItem.$$expandSubmenu = function() { el.slideDown(); };
        sidebarTogglingItem.$$collapseSubmenu = function() { el.slideUp(); };
      }
    };
  }

  /** @ngInject */
  function uiSrefToggler(sidebarService) {
    return {
      restrict: 'A',
      require: '^sidebarTogglingItem',
      link: function(scope, el, attrs, sidebarTogglingItem) {
        el.on('click', function() {
          if (sidebarService.isMenuCollapsed()) {
            // If the whole sidebar is collapsed and this item has submenu. We need to open sidebar.
            // This should not affect mobiles, because on mobiles sidebar should be hidden at all
            scope.$apply(function() {
              sidebarService.setMenuCollapsed(false);
            });
            sidebarTogglingItem.$expand();
          } else {
            sidebarTogglingItem.$toggle();
          }
        });
      }
    };
  }

})();
