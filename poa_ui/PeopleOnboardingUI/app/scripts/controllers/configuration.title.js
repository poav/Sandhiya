'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationTitleCtrl
 * @description
 * # ConfigurationTitleCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
 
 app.controller('ConfigurationTitleCtrl',['$scope','$rootScope','$http','$uibModal','gridOptionsService','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,pobCoreServerUrl) {
    
 	/*$scope.batchUpload=function()
 	{
 		var obj=$scope.titleBatchFile;
 		$http.post("http://localhost:8080/addTitle",obj)
        .then(function(){

            $http.get("http://localhost:8080/retrieveAllPositionsBeans")
            .then(function(response) {
               	$rootScope.gridOptions.data=response.data;
               	$uibModalInstance.close(1);
            	$uibModalInstance.dismiss('cancel');     
            });
        };
 		
 	};*/


	$scope.add= function () {

		var modalInstance = $uibModal.open({
    	animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.title.addTitle.html',
        controller: 'ConfigurationAddTitleCtrl',
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
    	columnDefs: [
    		{ name: 'positionName', displayName: 'Career Stage' },
    		{ name: 'domain', displayName: 'Domain'},
    		{ name: 'level', displayName: 'Level' },
    		{ name: 'isUsed', displayName: 'Active',cellFilter: 'isActiveFilter:this'}	
    		],
        rowHeight: 30,
        gridFooterHeight:30,
        gridHeaderHeight:30,
        paginationPageSizes:[10,20,30],
        onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;  
        $scope.pageSize=10;

        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        $scope.pageNumber = newPage;
        $scope.pageSize = pageSize;

        $scope.totalLength=$rootScope.gridOptions.data.length;

        if($scope.totalLength < newPage*pageSize)
        $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;

        angular.element(document.getElementsByClassName('grid')[0]).css('height',  ($scope.pageSize*40)+70 + 'px');

        //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    


        });
        }
    };
    

  	

 
    $http.get(pobCoreServerUrl+"/retrieveAllPositionsBeans")
    .then(function (response) {
        $rootScope.gridOptions.data=response.data;
        $scope.newBean=response.data[0];     
    });

}]);



app.controller('ConfigurationAddTitleCtrl', function($scope, $uibModalInstance, $rootScope,newObject,pobCoreServerUrl,

    $http) {


    $http.get(pobCoreServerUrl+"/getPositionBean")
    .then(function(response){
        
        $scope.newObject=response.data;
        $scope.newObject.isUsed=1;
    });

    $http.get(pobCoreServerUrl+"/retrieveAllCategories")
    .then(function(response){
        
        $scope.domainList=response.data;
      
    });

    $http.get(pobCoreServerUrl+"/getDesignationNamesList")
    .then(function(response){
        
        $scope.designationList=response.data;
      
    });

     $http.get(pobCoreServerUrl+"/getDesignationLevelsNamesList")
    .then(function(response){
        
        $scope.designationLevelList=response.data;
      
    });

    $scope.add = function() {
    	

         $http.post(pobCoreServerUrl+"/addTitle",$scope.newObject)
        .then(function(){

            $http.get(pobCoreServerUrl+"/retrieveAllPositionsBeans")
            .then(function(response) {
               	$rootScope.gridOptions.data=response.data;
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