'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsPeopleCtrl
 * @description
 * # OperationsPeopleCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('OperationsPeopleCtrl', ['$scope','$rootScope','$http','$uibModal','$filter','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,$mdDialog,pobCoreServerUrl) {

 	$rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    appScopeProvider: { 
        onDblClick : function(row) 
        {

           console.log(row.entity.userRole);

           	var modalInstance = $uibModal.open({
      		animation: $scope.animationsEnabled,
      		templateUrl: 'views/configuration.team.editTeam.html',
      		controller: 'ConfigurationEditTeamCtrl',
      		size: 'md',
      		resolve:{
        				editedObject: function () {
          				return row.entity;
        			}
      		}
    		});

    		modalInstance.result.then(function (selectedItem) {
      		$scope.selected = selectedItem;
    		});

        },
        showEditDialog : function(ev,row) {

      $mdDialog.show({
        controller: "PeopleEditCtrl",
        templateUrl: 'views/operations.peopleInfo.editPeopleInfo.html',
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
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showEditDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
 };

 	$rootScope.gridOptions.columnDefs = [
    { name: 'person.personOracleId', displayName: 'Sapient Id' },
    { name: 'personName', displayName: 'Name'},
    { name: 'personEmail', displayName: 'Email'},
    { name: 'person.personDetails.title', displayName: 'Career Stage'},
    { name: 'position.level', displayName: 'Level'},
    { name: 'position.domain', displayName: 'Domain'},
    { name: 'location.city', displayName: 'Location'},
    { name: 'supervisorName', displayName: 'Supervisor'},
    { name: 'dlNames', displayName: 'Dl to be added in'}   
    
    ];

	$http.get(pobCoreServerUrl+"/displayAllResourcesToBeOnBoarded")
    .then(function (response) {
    	$rootScope.initialList=response.data;
      $rootScope.gridOptions.data=$rootScope.initialList;
      $scope.newBean=response.data[0];
       
    });

    
$scope.search="";
 	$scope.$watch('search', function() {
        // do something here
     
        if($scope.search===null ||$scope.search==="")
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

  app.controller('PeopleEditCtrl',function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
    $scope.editedObject=editedObject;

    $scope.searchByName="";
    $scope.$watch('searchByName', function() {
        // do something here
     
       if($scope.searchByName.length>3)
       {
          $http.get(pobCoreServerUrl+"/retrievePersonByName",
          {
              params: {
                  name: $scope.searchByName
              }
          })
          .then(function (response) {

              $scope.PersonNameList=response.data;
              
          });
          
        }

    }, true);

    $http.get(pobCoreServerUrl+"/retrieveAllLocations")
    .then(function (response) {
       $scope.locationList=response.data;
       
    });

    $scope.getPersonNames=function(name)
    {
      
      $http.post(pobCoreServerUrl+"/displayPersonBeanForName",name)
      .then(function(response){

        $scope.personBeanList= response.data;
      });

      return $scope.personBeanList;
    };

    $scope.updatePSD= function(item,model,label,event)
    {
      console.log(item);
      $scope.editedObject.person.supervisorNtId=item.username;
      
    }

    $scope.edit=function(obj)
    {
      $http.post(pobCoreServerUrl+"/editPersonInfo",obj)
      .then(function(){

        $scope.updateSuccessMsg="Feedback is updated successfully.";
        $scope.msgEnabled="true";    

        $mdDialog.hide($scope.updateSuccessMsg,$scope.msgEnabled);
       });

    };

    console.log(editedObject); 


});
