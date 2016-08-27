var TicketPathApp = angular.module('TicketPathApp', ['ngMaterial']);

TicketPathApp
    .controller('TicketCtrl', function($scope, $http) {
        console.log('TicketCtrl', $scope, $http);

        $http.get('/artists').then(function(response) {
            console.log('artists', response.data);
            $scope.groups = response.data;
        });


        $scope.group = {};
        $scope.group.groupTitle = 'AC/DC';
        $scope.group.events = [
            { title: 'One concert', selected: false },
            { title: 'Another concert', selected: true },
            { title: 'Another 1', selected: false }
        ];

        $scope.selectedItem = '';

        $scope.querySearch = function() {
            return $scope.groups;
        };

        $scope.searchTextChange = function() {
            return $scope.groups;
        };

        $scope.selectedItemChange = function(item) {
            $scope.selectedItem = item;
            $http.get('/artists/' + item.id).then(function(response) {
                console.log('event', response);

            });
        };

        $scope.selectEvent = function(event) {
            console.log('selectEvent', event)
            $scope.group.events.forEach(function(item) { item.selected = false});
            event.selected = true;
        };
    });