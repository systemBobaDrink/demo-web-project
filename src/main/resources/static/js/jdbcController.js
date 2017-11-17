var app = angular.module('app', []);

app.controller('jdbcCntrl', function ($scope, $http) {
	
	$scope.testInput = "To be replaced";
	$scope.myData = 0;
	$scope.getByID = "Data goes here";
	
	$scope.addUser = function() {
		$http.post("/sqlAddUser/" + "?firstName=" + $scope.firstName + "&lastName=" + $scope.lastName)
		.then(function(response){
			$scope.myData = response.data;
			console.log($scope.myData);
		});
	}
	
	$scope.getUserID = function() {
		$http.get("/sqlGetUserByID/" + "?id=" + $scope.id)
		.then(function(response){
			$scope.testInput = "asdasdasdasd";
//			$scope.firstName = response;
			console.log($scope.firstName);
		});
	}


	$scope.getUser = function() {
		$http.get("cs480/user/" + $scope.userIdToSearch)
	  	.then(function(data){
	  		$scope.getByID = data;
	  	});
	}
	
	
});


//	$scope.addUser = function() {
//		$http.post("cs480/user/" + $scope.new_id + "?name=" + $scope.new_name + "&major=" + $scope.new_major)
//	  	.success(function(data){
//	  		$scope.loadUsers();
//	  	});
//}

