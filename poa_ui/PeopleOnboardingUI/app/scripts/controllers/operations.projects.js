'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsProjectsCtrl
 * @description
 * # OperationsProjectsCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('OperationsProjectsCtrl', ['$scope','$rootScope','$http','$uibModal','$filter','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,$mdDialog,pobCoreServerUrl) {

    $scope.onboardingIdList=[];

    $scope.sendPSIDtoCandidate= function(){
      $http.post(pobCoreServerUrl+"/sendPSIDToCandidate",$scope.onboardingIdList)
      .then(function(){

        $scope.updateSuccessMsg="PSID sent to Candidate";
        $scope.updateMsgEnabled="true";   
    
      });
    };

 	$rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    onRegisterApi: function(gridApi) {
      $scope.gridApi = gridApi;

       gridApi.selection.on.rowSelectionChanged($scope,function(row){
        if(row.isSelected)
          $scope.onboardingIdList.push(row.entity.personStaffingBean.personStaffingId);
        else
          $scope.onboardingIdList.pop(row.entity.personStaffingBean.personStaffingId);

        console.log(onboardingIdList);
      });

     },
    appScopeProvider: { 
    
      showDialog : function(ev,row) {

      $mdDialog.show({
        templateUrl: 'views/operations.projects.editProject.html',
        controller: 'ProjectInfoEditCtrl',
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
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
  
 };

 	$rootScope.gridOptions.columnDefs = [
 	  { name: 'personStaffingBean.person.personName', displayName: 'Name'},
    { name: 'person.personOracleId', displayName: 'Sapient Id' },  
    { name: 'personStaffingBean.project.projectName', displayName: 'Sapient Project Name'},
    { name: 'personStaffingBean.project.projectId', displayName: 'Sapient Project Id'},
    { name: 'personStaffingBean.project.clientProjectName', displayName: 'Client Project Name'},
    { name: 'personStaffingBean.project.clientProjectId', displayName: 'Client Project Id'},
    { name: 'person.personClientId', displayName: 'Person Client Id'},
    { name: 'personStaffingBean.startDate', displayName: 'Start Date(mm/dd/yy)'},
    { name: 'personStaffingBean.project.projectEndDate', displayName: 'Client Project End Date(mm/dd/yy)'},
    { name: 'accountTenure', displayName: 'Account Tenure'},
    { name: 'person.isActive', displayName: 'Active'}   
  
    
    ];

	  $http.get(pobCoreServerUrl+"/displayProjectInfo")
    .then(function (response) {
    	 $rootScope.initialList=response.data;
        $rootScope.gridOptions.data=$rootScope.initialList;
        
       
    });

    
$scope.search="";
 	$scope.$watch('search', function() {
        // do something here
     
        if($scope.search===null || $scope.search==="")
        {
       		$rootScope.filteredList=$rootScope.initialList;
             $scope.searchLength=0;
        }
        else
        {
            if($scope.searchLength > $scope.search.length)
            {
                $rootScope.filteredList=$filter('filter')($rootScope.initialList , $scope.search);
                
            }
            
            $rootScope.filteredList=  $filter('filter')( $rootScope.filteredList , $scope.search);
			
             
             $scope.searchLength= $scope.search.length;
        }

        $rootScope.gridOptions.data=$rootScope.filteredList;

    }, true);

  
  }]);

app.controller('ProjectInfoEditCtrl',function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
  
  $scope.editedObject=editedObject;
  console.log($scope.editedObject.dateOnboardingInitiatedDate);

  
  if($scope.editedObject.dateOnboardingInitiatedDate!=null)
      $scope.editedObject.dateOnboardingInitiatedDate = new Date($scope.editedObject.dateOnboardingInitiatedDate);

    $scope.update=function()
    {
      
      $http.post(pobCoreServerUrl+"/editProjectInfo",$scope.editedObject)
      .then(function(){

        $scope.updateSuccessMsg="Project Info is updated successfully.";
        $scope.updateMsgEnabled="true";
      

        $mdDialog.hide($scope.updateSuccessMsg,$scope.updateMsgEnabled);
    
      });


    };

    $scope.cancel=function()
    {
      $mdDialog.hide("","");
    };
    

  console.log(editedObject); 


});