var app = angular.module('app', []);

app.controller('jdbcCntrl', function ($scope, $http, $location) {
	
	$scope.addUser = function() {
		$http.post("/sqlAddUser/" + "?firstName=" + $scope.firstName + "?lastName=" + )
				   $scope.lastName)
		.success(function(data){});
	}
}

$scope.getUser = function() {
	  $http.get("cs480/user/" + $scope.userIdToSearch)
	  	.success(function(data){
	  		$scope.founduser = data;
	  	});
}

$scope.addUser = function() {
	  $http.post("cs480/user/" + $scope.new_id + "?name=" + $scope.new_name + "&major=" + $scope.new_major)
	  	.success(function(data){
	  		$scope.loadUsers();
	  	});
}