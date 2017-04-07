(function(){
    'use strict';


    angular.module('ConciergeApp.pages.roomInfo', ['ui.select'])
           .config(routeConfig);



  /** @ngInject */
  function routeConfig($stateProvider) {
       $stateProvider
           .state('room', {
                 url: '/room',
                 template : '<ui-view autoscroll="true" autoscroll-body-top></ui-view>',
                 title: 'Room',
                 sidebarMeta: {
                     icon: 'ion-compose',
                     order: 250,
                 },
           })
           .state('room.roomType', {
                  url: '/room',
                  templateUrl: 'app/pages/room-info/views/room-info.html',
                  title: 'Room Type',
                  sidebarMeta: {
                     order: 0,
                  },
           })
  }

})()