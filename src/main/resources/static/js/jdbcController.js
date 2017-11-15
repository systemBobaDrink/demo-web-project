var app = angular.module('app', []);

app.controller('jdbcCntrl', function ($scope, $http) {
	
	$scope.myData = 0;
	
	$scope.addUser = function() {
		$http.post("/sqlAddUser/" + "?firstName=" + $scope.firstName + "&lastName=" + $scope.lastName)
		.then(function(data){
			$scope.myData = data;
			console.log($scope.myData);
		});
	}


	$scope.getUser = function() {
		$http.get("cs480/user/" + $scope.userIdToSearch)
	  	.then(function(data){
	  		$scope.founduser = data;
	  	});
	}
});


//	$scope.addUser = function() {
//		$http.post("cs480/user/" + $scope.new_id + "?name=" + $scope.new_name + "&major=" + $scope.new_major)
//	  	.success(function(data){
//	  		$scope.loadUsers();
//	  	});
//}

