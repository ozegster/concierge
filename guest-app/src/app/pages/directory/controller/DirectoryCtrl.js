(function () {
    'use strict';

    angular.module('GuestApp.pages.directory').controller('DirectoryCtrl', DirectoryCtrl);

    DirectoryCtrl.$inject = ['$scope', 'DirectoryService', "$uibModal"];
    function DirectoryCtrl($scope, DirectoryService, $uibModal) {

        $scope.floors = [];

        DirectoryService.getRooms().then(function (response) {
                $scope.getAllFacilities(response);
            }, function (response) {
               console.log(response);
            });


        $scope.getAllFacilities = function (rooms) {
            DirectoryService.getFacilities().then(function (response) {
                $scope.getAllFloorNumbers(rooms,response);
            }, function (response) {
                console.log(response);
            })
        };

        $scope.getAllFloorNumbers = function (rooms, facilities) {
            var floorNumbers = [];
            var arr = rooms.concat(facilities);
            var floor;

            angular.forEach(arr, function (value,key) {

                if(value.floor === undefined) {
                    floor = value.floorNumber;
                }else {
                    floor = value.floor;
                }

                if($scope.isNumberExists(floorNumbers,floor) === -1) {
                    floorNumbers.push(floor);
                }
            });

            floorNumbers = floorNumbers.sort(function (a, b) {  return a - b;  });
            $scope.getListOfFloors(arr, floorNumbers);
        };

        $scope.getListOfFloors = function (arr, floorNumbers) {
            var currentFloor = {};
            var allFloors = [];

            angular.forEach(floorNumbers, function (valueFloorNumbers, keyFloorNumbers) {
                currentFloor = {
                    number : '',
                    rooms : [],
                    facilities : []
                };
                currentFloor.number = valueFloorNumbers;
                angular.forEach(arr, function (valueArr, keyArr) {
                    if(valueFloorNumbers === valueArr.floorNumber){
                        currentFloor.rooms.push(valueArr)
                    }
                    if(valueFloorNumbers === valueArr.floor) {
                        currentFloor.facilities.push(valueArr)
                    }
                });
                allFloors.push(currentFloor);
            });
            $scope.floors = allFloors;
        };

        $scope.isNumberExists = function (floorNumbers, currentFloor) {
            return floorNumbers.indexOf(currentFloor)
        };

        $scope.getRoomData = function (room){
            $scope.room = room;
            $uibModal.open({
                templateUrl: 'app/pages/directory/view/room-data-modal.html',
                controller: 'DirectoryCtrl',
                scope: $scope
            });
        };

        $scope.getFacilityData = function (facility){
            $scope.facility = facility;
            $uibModal.open({
                templateUrl: 'app/pages/directory/view/facility-data-modal.html',
                controller: 'DirectoryCtrl',
                scope: $scope
            });
        }
    }
})();


