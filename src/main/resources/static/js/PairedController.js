var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope) {

	$scope.carouselImages = [
		{source: "/images/calpoly.jpg", title:"Califorinia State Polytechnic University Pomona"} ,
		{source:  "/images/bric.jpg", title: "Bronco Recreation and Intramural Complex"},
		{source: "/images/japanesegarden.jpg", title: "George and Sakaye Aratani Japanese Garden"}];

	$scope.displayEventImages = [
		{source: "/images/calpoly.jpg", title:"Cal Poly Pomona"},
		{source:  "/images/bric.jpg", title: "BRIC"},
		{source: "/images/japanesegarden.jpg", title: "Japanese Garden"},
		{source: "/images/lylecenter.jpg", title: "Lyle Center"  },
		{source: "/images/calpoly.jpg", title:"Cal Poly Pomona1"},
		{source:  "/images/bric.jpg", title: "BRIC1"},
		{source: "/images/japanesegarden.jpg", title: "Japanese Garden1"},
		{source: "/images/lylecenter.jpg", title: "Lyle Center1"} ];


});

app.controller('pairedEventController', function($scope) {
	$scope.event = "Dia de Los Muertos";

});

app.controller('pairedCreateEventController', function($scope , $http) {
	$scope.eventName = "Name of Event";
	$scope.categories = ["Art", "Rock Climbing", "Studying", "Tennis", "Tutoring"];	
	//3. attach originalStudent model object
	$scope.originalEvent = {
			userID: '',
			eventName: '',
			eventVisibility: '',
			eventLocation: '',
			eventDate: '',
			eventTime: '',
			eventDescription: '',
			eventCategory: ''
	};
	$scope.test = "Please repalce";

	//4. copy originalStudent to student. student will be bind to a form 
	$scope.createdEvent = angular.copy($scope.originalEvent);
	
	//5. create submitStudentForm() function. This will be called when user submits the form
	$scope.submitCreateEventForm = function(){
		// TODO: HTTP REQUEST 
		// send $http request to save Event 
		
		$scope.test = container.data;
		
		$http.post("/sqlAddEvent/" + "?eventName=" + $scope.createdEvent.eventName + "&hostID=" + $scope.createdEvent.userID + "&description=" + $scope.createdEvent.eventDescription 
				   + "&priv=" + $scope.createdEvent.eventVisibility + "&location=" + $scope.createdEvent.eventLocation + "&eventTime=" + $scope.createdEvent.eventTime + "&eventDate=" + $scope.createdEvent.eventDate)
		.then(function(response){			   
		});
	}
	
	//6. create resetForm() function. This will be called on Reset button click.  
	$scope.resetForm = function () {
		$scope.createdEvent = angular.copy($scope.originalEvent);
	}

});



app.controller('pairedYourEventsController', function($scope) {
	$scope.event = "none";
});