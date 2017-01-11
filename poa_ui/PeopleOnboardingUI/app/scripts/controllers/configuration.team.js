'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationLocationCtrl
 * @description
 * # ConfigurationLocationCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
  
app.filter('isActiveFilter', function () {
    return function (status) {
      if(status===true  || status===1)
      return "Active";
      else
      return "InActive";
    };
  });

app.controller('ConfigurationTeamCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$filter','pobCoreServerUrl','uiGridConstants',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$filter,pobCoreServerUrl,uiGridConstants) {
  
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
        templateUrl: 'views/configuration.team.addTeam.html',
        controller: 'ConfigurationAddTeamCtrl',
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

      columnDefs : [
      { name: 'teamName', displayName: 'Team Name' },
      { name: 'isActive', displayName: 'Is Active',cellFilter: 'isActiveFilter:this'}],
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

        $scope.totalLength=$rootScope.gridOptions.data.length;

        if($scope.totalLength < newPage*pageSize)
        $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;
        
        angular.element(document.getElementsByClassName('grid')[0]).css('height',  ($scope.pageSize*30)+65 + 'px');

        //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    
      });
      },
      appScopeProvider: { 
        onDblClick : function(row,event) {

          console.log(row.entity);
          
          var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'views/configuration.team.editTeam.html',
            controller: 'ConfigurationEditTeamCtrl',
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

         },
         openCont: function(row,event)
         {
              console.log("context");

          console.log(row.entity);
          if(event.which==3)
          {
            console.log(row.entity);
            row.grid.appScope.dynamicPopover.isOpen=true;
          }

         },

         dynamicPopover:
         {
            content: 'Hello, World!',
            templateUrl: 'view/contextMenu.html',
            title: 'Title',
            isOpen: false
         }
         
      },

      rowTemplate: "<div ng-dblclick=\"grid.appScope.onDblClick(row,$event)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
   };
    
    $http.get(pobCoreServerUrl+"/displayAllteams")
    .then(function (response) {
        $rootScope.gridOptions.data=response.data;
        gridOptionsService.add(0,response.data);
        $scope.newBean=response.data[0];
       
       
    });

}]);

app.controller('ConfigurationEditTeamCtrl', function($scope,$rootScope, $uibModalInstance,editedObject,$q,
    $http,pobCoreServerUrl) {

    $scope.editedObject=editedObject;
    $scope.savedEditedObject=editedObject;
    $scope.radioButtonSelected=false;
    $scope.enableRadioMsg=false;

   

    $scope.checkIfDuplicate=function()
    {
      $http.get(pobCoreServerUrl+"/getTeamForTeamName",{
        params: {
          teamName : $scope.editedObject.teamName
        }
      })
      .then(function (response) {
        $scope.teamBeanOld= response.data;

        if($scope.teamBeanOld.teamId===$scope.editedObject.teamId)
          $scope.isNameNotValid=false;
        else
          $scope.isNameNotValid=true; 
      });
    };

    $scope.edit = function() {

      var obj= $scope.editedObject;

      if(obj.isActive==="1")
        obj.isActive=true;
      else
               obj.isActive=false;
 
      
      // if($scope.radioButtonSelected===true)
      // {
        $http.get(pobCoreServerUrl+"/getTeamForTeamName",{
        params: {
        teamName : $scope.editedObject.teamName
        }
      })
      .then(function (response) {

        $scope.teamBeanOld= response.data;

        if($scope.teamBeanOld.teamId===$scope.editedObject.teamId)
          $scope.isNameNotValid=false;
        else
          $scope.isNameNotValid=true; 

        if($scope.isNameNotValid==false)
      {
        $http.post(pobCoreServerUrl+"/editTeam",obj)
      .then(function(){

        $http.get(pobCoreServerUrl+"/displayAllteams")
        .then(function (response) {
          $rootScope.gridOptions.data=response.data;
          $scope.successMessage="Team edited Successfully.";
          $scope.successMessageCheck=true;    
        },function(){
          $scope.errorMessage="Team could not be edited Successfully.";
          $scope.errorMessageCheck=true;
        });

        $uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');

      });
      }
      else
      $scope.message="Please try some other Team Name";

      });
      // }
      // else
      // {
      //  $scope.enableRadioMsg=true;

      // }


     
     
    };

    $scope.radioButtonSelected=function()
    {
      $scope.radioButtonSelected=true;
      $scope.enableRadioMsg=false;

    };

    $scope.cancel = function() {
      $scope.editedObject=$scope.savedEditedObject;
      $uibModalInstance.close(1);
      $uibModalInstance.dismiss('cancel');
    };

});


app.controller('ConfigurationAddTeamCtrl', function($scope, $uibModalInstance, $rootScope,newObject,

    $http,pobCoreServerUrl) {

	

    $http.get(pobCoreServerUrl+"/getTeamBean")
    .then(function(response) {
      $scope.newObject=response.data;
   
    });

    $scope.checkIfDuplicate=function()
    {
      $http.get(pobCoreServerUrl+"/checkDuplicateName",{
        params: {
          teamName : $scope.newObject.teamName
        }
      })
      .then(function (response) {
        $scope.isNameNotValid= response.data;
       
    });
    }
    
    
    $scope.add = function(obj) {

      obj.teamId=0;

       if($scope.isNameNotValid==false)
      {

        $http.post(pobCoreServerUrl+"/addTeam",obj)
        .then(function(response){

          $http.get(pobCoreServerUrl+"/displayAllteams")
          .then(function(response) {
             	$rootScope.gridOptions.data=response.data;
             	$uibModalInstance.close(1);
          	  $uibModalInstance.dismiss('cancel');
     
          });

          })
          .then(function(){
            
          });
      }
      else
      $scope.message="Please add other Team Name";

    };

    $scope.cancel=function()
    {
    	$uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');
    };

});




