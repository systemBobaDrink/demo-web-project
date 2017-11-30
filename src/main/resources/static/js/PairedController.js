var app = angular.module('pairedApp', []);
app.controller('pairedController', function($scope, $location) {
	
	$scope.carouselImages = [
		{source: "/images/calpoly.jpg", title:"Califorinia State Polytechnic University Pomona"} ,
		{source:  "/images/bric.jpg", title: "Bronco Recreation and Intramural Complex"},
		{source: "/images/japanesegarden.jpg", title: "George and Sakaye Aratani Japanese Garden"}];

	$scope.displayEventImages = [
		{source: "/images/programming.jpg", title: "CS 365 Study Group", link: "/events/#StudyGroups"},
		{source: "/images/rockclimbing.jpeg", title:"Rock Climbing", link: "/events/#Fitness"},
		{source:  "/images/hiking.jpeg", title: "Backpacking and Hiking Trails", link: "/events/#OutdoorActivities"},
		{source: "/images/strangerthings.jpg", title: "Stranger Things Fan Club", link: "/events/#TV_Shows"},
		{source: "/images/studying.jpeg", title: "Cramming for Finals", link: "/events/#StudyGroups"},
		{source:  "/images/jamsesh.jpeg", title: "Musician's Club", link: "/events/#Music"},
		{source: "/images/pocketcamp.jpg", title:"Video Game Addicts", link: "/events/#Games"},
		{source: "/images/conference.jpeg", title: "Professional Development and Conferences", link: "/events/#Professional_Development"},
		{source: "/images/socialdance.jpeg", title: "Salsa Social Dance Event", link: "/events/#Music"} ];

});

app.filter('unique', function () {

  return function (items, filterOn) {

    if (filterOn === false) {
      return items;
    }

    if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
      var hashCheck = {}, newItems = [];

      var extractValueToCompare = function (item) {
        if (angular.isObject(item) && angular.isString(filterOn)) {
          return item[filterOn];
        } else {
          return item;
        }
      };

      angular.forEach(items, function (item) {
        var valueToCheck, isDuplicate = false;

        for (var i = 0; i < newItems.length; i++) {
          if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
            isDuplicate = true;
            break;
          }
        }
        if (!isDuplicate) {
          newItems.push(item);
        }

      });
      items = newItems;
    }
    return items;
  };
});
app.controller('pairedEventController', function($scope, $http, $location) {
	$scope.userID = "";
	$scope.curCategory = "aa";
	$scope.updateCategoryInput = function(value) {
		if(value == "StudyGroups")
			value = "Study Groups"; 
		if(value == "OutdoorActivities")
			value = "Outdoor Activities";
		if(value == "TV_Shows")
			value = "TV Shows";
		if(value == "Professional_Development")
			value = "Professional Development";
		$scope.filterCategory = value ; 
	}
	$scope.getAllEvents = function(){
		$scope.curCategory = $location.absUrl().split('#')[2];
		$scope.updateCategoryInput($scope.curCategory);
		
		$scope.status = "active";
		$http.get("/sqlGetAllEvents/")
		.then(function mySuccess(response){
			$scope.testInput = response.data;
			$scope.status = "active";
		}, function myError(response){
			$scope.error = "Problem getting all events.";
			$scope.testInput = response.data;
		});
	}
	$scope.addUserEventLink = function(eventID){
		$http.post("/sqlAddUserEventLink/" + "?userID=" + $scope.userID + "&eventID=" + eventID)
		.then(function(response){
		});
	}
	$scope.getUserIDFromEmail = function(){
		
		
		var decodedCookie = decodeURIComponent(document.cookie);
	    var string = decodedCookie.split(';');
	    
	    var emailCookie = string[1].split('=');
	    $scope.email = emailCookie[1];
	    
	    if($scope.email == "")
	    	return;
	    
		$http.get("/sqlGetUserIDFromEmail/" + "?email=" + $scope.email)
		.then(function mySuccess(response){
			
			$scope.userID = response.data;
		}, function myError(response){
			
			$scope.error = "Problem getting id from email";
		});
	}
	
});

app.controller('pairedCreateEventController', function($scope , $http) {
	$scope.eventName = "Name of Event";
	$scope.categories = ["Art", "Fitness", "Music", "Professional Development", "Outdoor Activities", "Study Groups", "TV Shows", "Games"];	
	//3. attach originalStudent model object
	$scope.originalEvent = {
			userEmail: '',
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
		$scope.createdEvent.eventTime = $('#time').val();
		$scope.createdEvent.eventDate = $('#datepicker').val();
		$scope.test = $scope.createdEvent.eventCategory;

		$http.post("/sqlAddEvent/" + "?eventName=" + $scope.createdEvent.eventName + "&hostEmail=" + $scope.createdEvent.userEmail + "&description=" + $scope.createdEvent.eventDescription 
				+ "&priv=" + $scope.createdEvent.eventVisibility + "&location=" + $scope.createdEvent.eventLocation + "&eventTime=" + $scope.createdEvent.eventTime + "&eventDate=" + $scope.createdEvent.eventDate
				+ "&eventCategory=" + $scope.createdEvent.eventCategory)
				.then(function(response){			   
				}, function myError(){
					$scope.test = "Error thrown on post?";
				});
	}

	//6. create resetForm() function. This will be called on Reset button click.  
	$scope.resetForm = function () {		
		$scope.createdEvent = angular.copy($scope.originalEvent);
	}

});

app.controller('pairedYourEventsController', function($scope, $http) {
	$scope.testMe = "aaaa";
	
	$scope.getEventsUserIsApartOfReturnObject = function(userID){
		$scope.getUserIDFromEmail(function (data) {
			if(!data.err){
				$http.get("/sqlGetEventsUserIsApartOfReturnObject/" + "?userID=" + $scope.userID)
				.then(function mySuccess(response){
					$scope.testInput = response.data;
					$scope.bool = false; 
					if($scope.testInput[0].description == "You don't have any events."){ 
						$scope.testInput = "You don't have any events.";
						$scope.bool = true; 
					}
					
				}, function myError(response){
					$scope.error = "Problem getting all events user is a part of.";
					$scope.testInput = response.data;
				});
			}
		});
		
		
	}

	$scope.updateCategoryInput = function(value) {
		$scope.filterCategory = value ; 
	}
	
	$scope.getUserIDFromEmail = function(callback){
		
		
		var decodedCookie = decodeURIComponent(document.cookie);
	    var string = decodedCookie.split(';');
	    
	    var emailCookie = string[1].split('=');
	    $scope.email = emailCookie[1];
	    
	    if($scope.email == "")
	    	return;
	    
		$http.get("/sqlGetUserIDFromEmail/" + "?email=" + $scope.email)
		.then(function mySuccess(response){
			
			$scope.userID = response.data;
			callback({err: false});
		}, function myError(response){
			
			$scope.error = "Problem getting id from email";
			callback({err: true});
		});
	}
});

app.controller('pairedLoginController', function($scope, $window) {
	$scope.hasName = "Log in";
	$scope.loggedIn = false;
	$scope.username = "change this";
	$scope.email = "|| also this";
	
	$scope.initTest = function() {
		if(document.cookie.indexOf('username=') == -1) {
			document.cookie = "username=; path=/;";
			document.cookie = "email=; path=/;"
			$scope.hasName = "Log in";
			return;
		}
		
		var decodedCookie = decodeURIComponent(document.cookie);
	    var string = decodedCookie.split(';');
	    
	    var nameCookie = string[0].split('=');
	    var emailCookie = string[1].split('=');
	    $scope.username = nameCookie[1];
	    $scope.email = emailCookie[1];
	    
	    
	    if($scope.username == "")
	    	$scope.loggedIn = false;
	    else {
	    	$scope.loggedIn = true;
	    }
	}
	$scope.deleteCookie = function() {
		document.cookie = "username=; path=/;";
		document.cookie = "email=; path=/;"
		$window.location.reload();
	}
	
	$scope.createCookie = function() {
		var usernameString = "username=" + $scope.username + "; path=/;";
		document.cookie = usernameString;
		
		var emailString = "email=" + $scope.email + "; path=/;";
		document.cookie = emailString;
		
		$loggedIn = true;
		$window.location.reload();
	}
	
});
