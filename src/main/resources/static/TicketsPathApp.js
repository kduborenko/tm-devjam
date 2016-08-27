var TicketPathApp = angular.module('TicketPathApp', ['ngMaterial']);

TicketPathApp
    .controller('TicketCtrl', function($scope) {
        $scope.message = 'Message scope!';
        $scope.buttonTitle = 'Goooo!';

        $scope.group = {};
        $scope.group.groupTitle = 'AC/DC';
        $scope.group.events = [
            { title: 'One concert' },
            { title: 'Another concert' }
        ];
    });