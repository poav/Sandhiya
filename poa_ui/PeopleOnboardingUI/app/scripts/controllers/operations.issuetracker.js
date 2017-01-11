'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsIssuetrackerCtrl
 * @description
 * # OperationsIssuetrackerCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
  app.controller('OperationsIssuetrackerCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$mdDialog,pobCoreServerUrl) {
    

	$scope.add= function(ev) {
      $mdDialog.show({
        controller: "newIssueTrackerCtrl",
        templateUrl: 'views/operations.issuetracker.reportIssue.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        locals: {
           
         },
        clickOutsideToClose:true
    })
        .then(function(saveSuccessMsg,msgEnabled) {
              $scope.saveSuccessMsg = saveSuccessMsg;
              $scope.msgEnabled = msgEnabled;
          $http.get(pobCoreServerUrl+"/retrieveAllFeedBacks")
          .then(function (response) {
              gridOptionsService.add(0,response.data);
              $scope.gridOptions.data=gridOptionsService.get(0);
              $scope.newBean=response.data[0];
       
    });
        });
    };
  

    $rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    appScopeProvider: { 
        onDblClick : function(row) {

           console.log(row.entity);

    
           var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      templateUrl: 'views/operations.interviewees.screeningDetails.html',
      controller: 'ConfigurationEditTeamCtrl',
      size: 'lg',
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
      showTabDialog : function(ev,row) {

      $mdDialog.show({
        controller: "IssueTrackerEditCtrl",
        templateUrl: 'views/operations.issuetracker.updateFedback.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        locals: {
           editedObject: row.entity
         },
        clickOutsideToClose:true
    })
        .then(function(successMsg,msgEnabled) {
          $scope.successMsg = saveSuccessMsg;
          $scope.msgEnabled = msgEnabled;
        });
    }

    },
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showTabDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
  
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
 
    $http.get(pobCoreServerUrl+"/retrieveAllFeedBacks")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $scope.newBean=response.data[0];
       
    });

}]);

app.controller('IssueTrackerEditCtrl',function(editedObject,$scope,$rootScope,$http,pobCoreServerUrl,$mdDialog){
    
    $scope.editedObject=editedObject;
   
$http.get(pobCoreServerUrl+"/getIssueTrackerStatusByName")
    .then(function (response) {
       $scope.statusList=response.data;
       
    });

    $scope.update=function(obj)
    {
       $scope.editedObject.fixedBy=$rootScope.currentUser.name;

      $http.post(pobCoreServerUrl+"/updateIssueTracker",obj)
      .then(function(){

        $scope.updateSuccessMsg="Feedback is updated successfully.";
        $scope.msgEnabled="true";
      

      $mdDialog.hide($scope.updateSuccessMsg,$scope.msgEnabled);
       });

    };

console.log(editedObject); 


});

app.controller('newIssueTrackerCtrl', function($scope,pobCoreServerUrl,$rootScope,

    $http,$mdDialog) {

  $http.get(pobCoreServerUrl+"/getIssueTrackerBean")
    .then(function (response) {
       $scope.newObject=response.data;
       $scope.newObject.oracleId=$rootScope.currentUser.oracleId;
       
    });

	$http.get(pobCoreServerUrl+"/getIssueTrackerTypeByName")
    .then(function (response) {
       $scope.typeList=response.data;
       
    });

    $scope.save=function(obj){

      $http.post(pobCoreServerUrl+"/saveIssueTracker",obj)
      .then(function(){

        

        $scope.saveSuccessMsg="Feedback is reported successfully. It will be updated soon";
        $scope.msgEnabled="true";
      

        $mdDialog.hide($scope.saveSuccessMsg,$scope.msgEnabled);
       });
    };

});
