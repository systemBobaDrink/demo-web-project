var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope) {
    $scope.firstName = "bill";
    $scope.lastName = "Doe";
});

app.controller('pairedEventController', function($scope) {
    $scope.event = "Dia de Los Muertos";
});

