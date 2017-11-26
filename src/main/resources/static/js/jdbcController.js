var app = angular.module('app', []);

app.controller('jdbcCntrl', function ($scope, $http) {
	
	$scope.testInput = "To be replaced";
	$scope.myData = 0;
	$scope.getByID = "Data goes here";
	$scope.foundUser;
	$scope.error = "";
	
	$scope.addUser = function() {
		$http.post("/sqlAddUser/" + "?firstName=" + $scope.firstName + "&lastName=" + $scope.lastName)
		.then(function(response){
			$scope.myData = response.data;
			console.log($scope.myData);
		});
	}
	
	$scope.getUserID = function() {
		$http.get("/sqlGetUserByID/" + "?id=" + $scope.id)
		.then(function mySuccess(response){
			$scope.foundUser = response.data;
		}, function myError(response){
			$scope.testInput = response.data;
		});	
	}
	
	$scope.addEvent = function(){
		$http.post("/sqlAddEvent/" + "?eventName=" + $scope.eventName + "&hostEmail=" + $scope.hostEmail + "&description=" + $scope.description 
				   + "&priv=" + $scope.priv + "&location=" + $scope.location + "&eventTime=" + $scope.eventTime + "&eventDate=" + $scope.eventDate)
		.then(function(response){			   
		});
	}
	
	$scope.getEventByID = function(){
		$http.get("/sqlGetEventByID/" + "?id=" + $scope.eventID)
		.then(function mySuccess(response){
			$scope.foundEvent = response.data;
		}, function myError(response){
			$scope.error = "Problem getting event from sql";
		});
	}
	
	$scope.getNumEvents = function(){
		$http.get("/sqlGetEventNum/")
		.then(function mySuccess(response){
			$scope.testInput = response.data;
		}, function myError(response){
			$scope.error = "Problem getting Num of events.";
		});
	}
	
	$scope.addUserEventLink = function(){
		$http.post("/sqlAddUserEventLink/" + "?userID=" + $scope.userID + "&eventID=" + $scope.eventID)
		.then(function(response){
		});
	}
	
	$scope.getEventsUserIsApartOf = function(){
		$http.get("/sqlGetUserEventLinkUsers/" + "?userID=" + $scope.userID)
		.then(function mySuccess(response){
			$scope.testInput = response.data;
		}, function myError(response){
			$scope.error = "Problem getting all events user is a part of.";
			$scope.testInput = response.data;
		});
	}
	
	$scope.getEventsUserIsApartOfReturnObject = function(){
		$http.get("/sqlGetEventsUserIsApartOfReturnObject/" + "?userID=" + $scope.userID)
		.then(function mySuccess(response){
			$scope.testInput = response.data;
		}, function myError(response){
			$scope.error = "Problem getting all events user is a part of.";
			$scope.testInput = response.data;
		});
	}
	
	$scope.getUsersApartOfEvent = function(){
		$http.get("/sqlGetUserEvenLinkEvents/" + "?eventID=" + $scope.eventID)
		.then(function mySuccess(response){
			$scope.testInput = response.data;
		}, function myError(response){
			$scope.error = "Problem getting all events user is a part of.";
			$scope.testInput = response.data;
		});
	}
	
	$scope.getAllEvents = function(){
		$http.get("/sqlGetAllEvents/")
		.then(function mySuccess(response){
			$scope.testInput = response.data;
		}, function myError(response){
			$scope.error = "Problem getting all events.";
			$scope.testInput = response.data;
		});
	}
});

