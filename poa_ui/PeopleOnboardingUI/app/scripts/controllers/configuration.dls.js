'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationDlsCtrl
 * @description
 * # ConfigurationDlsCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');

 app.controller('ConfigurationDlsCtrl',['$scope','$rootScope','$http','$uibModal','$filter','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,gridOptionsService,$mdDialog,pobCoreServerUrl) {

$scope.search="";

   $scope.search="";
  $scope.$watch('search', function() {
     

        if($scope.search===null ||$scope.search==="")
        {
          $scope.filteredList=gridOptionsService.get(0);
          $scope.initialList=gridOptionsService.get(0);
          $scope.searchLength=0;
        }
        else
        {
            if($scope.search.length < $scope.searchLength)
            {
                $scope.initialList=gridOptionsService.get(0);
                $scope.initialList=$filter('filter')($scope.initialList, $scope.search);
                $scope.filteredList=$scope.initialList;
                
            }
            else
            {
                $scope.filteredList= $filter('filter')($scope.filteredList , $scope.search);   

            }
            
            $scope.searchLength= $scope.search.length;
        }

        $rootScope.gridOptions.data=$scope.filteredList;

    }, true);

	$scope.add= function () {

		var modalInstance = $uibModal.open({
    	animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.dls.addDl.html',
        controller: 'ConfigurationAddDLCtrl',
        size: 'sm',
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
    paginationPageSizes:[10,20,30],
    rowHeight: 30,
    gridFooterHeight:30,
    gridHeaderHeight:30,
    onRegisterApi: function(gridApi) {
    $scope.gridApi = gridApi;  
    $scope.pageSize=10;

    gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
      $scope.pageNumber = newPage;
      $scope.pageSize = pageSize;

      $scope.totalLength=$rootScope.gridOptions.data.length;

      if($scope.totalLength < newPage*pageSize)
      $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;
      
      angular.element(document.getElementsByClassName('grid')[0]).css('height',  ($scope.pageSize*30)+50 + 'px');

      //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    


    });
    },
    columnDefs: [
    		{ name: 'email', displayName: 'DL Name' },
       		{ name: 'projectBean.teamBean.teamName', displayName: 'Team Name' },
       		{ name: 'projectBean.projectName', displayName: 'Project Name'},
    		{ name: 'locationBean.city', displayName: 'Location'},
    		{ name: 'isActive', displayName: 'Active',cellFilter: 'isActiveFilter:this'}	
    		],

    appScopeProvider: { 
        onDblClick : function(row) {

           	console.log(row.entity);

           	var modalInstance = $uibModal.open({
      		animation: $scope.animationsEnabled,
      		templateUrl: 'views/configuration.dls.editDl.html',
      		controller: 'ConfigurationEditDLCtrl',
      		size: 'md',
      		resolve: {
        		editedObject: function () {
          		return row.entity;
        		}
      		}
    	});

    	modalInstance.result.then(function (selectedItem) {
      		$scope.selected = selectedItem;
    	});

      }
    },
    rowTemplate: "<div ng-dblclick=\"grid.appScope.onDblClick(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
 	};


    $http.get(pobCoreServerUrl+"/retrieveAllDls")
    .then(function (response) {

       
        $rootScope.gridOptions.data=response.data;
        gridOptionsService.add(0,response.data);
        $scope.newBean=response.data[0];     
    });

}]);

app.controller('ConfigurationEditDLCtrl', function($scope, $uibModalInstance,editedObject,$rootScope,pobCoreServerUrl,
    $http) {

    $scope.editedObject=editedObject;

    $scope.activateProjectName=false;

    $http.get(pobCoreServerUrl+"/retrieveLocations").then(function(response) {
        $scope.locationList = response.data;
    });

    $http.get(pobCoreServerUrl+"/retrieveAllTeamNames").then(function(response) {
        $scope.teamNames = response.data;
    });

   

    $scope.isSelected = function(obj) {

        if(obj.isActive===1)
        return true;

    };

    $scope.isNotSelected = function(obj) {

        if(obj.isActive===0)
        return true;
    
    };

    $scope.getProjectList=function(teamName)
    {
      $http.post(pobCoreServerUrl+"/retrieveAllProjectBeansForTeam",teamName).then(function(response) {
          $scope.projectNames = response.data;
          $scope.activateProjectName=true;
      });

    }

  $scope.checkIfDuplicate=function(project,location)
    {

       if(project!=null )
        {
          $scope.newObject.projectBean.projectId=project.id;
        }

        if(location!=null)
        {
          $scope.newObject.locationBean.locationId=location.locationId;
        }

      if($scope.newObject.email != null && $scope.newObject.projectBean.id!= null && $scope.newObject.locationBean.locationId != null)
      {

        $http.get(pobCoreServerUrl+"/getDlByDlNameAndProjectIdAndLocationId",{
        params: {
          dlName : $scope.newObject.email,
          projectId: $scope.newObject.projectBean.id,
          locationId: $scope.newObject.locationBean.locationId
        }
        })
        .then(function (response) {
        $scope.oldBean= response.data;

          if($scope.oldBean ==="")
            $scope.isNameNotValid=false;
          else
            $scope.isNameNotValid=true; 

        });
      }
 
    };


    $scope.edit = function(editedObject) {

      var obj= $scope.editedObject;
    
        $http.post(pobCoreServerUrl+"/editDL",obj)
        .then(function(){

        		 $http.get(pobCoreServerUrl+"/retrieveAllDls")
    			.then(function (response) {
       				 $rootScope.gridOptions.data=response.data;      
    			});

        $uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');

        });

    };

    $scope.cancel = function() {

      $uibModalInstance.close(1);
      $uibModalInstance.dismiss('cancel');
    };

});

app.controller('ConfigurationAddDLCtrl', function($scope, $uibModalInstance, $rootScope,pobCoreServerUrl,newObject,$http) {

  $scope.activateProjectName=false;

      $http.get(pobCoreServerUrl+"/getEmailDlBean").then(function(response) {
        $scope.newObject = response.data;
        // $scope.newObject.email=null;
        // $scope.newObject.projectBean.teamBean.teamName=null;
        // $scope.newObject.projectBean.projectName=null;
        // $scope.newObject.locationBean.city=null;
        // $scope.newObject.projectBean.projectId=null;
        // $scope.newObject.locationBean.locationId=null;

    });

 	$http.get(pobCoreServerUrl+"/retrieveLocations").then(function(response) {
        $scope.locationList = response.data;
    });

    $http.get(pobCoreServerUrl+"/retrieveAllTeamNames").then(function(response) {
        $scope.teamNames = response.data;
    });

    $scope.getProjectList=function(teamName)
    {
      $http.post(pobCoreServerUrl+"/retrieveAllProjectBeansForTeam",teamName).then(function(response) {
          $scope.projectNames = response.data;
          $scope.activateProjectName=true;
      });

    }

  $scope.checkIfDuplicate=function(project,location)
    {

       if(project!=null )
        {
          $scope.newObject.projectBean.projectId=project.id;
        }

        if(location!=null)
        {
          $scope.newObject.locationBean.locationId=location.locationId;
        }

      if($scope.newObject.email != null && $scope.newObject.projectBean.id!= null && $scope.newObject.locationBean.locationId != null)
      {

        $http.get(pobCoreServerUrl+"/getDlByDlNameAndProjectIdAndLocationId",{
        params: {
          dlName : $scope.newObject.email,
          projectId: $scope.newObject.projectBean.id,
          locationId: $scope.newObject.locationBean.locationId
        }
        })
        .then(function (response) {
        $scope.oldBean= response.data;

          if($scope.oldBean ==="")
            $scope.isNameNotValid=false;
          else
            $scope.isNameNotValid=true; 

        });
      }
 
    };



    $scope.add = function() {

        $http.post(pobCoreServerUrl+"/saveDL",$scope.newObject)
        .then(function(){

            $http.get(pobCoreServerUrl+"/retrieveAllDls")
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
