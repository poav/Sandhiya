'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('LoginCtrl', function ($scope,$rootScope,pobCoreServerUrl,$http,$location) {
    
    

    
    $scope.validateAndLogin=function()
    {
    	var credentials={

    	username: $scope.username,
    	password: $scope.password

    	};

    	$http.post(pobCoreServerUrl+"/loginValidation",credentials)
      .then(function(response){

      	
      	$rootScope.currentUser=response.data;
      	$rootScope.loginValidator=true;
      	$location.path("/admin");
      });
    }

    
  });
