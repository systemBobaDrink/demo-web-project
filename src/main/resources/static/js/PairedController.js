var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope) {
  
   $scope.carouselImages = [
  {source: "/images/bric.jpg", title:"bric"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly"}  ];
  
   $scope.displayEventImages = [
  {source: "/images/bric.jpg", title:"bric1"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly1"} ,
  {source: "/images/bric.jpg", title:"bric2"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly2"}, 
   {source: "/images/bric.jpg", title:"bric3"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly3"} ,
  {source: "/images/bric.jpg", title:"bric4"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly4"},
   {source: "/images/bric.jpg", title:"bric5"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly5"} ,
  {source: "/images/bric.jpg", title:"bric5"} ,
  {source:  "/images/calpoly.jpg", title: "cal poly5"} ];
  
   
});

app.controller('pairedEventController', function($scope) {
    $scope.event = "Dia de Los Muertos";
   
});

app.controller('pairedCreateEventController', function($scope) {
	$scope.eventName = "Name of Event";
	$scope.categories = ["Art", "Rock Climbing", "Studying", "Tennis", "Tutoring"];	
});

app.controller('pairedYourEventsController', function($scope) {
	$scope.event = "none";
});