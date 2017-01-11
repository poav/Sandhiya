'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:DashboardsAnnualCtrl
 * @description
 * # DashboardsAnnualCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('DashboardsAnnualCtrl',['$scope','$rootScope','$http','$uibModal','$filter','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,$mdDialog,pobCoreServerUrl) {
   
 	$rootScope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30]
    
 };

 	$rootScope.gridOptions.columnDefs = [

 	{ name: 'projectNewBean.clientProjectName', displayName: 'Client Project Name'},
	{ name: 'projectNewBean.clientProjectId', displayName: 'Client ProjectId'},
	{ name: 'projectNewBean.clientTimeTrackingId', displayName: 'Client TimeTracking Id'},
	{ name: 'projectNewBean.costCenter', displayName: 'Cost Center'},
	{ name: 'projectNewBean.projectStartDate', displayName: 'Project Start Date'},
	{ name: 'projectNewBean.projectEndDate', displayName: 'Project End Date'},
	{ name: '0', displayName: 'Project Budget For the Year'},
	{ name: 'projectAnnualCost', displayName: 'Project Actual For the Year'},
	{ name: 'NA', displayName: 'Project Credit For the Year'},
	{ name: 'comment', displayName: 'Comments'}
   
    ];

	$scope.yearsList=[];
	$scope.selectedYear={};

	$http.get(pobCoreServerUrl+"/displayAnnualProjectDashBoard",
   {
	  	params:{
	  		year: new Date().getFullYear()

	}
	
	})
    .then(function (response) {
    	$scope.totalCredit=response.data.totalCreditThisQuarter;
    	console.log($scope.totalCredit);
        
    	$rootScope.initialList=response.data.annualProjectActualBudgetList;
        $rootScope.gridOptions.data=$rootScope.initialList;
        $scope.yearsList=response.data.yearsList;
               
    });

    $scope.show= function(){

    	$http.get(pobCoreServerUrl+"/displayAnnualProjectDashBoard",
		{
		  	params:{
		  		year: $scope.selectedYear

		  	}
		})
    	.then(function (response) {
    	$scope.totalCredit=response.data.totalCreditThisQuarter;
    	console.log($scope.totalCredit);
        
    	$rootScope.initialList=response.data.annualProjectActualBudgetList;
        $rootScope.gridOptions.data=$rootScope.initialList;
        $scope.yearsList=response.data.yearsList;
               
    });


    };

     $scope.export= function(){

      //$http.post(pobCoreServerUrl+"/annualProjectExport",$scope.selectedYear);
      location.href(pobCoreServerUrl+"/annualProjectExport",$scope.selectedYear);


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
