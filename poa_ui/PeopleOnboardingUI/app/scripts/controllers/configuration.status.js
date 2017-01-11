'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationStatusCtrl
 * @description
 * # ConfigurationStatusCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('ConfigurationStatusCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,pobCoreServerUrl) {
    
    $scope.add= function () {

		var modalInstance = $uibModal.open({
    	animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.status.addStatus.html',
        controller: 'ConfigurationAddStatusCtrl',
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

    $scope.gridOptions = {
    columnDefs :[
      { name: 'statusName', displayName: 'Phase Name' },
      { name: 'resultName', displayName: 'Current Status Name'}],
    enablePaginationControls: true,
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

      $scope.totalLength=$scope.gridOptions.data.length;

      if($scope.totalLength < newPage*pageSize)
      $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;
      
      angular.element(document.getElementsByClassName('grid')[0]).css('height',  ($scope.pageSize*30)+65 + 'px');

      //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    


    });
    }
    	};
    
  	

 
    $http.get(pobCoreServerUrl+"/displayAllStatusBeans")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $scope.newBean=response.data[0];
       
    });

}]);



app.controller('ConfigurationAddStatusCtrl', function($scope,pobCoreServerUrl, $uibModalInstance, $rootScope,newObject,gridOptionsService,

    $http) {

	$scope.newObject=newObject;

	$scope.newObject.statusName=null;
	$scope.newObject.resultName=null;

    $scope.checkIfDuplicateStatus=function()
    {
      $scope.isNameNotValid=false;
      $http.get(pobCoreServerUrl+"/displayAllStatusBeans")
            .then(function(response) {
        $scope.statusList= response.data;
        var list=$scope.statusList;
        var status;

        for(var i=0; i<list.length; i++)
        {
            status=list[i];

            
          if(status.resultName === $scope.newObject.resultName){

            $scope.isNameNotValid=true;
                        break;
          }
          else
          {
            $scope.isNameNotValid=false; 

          }
        }
      });
    };
    
    $scope.add = function(obj) {

    	
    	obj.statusId=0;

         $http.post(pobCoreServerUrl+"/addStatus",obj)
        .then(function(){

            

            $http.get(pobCoreServerUrl+"/displayAllStatusBeans")
            .then(function(response) {
               	 gridOptionsService.add(0,response.data);
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

