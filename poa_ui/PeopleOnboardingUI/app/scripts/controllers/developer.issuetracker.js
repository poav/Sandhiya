'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:DeveloperIssuetrackerCtrl
 * @description
 * # DeveloperIssuetrackerCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('DeveloperIssuetrackerCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService',function ($scope,$rootScope,$http,$uibModal,gridOptionsService) {
    

	$scope.add= function () {

		var modalInstance = $uibModal.open({
    	animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.location.addNewLocation.html',
        controller: 'ConfigurationAddLocationCtrl',
        size: 'md',
        resolve: {
        newObject: function () {
          return $scope.newBean;
        }
       }
   });

    	modalInstance.result.then(function (selectedItem) {
      	$scope.selected = selectedItem;
    	});

    };

    $rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30]
	};
    
  $scope.gridOptions.columnDefs = [
    { name: 'feedbackProvider', displayName: 'FeedBack Provider' },
     { name: 'feedbackType', displayName: 'Type' },
      { name: 'summary', displayName: 'Summary' },
      { name: 'details', displayName: 'Details' },
      { name: 'status', displayName: 'Status' },
      { name: 'fixedBy', displayName: 'Fixed By' },
      { name: 'fixedDate', displayName: 'Fixed Date' },
      { name: 'targetRelease', displayName: 'Target Releases' },
      { name: 'fixedComments', displayName: 'Comments' }
        
  ];
 
    $http.get("http://localhost:8080/retrieveAllFeedBacks")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $scope.newBean=response.data[0];
       
    });

}]);

app.controller('ConfigurationAddLocationCtrl', function($scope, $uibModalInstance, $rootScope,newObject,

    $http) {

	$scope.newObject=newObject;

	$scope.newObject.locationId=null;
	$scope.newObject.city=null;
	$scope.newObject.state=null;
	$scope.newObject.country=null;
	$scope.newObject.isActive=1;

    
    
    $scope.add = function(obj) {

    	
    	
    	

         $http.post("http://localhost:8080/addLocation",obj)
        .then(function(){

            $http.get("http://localhost:8080/retrieveLocations")
            .then(function(response) {
               	gridOptionsService.add(0,response.data);
                $scope.gridOptions.data=gridOptionsService.get(0);
               	$uibModalInstance.close(1);
            	  $uibModalInstance.dismiss('cancel');
       
            });

            

        });
    };

    $scope.cancel=function()
    {
    	$uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');
    };

});