var app = angular.module('app', []);

app.controller('jdbcCntrl', function ($scope, $http) {
	
	$scope.testInput = "To be replaced";
	$scope.myData = 0;
	$scope.getByID = "Data goes here";
	$scope.foundUser;
	
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
		$http.post("/sqlAddEvent/" + )
	}
});

