var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope) {
  
   $scope.carouselImages = [
  {source: "/images/calpoly.jpg", title:"Cal Poly Pomona"} ,
  {source:  "/images/bric.jpg", title: "BRIC"},
  {source: "/images/japanesegarden.jpg", title: "Japanese Garden"},
  {source: "/images/lylecenter.jpg", title: "Lyle Center"  }];
  
   $scope.displayEventImages = [
  {source: "/images/calpoly.jpg", title:"Cal Poly Pomona"} ,
  {source:  "/images/bric.jpg", title: "BRIC"},
  {source: "/images/japanesegarden.jpg", title: "Japanese Garden"},
  {source: "/images/lylecenter.jpg", title: "Lyle Center"  },
  {source: "/images/calpoly.jpg", title:"Cal Poly Pomona1"} ,
  {source:  "/images/bric.jpg", title: "BRIC1"},
  {source: "/images/japanesegarden.jpg", title: "Japanese Garden1"},
  {source: "/images/lylecenter.jpg", title: "Lyle Center1"  } ];
  
   
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