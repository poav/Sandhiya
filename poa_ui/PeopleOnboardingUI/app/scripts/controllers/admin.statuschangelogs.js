'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:AdminStatuschangelogsCtrl
 * @description
 * # AdminStatuschangelogsCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('AdminStatuschangelogsCtrl',['$scope','$rootScope','$http','$uibModal','$filter','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,$mdDialog,pobCoreServerUrl) {
   
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

  $rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30]
 };

  $rootScope.gridOptions.columnDefs = [

  { name: 'candidatePSD.personBean.personName', displayName: 'Candidate Name'},
  { name: 'candidatePSD.personBean.personOracleId', displayName: 'Candidate OracleId'},
  { name: 'statusChangedFrom.statusName', displayName: 'Status Changed From'},
  { name: 'statusChangedTo.statusName', displayName: 'Status Changed To'},
  { name: 'statusChangeDate', displayName: 'Status changed Date'},
  { name: 'operator.personOracleId', displayName: 'Operator Oracle Id'},
  { name: 'operator.personName', displayName: 'Operator Name'}

    ];

  

  $http.get(pobCoreServerUrl+"/displayStatusChangeLog")
    .then(function (response) {
      
        
      $rootScope.gridOptions.data=response.data;
      gridOptionsService.add(0,response.data);
      $scope.filteredList=gridOptionsService.get(0);
               
    });

    


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
