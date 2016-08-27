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
        $scope.group.events = [];

        $scope.selectedItem = '';

        $scope.querySearch = function() {
            return $scope.groups;
        };

        $scope.searchTextChange = function() {
            return $scope.groups;
        };

        $scope.selectedItemChange = function(item) {
            $scope.selectedItem = item;
            if (item) {
                $http.get('/artists/' + item.id).then(function(response) {
                    document.getElementById('background-artist')
                        .setAttribute("style", "background-image: url(" + response.data.mainImageUrl + ")")
                });
                $http.get('/discovery/timeline/' + item.id).then(function(response) {
                    console.log('event', response.data);
                    $scope.group.events = response.data;
                    renderPoints($scope.group.events);
                });
            } else {
                console.error('item not found');
            }

        };

        $scope.selectEvent = function(event) {
            console.log('selectEvent', event)
            $scope.group.events.forEach(function(item) { item.isSelected = false});
            event.isSelected = true;
        };
    });