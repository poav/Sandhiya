'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsUpdateoidCtrl
 * @description
 * # OperationsUpdateoidCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
 app.controller('OperationsUpdateoidCtrl',['$scope','$rootScope','$http','$uibModal','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$mdDialog,pobCoreServerUrl) {

  	$scope.gridOptions = {
    paginationPageSizes: [10, 20, 30],
    enablePaginationControls: true,
    columnDefs: [
      { name: 'tempPersonName' ,displayName : 'Name'},
      { name: 'tempPersonEmail' , displayName : 'Email'}
      
    ],
    appScopeProvider: { 
       
        showTabDialog : function(ev,row) {
    	$mdDialog.show({
      	controller: "updateOIDeditCtrl",
      	templateUrl: 'views/operations.updateTempPersonOID.html',
      	parent: angular.element(document.body),
      	targetEvent: ev,
      	size: 'md',
      	locals: {
           editedObject: row.entity
         },
      	clickOutsideToClose:true
    })
        .then( function() {
          $scope.status = 'You cancelled the dialog.';
        });
  	}

    },
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showTabDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
  
  	};
  	
  	$http.get(pobCoreServerUrl+"/displayAllTempPerson")
    .then(function (response) {

        $scope.gridOptions.data=response.data;
	
        console.log(response.data);
    });
}]);

app.controller("updateOIDeditCtrl",function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
  $scope.editedObject=editedObject;

  	

  	$scope.update=function()
  	{
  		var map = {};
  		map.tempPersonId=$scope.editedObject.tempPersonId;
  	  	map.tempPersonOracleID= $scope.tempPersonOracleID;
	
    	$http.post(pobCoreServerUrl+"/updateOracleId",map)
      .then(function () {
        $scope.updateSuccessMsg="Feedback is updated successfully";
        $scope.msgEnabled="true";
      	$mdDialog.hide();
       
      });
 	};

  	$scope.isOperationMenu=true;
  
});
