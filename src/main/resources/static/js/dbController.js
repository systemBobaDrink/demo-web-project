var app = angular.module('appDeprecated', []);


app.controller('dbCtrl', function ($scope, $http, $location) {
	
	$scope.name2="aa test";
	$scope.curUrl=$location.absUrl();
	$scope.port=$location.port();
	$scope.protocol = $location.protocol();
	$scope.host = $location.host();
	$scope.fullUrl = $scope.protocol + "://" + $scope.host + ":" + $scope.port;
	
	$scope.insertdata=function(){
		$http.post("/php/insert.php", {'id':$scope.id, 'name':$scope.name})
		.then(function(response) {
			$scope.postResultMessage = response.data;
			
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
	
	$scope.displayusers=function(){
		//$scope.name2="tocuhed";
		$http.get($scope.fullUrl + "/php/select.php")
		.then(function(data){
			$scope.name2="in displayusers function";
			$scope.data=data.data;
			
		}, function error(response){
			//$scope.name2="errrorrr";
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
	
	$scope.testconnection=function(){
//		$scope.curUrl=$location.absUrl().origin;
		$http.get($scope.fullUrl + "/php/connector.php")
		.then(function(response){
//			$scope.name2="in testconnection function";
//			$scope.data=data;
			$scope.name2=response.data;
			
		}, function error(response){
			//$scope.name2="errrorrr";
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
	
	
});

//app.controller('postcontroller', function($scope, $http, $location) {
//	$scope.submitForm = function(){
//		var url = $location.absUrl() + "postcustomer";
//		
//		var config = {
//                headers : {
//                    'Accept': 'text/plain'
//                }
//        }
//		var data = {
//            firstname: $scope.firstname,
//            lastname: $scope.lastname
//        };
//		
//		$http.post(url, data, config).then(function (response) {
//			$scope.postResultMessage = response.data;
//		}, function error(response) {
//			$scope.postResultMessage = "Error with status: " +  response.statusText;
//		});
//		
//		$scope.firstname = "";
//		$scope.lastname = "";
//	}
//	$scope.name="aaaa";
//});
//
//app.controller('getcontroller', function($scope, $http, $location) {
//	$scope.getfunction = function(){
//		var url = $location.absUrl() + "getallcustomer";
//		
//		$http.get(url).then(function (response) {
//			$scope.response = response.data
//		}, function error(response) {
//			$scope.postResultMessage = "Error with status: " +  response.statusText;
//		});
//	}
//});