'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationEmailsCtrl
 * @description
 * # ConfigurationEmailsCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('ConfigurationEmailsCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,pobCoreServerUrl) {

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
      { name: 'name', displayName: 'Email Template Name' }
      ],
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
          if(event.which==3)
          {
            dynamicPopover.isOpen=true;
          }
          var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'views/configuration.emails.editEmailTemplates.html',
            controller: 'ConfigurationEditemailTemplateCtrl',
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

         }   
      },

      rowTemplate: "<div ng-dblclick=\"grid.appScope.onDblClick(row,$event)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
   };
    
    $http.get(pobCoreServerUrl+"/retrieveAllEmailTemplates")
    .then(function (response) {
        $rootScope.gridOptions.data=response.data;
        $scope.newBean=response.data[0];
       
    });

}]);

app.controller('ConfigurationEditemailTemplateCtrl', function($scope,$rootScope, $uibModalInstance,editedObject,$q,
    $http,pobCoreServerUrl) {

	$scope.editedObject=editedObject;

  $scope.selectedAttachments=[];

  $scope.selected=[false,false,false,false,false,false];

  var attachmentsSelected;

  var i=0;
var index;
  for(index=0; index<$scope.editedObject.attachments.length;index++)
  {
    if($scope.editedObject.selectedAttachments[i]===$scope.editedObject.attachments[index])
    {
      $scope.selected[index]=true;
      i++;
    }

  
  }
  $scope.toggle=function(index)
  {
    $scope.selected[index]=!$scope.selected[index];
  };    

  $scope.saveHTML = function(event) {
    $scope.editedObject.content = event.target.innerHTML;
  }

  $scope.cancel=function()
  {
    $uibModalInstance.close(1);
    $uibModalInstance.dismiss('cancel');
  }

	$scope.edit= function()
	{

    var ind=0;
      var i;
      for(i in $scope.selected)
      {
        if($scope.selected[i]===true)
        {
        $scope.selectedAttachments.push($scope.editedObject.attachments[ind]);
      }
      ind++;
      }

      $scope.editedObject.selectedAttachments=$scope.selectedAttachments;

      var map = {};
         
		 $http.post(pobCoreServerUrl+"/updateEmailConfig",$scope.editedObject)
    	.then(function () {

    		$http.get(pobCoreServerUrl+"/retrieveAllEmailTemplates")
	    	.then(function (response) {
		        $rootScope.gridOptions.data=response.data;
	   		});

        $uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');

    	});
	}

});

