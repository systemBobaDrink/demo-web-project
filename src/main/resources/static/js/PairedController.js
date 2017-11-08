var app = angular.module('pairedApp', ['ngMaterial', 'ngMessages']);
app.controller('pairedController', function($scope) {
    $scope.firstName = "bill";
    $scope.lastName = "Doe";
});

app.controller('pairedEventController', function($scope) {
    $scope.event = "Dia de Los Muertos";
});

app.controller('pairedCreateEventController', function($scope) {
	$scope.eventName = "Name of Event";
	$scope.categories = ["Art", "Rock Climbing", "Studying", "Tennis", "Tutoring"];
	$scope.dateChoose = function() { 
		this.myDate = new Date();
  		this.isOpen = false;
  	}
	
});

app.controller('pairedYourEventsController', function($scope) {
	$scope.event = "none";
});