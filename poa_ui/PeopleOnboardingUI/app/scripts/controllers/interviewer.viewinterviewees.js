'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:InterviewerViewintervieweesCtrl
 * @description
 * # InterviewerViewintervieweesCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
	/*app.service('serverSidePaginationService',function($http){

	this.func= function(url,config)
	{
		var array=[];

    $http.get(url,config)
    .then(function (response) {
        array[0]=response.data.list;
        array[1]=response.data.totalListSize;
 
    });

    var getTotalCount = function()
    {
    	return array[1];
    }

    var getData= function()
    {
    	return array[0];
    }

    return {

      getData: getData,
      getTotalCount: getTotalCount
    };
	};	
    
  });*/


  app.controller('InterviewerViewintervieweesCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$mdDialog,pobCoreServerUrl) {
  
    var map = new Map(); 
  	map.set("name", "suraj");
    $scope.selectedIntervieweeRow=[];
    $scope.initiateOnBoardingErrorMsgValidator=false;

  	
    $scope.personScreeningIdSelectedList=[];

    $scope.getAsEmail=function()
    {
          $scope.initiateOnBoardingErrorMsgValidator=false;

      console.log($scope.personScreeningIdSelectedList);
      

      $http.post(pobCoreServerUrl+"/getAsEmail",$scope.personScreeningIdSelectedList);
    };

    $scope.notifyStaffing=function()
    {
          $scope.initiateOnBoardingErrorMsgValidator=false;

      console.log($scope.personScreeningIdSelectedList);
      $http.post(pobCoreServerUrl+"/notifyStaffing",$scope.personScreeningIdSelectedList);

    };

     $scope.initiateOnboarding=function()
    {
      if($scope.personScreeningIdSelectedList.length>1)
      {
        $scope.initiateOnBoardingErrorMsg="Please select only one Canditate for initiating Onboarding";
        $scope.initiateOnBoardingErrorMsgValidator=true;
      }
      else
      {
        var modalInstance = $uibModal.open({
        animation: $scope.animationsEnabled,
        templateUrl: 'views/operations.interviewees.onBoardingDetails.html',
        controller: 'InitiateOnBoardingCntrl',
        size: 'lg',
        resolve: {
        editedObject: function () {
          return $scope.selectedIntervieweeRow;
        }
       }
      });

      modalInstance.result.then(function (selectedItem) {
        $scope.selected = selectedItem;
      });
      }
      

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
      { name: 'personBean.personDetails.name' ,displayName : 'Name'},
      { name: 'personBean.personOracleId' , displayName : 'Oracle ID'},
      { name: 'personBean.position.positionName' , displayName : 'Applied for'},
      { name: 'stringScreeningStartDate' , displayName : 'start Date'},
      { name: 'stringScreeningEndDate' , displayName : 'end Date'},
      { name: 'personBean.location.city' , displayName : 'Location'},
      { name: 'statusBean.statusName' , displayName : 'Status'},
      { name: 'statusBean.resultName' , displayName : 'Result'},
      { name: 'projectBean.projectName' , displayName : 'Sapient Project Name'},
      { name: 'personBean.position.domain' , displayName : 'Domain'},
      { name: 'feedback' , displayName : 'Comments', 
      cellTooltip: function( row, col ) {
          return  row.entity.feedback ;
        }},
      { name: 'personBean.isContractor' , displayName : 'is Contractor'},

    ],

    onRegisterApi: function(gridApi) {
      $scope.gridApi = gridApi;

       gridApi.selection.on.rowSelectionChanged($scope,function(row){

        if(row.isSelected)
        {
          $scope.selectedIntervieweeRow=row.entity;

          $scope.personScreeningIdSelectedList.push(row.entity.personScreeningId);
        }
        else
        {
          

          $scope.personScreeningIdSelectedList.pop(row.entity.personScreeningId);
          if($scope.personScreeningIdSelectedList.length<2)
            $scope.initiateOnBoardingErrorMsgValidator=false;
        }

        console.log($scope.personScreeningIdSelectedList);
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

        $http.get(pobCoreServerUrl+"/displayAllInterviewee",{
        	params: {
            start: ($scope.paginationOptions.pageNumber-1)*$scope.paginationOptions.pageSize,
            rows: $scope.paginationOptions.pageSize,
            colNum: i,
            searchKey: "",
            sortDirection: $scope.paginationOptions.sort
        	}
     	})
    	.then(function (response) {

        $scope.gridOptions.data=response.data.list;
		$scope.gridOptions.totalItems=response.data.totalListSize;
    	});
        
      });
      
      $scope.gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        $scope.paginationOptions.pageNumber = newPage;
        $scope.paginationOptions.pageSize = pageSize;  	
        var sortDirection=$scope.paginationOptions.sort;

          $http.get(pobCoreServerUrl+"/displayAllInterviewee",{
        	params: {
            start: ($scope.paginationOptions.pageNumber-1)*$scope.paginationOptions.pageSize,
            rows: $scope.paginationOptions.pageSize,
            sortDirection: sortDirection,
            searchKey: "",
            colNum: $scope.paginationOptions.colNum
            
            
        	}
     	})
    	.then(function (response) {

       $scope.gridOptions.data=response.data.list;
		$scope.gridOptions.totalItems=response.data.totalListSize;
    	});

      });


    },
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
      	controller: "screeningDetailsCtrl",
      	templateUrl: 'views/operations.interviewees.screeningDetails.html',
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
    rowTemplate: "<div ng-dblclick=\"grid.appScope.showTabDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
  
  	};
  	
  	$http.get(pobCoreServerUrl+"/displayAllInterviewee",
 		{
        params: {
            start: $scope.paginationOptions.pageNumber,
            rows: $scope.paginationOptions.pageSize,
            colNum: $scope.paginationOptions.colNum,
            searchKey: "",
            sortDirection: "asc"
        }
     })
    .then(function (response) {

        $scope.gridOptions.data=response.data.list;
		    $scope.gridOptions.totalItems=response.data.totalListSize;
        console.log(response.data);
    });
}]);

app.controller("screeningDetailsCtrl",function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
  $scope.editedObject=editedObject;
  //$scope.user.name="Suraj Rawat";
  $scope.activateProjectName=false;

  console.log($scope.editedObject.personScreeningId);

   $http.get(pobCoreServerUrl+"/retrieveAllTeamNames")
   .then(function(response){
    $scope.teamList=response.data;
   });

  $scope.update=function()
  {
    $http.post(pobCoreServerUrl+"/updateScreeningDetails",$scope.editedObject)
      .then(function (response) {
        $scope.updateSuccessMsg="Feedback is updated successfully.";
        $scope.msgEnabled="true";

      $mdDialog.hide();
       
      });
  };

  $scope.getProjectList=function(teamName)
    {
      $http.post(pobCoreServerUrl+"/retrieveAllProjectBeansForTeam",teamName).then(function(response) {
          $scope.projectNames = response.data;
          $scope.activateProjectName=true;
      });

    }

  
/*
    $http.get("http://localhost:8080/getScoresList",{
      params:{
        personScreeningId:$scope.editedObject.intervieweeId
      }
    })
      .then(function (response) {

       $scope.scoreGridOptions.data=response.data.scoreList;
            console.log(response.data);
      });
*/
      /*$http.get("http://localhost:8080/getInterviewee",{
      params:{
        personScreeningId:$scope.editedObject.personScreeningId
      }
    })
      .then(function (response) {

      $scope.interviewee=response.data;
      $scope.scoreGridOptions.data=response.data.scoreList;
      console.log(response.data);
      });*/

    

    var arr=[];
    arr=$scope.editedObject.stringScreeningStartDate.split("/");
    //$scope.editedObject.screeningStartDate= new Date(arr[2],arr[0],arr[1]);
    $scope.editedObject.screeningStartDate= new Date($scope.editedObject.screeningStartDate);

    arr=$scope.editedObject.stringScreeningEndDate.split("/");
    //$scope.editedObject.screeningEndDate= new Date(arr[2],arr[0],arr[1]);
    $scope.editedObject.screeningEndDate= new Date($scope.editedObject.screeningEndDate);

    $scope.scoreGridOptions = {
    
    paginationPageSizes: [20,30,50],
    enablePaginationControls: true,
    enableCellEditOnFocus : true,
    columnDefs: [
      { name: 'topicBean.topicName' ,displayName : 'Topic', enableCellEdit: false},
      { name: 'score' , displayName : 'Score'},
      { name: 'comments' , displayName : 'Comments'}
    ]
  };

    $scope.scoreGridOptions.data=$scope.editedObject.scoreList;

});

app.controller("InitiateOnBoardingCntrl",function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
  $scope.editedObject=editedObject;
  $scope.personStaffingBean={};

  $scope.rateTypes = ['MONTHLY'];
  $scope.rateCategories = ['Offshore', 'Onshore'];
  $scope.options=['Yes','No'];

  $http.get(pobCoreServerUrl+"/retrieveAllProjectBeans").then(function(response) {
        $scope.projectNames = response.data;
  });

  $http.get(pobCoreServerUrl+"/retrieveLocations").then(function(response) {
        $scope.rateLocations = response.data;
  });


  $scope.dls=[];

  $http.get(pobCoreServerUrl+"/retrieveDlForLocation",
    {
      params: {
        projectId: editedObject.projectBean.id,
        locationId: editedObject.personBean.location.locationId
        
      }
    }).then(function(response) {
        $scope.dls = response.data;

  });

  $scope.getDls=function()
  {
    $http.get(pobCoreServerUrl+"/retrieveDlForLocation",
    {
      params: {
        
        projectId: $scope.personStaffingBean.project.id,
        locationId: $scope.personStaffingBean.location.locationId
      }
    }).then(function(response) {
        $scope.dls = response.data;

  });
  }


  $scope.dlsListToBeSubmitted="";



  $scope.selected=[false,false,false,false];

  $scope.toggle=function(index)
  {
    $scope.selected[index]=!$scope.selected[index];
  };    


  $http.get(pobCoreServerUrl+"/getPersonStaffingBean").then(function(response) {
        $scope.personStaffingBean = response.data;
        $scope.personStaffingBean.project=editedObject.projectBean;
        $scope.personStaffingBean.location=editedObject.personBean.location;
        $scope.personStaffingBean.staffingStartDate= new Date();
        $scope.personStaffingBean.rateBean.rateType=$scope.rateTypes[0];
        $scope.personStaffingBean.rateBean.rateCategory=$scope.rateCategories[0];
        $http.get(pobCoreServerUrl+"/findRate",{

        params: {

          locationName: editedObject.personBean.location.city,
          positionName: editedObject.personBean.position.positionName,
          domainName: editedObject.category,
          rateCategory: $scope.rateCategories[0],
          rateType: $scope.rateTypes[0]

        }

      }
        ).then(function(response) {
    $scope.personStaffingBean.rateBean.rate=response.data.rate;        


  });


  });

  $http.get(pobCoreServerUrl+"/getOnboardingCheckListBean").then(function(response) {
        $scope.onBoardingCheckListBean = response.data;
        


  });

  $scope.getRate=function()
  {
    var location=$scope.personStaffingBean.location.city;
    var rateCategory=$scope.personStaffingBean.rateBean.rateCategory;
    var rateType=$scope.personStaffingBean.rateBean.rateType;

    if(location!=null)
    {
      $scope.getDls();
    }

    if(location!=null && rateCategory!=null && rateType!=null)
    {
      $http.get(pobCoreServerUrl+"/findRate",{

        params: {

          locationName: location,
          positionName: $scope.editedObject.personBean.position.positionName,
          domainName: $scope.editedObject.category,
          rateCategory: rateCategory,
          rateType: rateType

        }

      }
        ).then(function(response) {
          if(response.data==="")
          {
                $scope.personStaffingBean.rateBean.rate=0;
                $scope.rateNotFoundWarning="Rate Not Found";        

          }
          else
          {
                $scope.personStaffingBean.rateBean.rate=response.data.rate;        

          }


  });

    }
  }

 
  var map={};
  $scope.initiateOnboarding=function()
  {
    console.log($scope.personStaffingBean);

    $scope.personStaffingBean.staffingStartDate= new Date($scope.personStaffingBean.staffingStartDate.getTime());
    $scope.personStaffingBean.startDate= $scope.personStaffingBean.staffingStartDate.toLocaleDateString();
    $scope.personStaffingBean.position=$scope.editedObject.personBean.position;
    $scope.onBoardingCheckListBean.personStaffingBean=$scope.personStaffingBean;
    $scope.onBoardingCheckListBean.personScreeningDetails=$scope.editedObject;
    $scope.onBoardingCheckListBean.initiator.personNtId=$rootScope.currentUser.username;

    
    console.log($scope.onBoardingCheckListBean);

    $scope.dlsListToBeSubmitted="";
      var ind=0;
      var i;
      for(i in $scope.selected)
      {
        if($scope.selected[i]===true)
        {
        $scope.dlsListToBeSubmitted+=$scope.dls[ind].email;
        $scope.dlsListToBeSubmitted+=",";
        }
       ind++;
      }

      $scope.onBoardingCheckListBean.personStaffingBean.dlNames=$scope.dlsListToBeSubmitted;
      if($scope.onBoardingCheckListBean.hasPreviouslyWorkedForClient==="Yes")
      {
        $scope.onBoardingCheckListBean.hasPreviouslyWorkedForClient=true;
      }
      else
      {
        $scope.onBoardingCheckListBean.hasPreviouslyWorkedForClient=false;
      }

      if($scope.onBoardingCheckListBean.hasSapientLaptop==="Yes")
      {
        $scope.onBoardingCheckListBean.hasSapientLaptop=true;
      }
      else
      {
        $scope.onBoardingCheckListBean.hasSapientLaptop=false;
      }





var map= {

  onboardingCheckListBean: $scope.onBoardingCheckListBean,
  personScreeningDetailsBean: $scope.editedObject,
  personStaffingBean:$scope.personStaffingBean,
  currentUser: $rootScope.currentUser,
  emailDlArray: $scope.dlsListToBeSubmitted
};
console.log(map);
    $http.post(pobCoreServerUrl+"/initiateOnboarding",$scope.onBoardingCheckListBean)
    .then(function(response)
      {
        $scope.selected=[false,false,false,false];
        $mdDialog.hide();
      });


  };

  $scope.cancel=function()
  {
    $mdDialog.hide();
  };
  
  

});