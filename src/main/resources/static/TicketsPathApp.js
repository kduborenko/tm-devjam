angular.module('TicketPathApp', ['ngMaterial'])
    .controller('TicketCtrl', function($scope) {
        $scope.message = 'Message scope!';
        $scope.buttonTitle = 'Goooo!';
    });