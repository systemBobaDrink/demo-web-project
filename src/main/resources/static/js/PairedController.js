var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope) {
     $scope.imageSources = 'images/bric.jpg';
     
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