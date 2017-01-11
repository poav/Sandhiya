'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:DashboardsProjectsCtrl
 * @description
 * # DashboardsProjectsCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('DashboardsProjectsCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$mdDialog,pobCoreServerUrl) {
  
    $scope.personScreeningIdSelectedList=[];
    $scope.projectList=[];
    $scope.yearsList=[2015,2016,2017,2018,2019,2020];
	  $scope.selectedProject={};
	  $scope.selectedYear=$scope.yearsList[0];
	  $scope.budgetSummaryBean={};

    $http.get(pobCoreServerUrl+"/retrieveAllProjectNames")
    .then(function(response){
    	$scope.projectList=response.data;	
    	$scope.selectedProject=$scope.projectList[0];


		$http.get(pobCoreServerUrl+"/getBudgetSummary",
 		{
        params: {

            year: 2015,
            projectName: $scope.selectedProject
        }
     })
    .then(function (response) {

        $scope.budgetSummaryBean=response.data;
		
    });

    $http.get(pobCoreServerUrl+"/getActualSummary",
 		{
        params: {

            year: 2015,
            projectName: $scope.selectedProject
        }
     })
    .then(function (response) {

        $scope.actualSummaryBean=response.data;
		
    });

 
    $http.get(pobCoreServerUrl+"/displayProjectDashBoard",
 		{
        params: {
            start: $scope.paginationOptions.pageNumber,
            rows: $scope.paginationOptions.pageSize,
            colNum: $scope.paginationOptions.colNum,
            searchKey: "",
            sortDirection: "asc",
            year: 2015,
            projectName: $scope.selectedProject
        }
     })
    .then(function (response) {

        $scope.gridOptions.data=response.data.projectSummaryBeanList;
		$scope.gridOptions.totalItems=response.data.count;
        console.log(response.data);
    });

		
    });
	

    $scope.show=function()
    {
    	$http.get(pobCoreServerUrl+"/displayProjectDashBoard",{
        	params: {
            start: ($scope.paginationOptions.pageNumber-1)*$scope.paginationOptions.pageSize,
            rows: $scope.paginationOptions.pageSize,
            colNum: 0,
            searchKey: "",
            sortDirection: $scope.paginationOptions.sort,
            year: $scope.selectedYear,
            projectName: $scope.selectedProject
        	}
     	})
    	.then(function (response) {

        $scope.gridOptions.data=response.data.projectSummaryBeanList;
		    $scope.gridOptions.totalItems=response.data.count;
    	});
      
    };

    $scope.myFunction= function()
    {
      alert("You right-clicked inside the div!");

    };

    $scope.updateMonthlyProjectDashBoard=function()
    {
      $http.post(pobCoreServerUrl+"/editMonthlyProjectBudget",$scope.budgetSummaryBean)
      .then(function()
      {
          $scope.successMsg="Monthly Project DashBoard updated successfully";
      });
    };


     $scope.export=function()
    {
      

    };

  	$scope.paginationOptions = {
	    pageNumber: 1,
	    pageSize: 10,
	    sort: "none",
	    colNum: 0
  	};

  	$scope.gridOptions = {
    enableRowSelection: true,
    enableSelectAll: true,
    paginationPageSizes: [10, 20, 30],
    useExternalPagination: true,
    useExternalSorting: true,
    enablePaginationControls: true,
    columnDefs: [
      { name: 'personStaffingBean.person.personName' ,displayName : 'Person Name'},
      { name: 'personStaffingBean.location.city' ,displayName : 'Location'},
      { name: 'personStaffingBean.allocation' ,displayName : 'Allocation'},
      { name: 'jan.individualMonthlyCost' , displayName : 'Jan'},
      { name: 'feb.individualMonthlyCost', displayName : 'Feb'},
      { name: 'march.individualMonthlyCost' , displayName : 'Mar'},
            { name: 'q1' , displayName : 'Q1'},

      {  name: 'april.individualMonthlyCost' , displayName : 'Apr'},
      {  name: 'may.individualMonthlyCost' , displayName : 'May'},
      {  name: 'june.individualMonthlyCost', displayName : 'Jun'},
            {  name: 'q2', displayName : 'Q2'},

      {  name: 'july	.individualMonthlyCost' , displayName : 'Jul'},
      {  name: 'aug.individualMonthlyCost', displayName : 'Aug'},
      {  name: 'sept.individualMonthlyCost' , displayName : 'Sep'},
            {  name: 'q3' , displayName : 'Q3'},

      {  name: 'oct.individualMonthlyCost' , displayName : 'Oct'},
      {  name: 'nov.individualMonthlyCost' , displayName : 'Nov'},
      {  name: 'dec.individualMonthlyCost' , displayName : 'Dec'},
            {  name: 'q4', displayName : 'Q4'}


    ],

    onRegisterApi: function(gridApi) {
      $scope.gridApi = gridApi;

       gridApi.selection.on.rowSelectionChanged($scope,function(row){
        if(row.isSelected)
        {

        }
          // $scope.personScreeningIdSelectedList.push(row.entity.personScreeningId);
       
          // $scope.personScreeningIdSelectedList.pop(row.entity.personScreeningId);

        // console.log(personScreeningIdSelectedList);

      });
 
      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        var msg = 'rows changed ' + rows.length;
        console.log(msg);
      });

      $scope.gridApi.core.on.sortChanged($scope, function(grid, sortColumns) {
        
        var colArray= $scope.gridOptions.columnDefs;

        var sortedColumn=sortColumns;
        var i = 0;
		
    	var len = colArray.length;
		for (; i < len;i++ ) { 
    		if(colArray[i].displayName===sortedColumn[0].displayName)
      		break;  		
		}

		$scope.paginationOptions.colNum=i;


        if (sortColumns.length == 0) {
          $scope.paginationOptions.sort = null;
        } else {
          $scope.paginationOptions.sort = sortColumns[0].sort.direction;
        }

        $http.get(pobCoreServerUrl+"/displayProjectDashBoard",{
        	params: {
            start: ($scope.paginationOptions.pageNumber-1)*$scope.paginationOptions.pageSize,
            rows: $scope.paginationOptions.pageSize,
            colNum: i,
            searchKey: "",
            sortDirection: $scope.paginationOptions.sort,
            year: 2015,
            projectName: $scope.selectedProject
        	}
     	})
    	.then(function (response) {

      $scope.gridOptions.data=response.data.projectSummaryBeanList;
		  $scope.gridOptions.totalItems=response.data.count;
    	});
        
      });
      
      $scope.gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        $scope.paginationOptions.pageNumber = newPage;
        $scope.paginationOptions.pageSize = pageSize;  	
        var sortDirection=$scope.paginationOptions.sort;

          $http.get(pobCoreServerUrl+"/displayProjectDashBoard",{
        	params: {
            start: ($scope.paginationOptions.pageNumber-1)*$scope.paginationOptions.pageSize,
            rows: $scope.paginationOptions.pageSize,
            sortDirection: sortDirection,
            searchKey: "",
            colNum: $scope.paginationOptions.colNum,
            year: 2015,
            projectName: $scope.selectedProject            
            
        	}
     	})
    	.then(function (response) {

       		$scope.gridOptions.data=response.data.projectSummaryBeanList;
			$scope.gridOptions.totalItems=response.data.count;
    	});

      });


    },
    appScopeProvider: { 
      
    showEditDialog : function(ev,row) {
    	  $mdDialog.show({
      	controller: "EditProjectDashBoardCtrl",
      	templateUrl: 'views/dashboard.editProjectDashBoard.html',
      	parent: angular.element(document.body),
      	targetEvent: ev,
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
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showEditDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
  
  	};
  	
  	
}]);

app.controller("EditProjectDashBoardCtrl",function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
    
    $scope.editedObject=editedObject;

    $scope.locationName=$scope.editedObject.personStaffingBean.location.city;
    $scope.projectName=$scope.editedObject.personStaffingBean.project.projectName;
    $scope.updatedEmailDlList=[];
    $scope.selectedEditOption=0;
    $scope.updatedProjectName={};
    $scope.updatedLocationName={};
    $scope.updatedPositionName={};
    $scope.updatedRateCategory={};
    $scope.updatedRateType={};
    $scope.updatedStaffingStartDate=new Date($scope.editedObject.personStaffingBean.staffingStartDate);
    $scope.updatedStaffingEndDate=new Date($scope.editedObject.personStaffingBean.staffingEndDate);
    $scope.emailDlList =[];
    $scope.selectedDLsList=[];
    $scope.offboardingComments={};


  $scope.selected=[false,false,false,false,false];
  $scope.toggle=function(index)
  {
    $scope.selected[index]=!$scope.selected[index];
  };    

    $http.get(pobCoreServerUrl+"/retrieveAllPositions").then(function(response) {
      $scope.positionList = response.data;
    });

    $http.get(pobCoreServerUrl+"/retrieveLocations").then(function(response) {
        $scope.locationList = response.data;
    });
   

    $scope.update=function()
    {

      var ind=0;
      var i;
      for(i in $scope.selected)
      {
        if($scope.selected[i]===true)
        {
        $scope.selectedDLsList[i]=$scope.emailDlList[ind].email;
        }
        else
        {
          $scope.selectedDLsList[i]="";
        }
      ind++;
      }

      // $scope.updatedStaffingEndDate= new Date($scope.updatedStaffingEndDate);
      //  $scope.updatedStaffingStartDate= new Date($scope.updatedStaffingStartDate);

      //   @RequestParam("assignmentCode") int assignmentCode,
      // @RequestParam("personStaffingId") Integer personStaffingId,
      // @RequestParam("projectNameNew") String projectNameNew,
      // @RequestParam("locationNameNew") String locationNameNew,
      // @RequestParam("positionNameNew") String positionNameNew,
      // @RequestParam("rateTypeNew") String rateTypeNew,
      // @RequestParam("rateCategoryNew") String rateCategoryNew,
      // @RequestParam("dlString") String dlString,
      // @RequestParam("offboardingComments") String offboardingComments,
      // @RequestParam("staffingStartDateNew") Date staffingStartDateNew,
      // @RequestParam("staffingEndDateNew") Date staffingEndDateNew

        $http.get(pobCoreServerUrl+"/editStaffingAssignment",
        {
          params: {
            assignmentCode: $scope.selectedEditOption,
            personStaffingId: $scope.editedObject.personStaffingBean.personStaffingId,
            projectNameNew: $scope.projectName,
            locationNameNew: $scope.locationName,
            positionNameNew:  $scope.updatedPositionName,
            rateTypeNew : $scope.updatedRateType,
            rateCategoryNew : $scope.updatedRateCategory,
            dlStringArray :$scope.selectedDLsList,
            offboardingComments :$scope.offboardingComments,
            staffingStartDateNew : $scope.updatedStaffingStartDate.getTime(),
            staffingEndDateNew : $scope.updatedStaffingEndDate.getTime()
          }
        }).then(function(response) {
            $scope.emailDlList = response.data;
        });

    };

    

    $scope.updateEmailDlListWhenProjectSelected=function(projectName)
    {
        $scope.projectName=projectName;
        $http.get(pobCoreServerUrl+"/getDlForLocationAndProject",
        {
          params: {

            teamName: $scope.projectName,
            locationName: $scope.locationName

          }
        }).then(function(response) {
        $scope.emailDlList = response.data;
    });
    };

    $scope.updateEmailDlListWhenLocationSelected=function(locationName)
    {
        $scope.locationName=locationName;

        $http.get(pobCoreServerUrl+"/getDlForLocationAndProject",
        {
          params: {

            teamName: $scope.projectName,
            locationName: $scope.locationName

          }
        }).then(function(response) {
        $scope.emailDlList = response.data;
    });
    }

    $scope.rateCategoryList = ['Offshore','Onshore'];
    $scope.rateTypeList = ['MONTHLY'];

    
    $http.get(pobCoreServerUrl+"/retrieveAllProjectBeans").then(function(response) {
      $scope.projectList = response.data;
    });


});
