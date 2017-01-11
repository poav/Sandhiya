'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:DashboardsQuaterlyCtrl
 * @description
 * # DashboardsQuaterlyCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('DashboardsQuaterlyCtrl', ['$scope','$rootScope','$http','$uibModal','$filter','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,$mdDialog,pobCoreServerUrl) {
   
 	$rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    ovider: { 
    
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

 	{ name: 'projectNewBean.clientProjectName', displayName: 'Client Project Name'},
	{ name: 'projectNewBean.clientProjectId', displayName: 'Client ProjectId'},
	{ name: 'projectNewBean.clientTimeTrackingId', displayName: 'Client TimeTracking Id'},
	{ name: 'projectNewBean.costCenter', displayName: 'Cost Center'},
	{ name: 'projectNewBean.projectStartDate', displayName: 'Project Start Date'},
	{ name: 'projectNewBean.projectEndDate', displayName: 'Project End Date'},
	{ name: '0', displayName: 'Project Budget For the Quarter'},
	{ name: 'projectQuarterlyCost', displayName: 'Project Actual For the Quarter'},
	{ name: 'NA', displayName: 'Project Credit For the Quarter'},
	{ name: 'comment', displayName: 'Comments'}
   
    ];

$scope.quarterList=[];
$scope.yearsList=[];
$scope.selectedQuarter={};
$scope.selectedYear={};

	  $http.get(pobCoreServerUrl+"/displayQuarterlyProjectDashBoard",
	  {
	  	params:{
	  		quarter: "Q3",
	  		year: new Date().getFullYear()

	  	}
	  })
    .then(function (response) {
    	$scope.totalCredit=response.data.totalCreditThisQuarter;
    	console.log($scope.totalCredit);
        
    	$rootScope.initialList=response.data.quarterlyProjectActualBudgetList;
        $rootScope.gridOptions.data=$rootScope.initialList;
        $scope.quarterList=response.data.quarterList;
        $scope.yearsList=response.data.yearsList;
               
    });

    $scope.show= function(){

    	$http.get(pobCoreServerUrl+"/displayQuarterlyProjectDashBoard",
		{
		  	params:{
		  		quarter: $scope.selectedQuarter,
		  		year: $scope.selectedYear

		  	}
		})
    	.then(function (response) {
    	$scope.totalCredit=response.data.totalCreditThisQuarter;
    	console.log($scope.totalCredit);
        
    	$rootScope.initialList=response.data.quarterlyProjectActualBudgetList;
        $rootScope.gridOptions.data=$rootScope.initialList;
        $scope.quarterList=response.data.quarterList;
        $scope.yearsList=response.data.yearsList;
               
    });


    };
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
