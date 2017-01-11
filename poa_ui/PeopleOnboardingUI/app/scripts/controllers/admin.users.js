'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:AdminUsersCtrl
 * @description
 * # AdminUsersCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');

app.filter('roleFilter', function () {
  return function (userRole) {
    var role="";
    	var i=0;
    	var roleIdList=[2,3,5,7,11];
    	var roleNames=["ADMIN","INTERVIEWER","LEAD","OPERATIONS","DEVELOPER"];
    	while(i<5)
    	{
    		if(userRole%roleIdList[i]==0)
    		{
    			role=role+roleNames[i];
    			userRole=userRole/roleIdList[i];

    			if(userRole>1)
    			 role=role+" | ";
    		}
    		i++;
    	}
    	return role;
  };
});

app.controller('AdminUsersCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$filter','pobCoreServerUrl','uiGridConstants',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$filter,pobCoreServerUrl,uiGridConstants) {
  
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
                $scope.filteredList= $filter('filter')( $scope.filteredList , $scope.search);   

            }
            
            $scope.searchLength= $scope.search.length;
        }

        $rootScope.gridOptions.data=$scope.filteredList;

    }, true);

  $scope.selectedUser=null;

 	$scope.gridOptions = {
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    rowHeight: 30,
    gridFooterHeight:30,
    gridHeaderHeight:30,
    enableRowHeaderSelection: false,
    multiSelect :false,
    enableHorizontalScrollbar : uiGridConstants.scrollbars.NEVER, 
    enableVerticalScrollbar : uiGridConstants.scrollbars.NEVER,

    onRegisterApi: function(gridApi) {
      $scope.gridApi = gridApi;

      $scope.pageSize=10;

        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        $scope.pageNumber = newPage;
        $scope.pageSize = pageSize;

        $scope.totalLength=$scope.gridOptions.data.length;

        if($scope.totalLength < newPage*pageSize)
        $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;

        angular.element(document.getElementsByClassName('grid')[0]).css('height',  ($scope.pageSize*30)+65 + 'px');

        //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    
          $scope.successMessageCheck=false  ;
          $scope.errorMessageCheck=false  ;
          $scope.userRolesModifyCheck=false;

        });

       gridApi.selection.on.rowSelectionChanged($scope,function(row){
        
        if(row.isSelected){

          $scope.selectedUser=row.entity;
          var userRole=$scope.selectedUser.userRole;
          setCheckBoxes(userRole);

          $scope.successMessageCheck=false  ;
          $scope.errorMessageCheck=false  ;
          $scope.userRolesModifyCheck=false;


        }
        else
        {
          $scope.selectedUser=null;
          $scope.successMessageCheck=false  ;
          $scope.errorMessageCheck=false  ;
          $scope.userRolesModifyCheck=false;
        }


        console.log($scope.selectedUser);
      });
   },
    columnDefs : [
     { name: 'personOracleId', displayName: 'Sapient Id' },
     { name: 'personDetails.name', displayName: 'Name'},
     { name: 'personDetails.title', displayName: 'Career Stage'},
     { name: 'userRole', displayName: 'Role', cellFilter: 'roleFilter:this'}
     ]

  };

	   $http.get(pobCoreServerUrl+"/displayAllUsers")
    .then(function (response) {
    	
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $scope.filteredList=gridOptionsService.get(0);
        $scope.initialList=gridOptionsService.get(0);
       
    });

 	

  var setCheckBoxes=function (userRole) {
    var role="";
      var i=0;
      var roleIdList=[2,3,5,7,11];
      var roleNames=["ADMIN","INTERVIEWER","LEAD","OPERATIONS","DEVELOPER"];
      while(i<5)
      {
        if(userRole%roleIdList[i]==0)
        {
          $scope.selected[i]=true;
          role=role+roleNames[i];
          userRole=userRole/roleIdList[i];

          if(userRole>1)
           role=role+" | ";
        }
        else
        {
          $scope.selected[i]=false;
        }
        i++;
      }
      return role;
  };
  $scope.deActivate=function()
  {
    $scope.successMessageCheck=false;
      $scope.errorMessageCheck=false;

      if($scope.selectedUser!=null)
      {

        $http.post(pobCoreServerUrl+"/deactivateUser",$scope.selectedUser)
        .then(function()
        {
          $scope.successMessage="Roles of " + $scope.selectedUser.personDetails.name +" is deactivated Successfully.";
          $scope.successMessageCheck=true;
        },function()
        {
            $scope.errorMessage="Roles of " +$scope.selectedUser.personDetails.name +" could not be deactivated.";
            $scope.errorMessageCheck=true;
        });

      }
      else
      {
        $scope.errorMessage="No User is Selected. Please select User";
        $scope.errorMessageCheck=true;

      }

             /*$http.get("http://localhost:8080/retrieveAllDls")
          .then(function (response) {
               $rootScope.gridOptions.data=response.data;      
          });*/
  };

  $scope.delete=function()
  {
      $scope.successMessageCheck=false;
      $scope.errorMessageCheck=false;

    
      if($scope.selectedUser!=null)
      {
            $http.post(pobCoreServerUrl+"/deleteUser",$scope.selectedUser)
            .then(function()
            {
                $scope.successMessage=$scope.selectedUser.personDetails.name+" is deleted Successfully.";
                $scope.successMessageCheck=true;
            },function()
            {
                $scope.errorMessage=$scope.selectedUser.personDetails.name +" could not be deleted.";
                $scope.errorMessageCheck=true;
            });
      }
      else
      {
        $scope.errorMessage="No User is Selected. Please select User";
        $scope.errorMessageCheck=true;

      }


       

             /*$http.get("http://localhost:8080/retrieveAllDls")
          .then(function (response) {
               $rootScope.gridOptions.data=response.data;      
          });*/
  };

  
  


  $scope.detailsListToBeSubmitted=[];



  $scope.selected=[false,false,false,false,false];
  $scope.toggle=function(index)
  {
    $scope.selected[index]=!$scope.selected[index];
    $scope.successMessageCheck=false;
    $scope.errorMessageCheck=false;
  };    

  

    $scope.roleNames=["ADMIN","INTERVIEWER","LEAD","OPERATIONS","DEVELOPER"];
    $scope.userRolesModifyCheck=false;


//     var i=0;
// var index;
//   for(index=0; index<$scope.editedObject.attachments.length;index++)
//   {
//     if($scope.editedObject.selectedAttachments[i]===$scope.editedObject.attachments[index])
//     {
//       $scope.selected[index]=true;
//       i++;
//     }
      

  $scope.modify=function()
  {

      $scope.successMessageCheck=false;
      $scope.errorMessageCheck=false;

   

      if($scope.selectedUser!=null)
      {
        
         if($scope.userRolesModifyCheck===true)
    {
      $scope.detailsListToBeSubmitted=[];
      var ind=0;
      var i;
      for(i in $scope.selected)
      {
        if($scope.selected[i]===true)
        {
        $scope.detailsListToBeSubmitted.push($scope.roleNames[ind]);
        }
       ind++;
      }
      var objectData={
        userRolesList: $scope.detailsListToBeSubmitted,
        currentUserOID: $scope.selectedUser.personOracleId
      }
      console.log($scope.detailsListToBeSubmitted);
      $http.post(pobCoreServerUrl+"/modifyUserRole",objectData)
      .then(function()
        {
          $scope.successMessage="Roles of " +$scope.selectedUser.personDetails.name +" is modified Successfully.";
          $scope.successMessageCheck=true;
        },function()
        {
          $scope.errorMessage="Roles could not be modified Successfully.";
          $scope.errorMessageCheck=true;

        });
      $scope.userRolesModifyCheck=false;
      $scope.selected=[false,false,false,false,false];
        //setCheckBoxes(userRole);
    }
    else
    {
      var userRole=$scope.selectedUser.userRole;
        setCheckBoxes(userRole);

        $scope.userRolesModifyCheck=true;

    }

      }
      else
      {
        $scope.errorMessage="No User is Selected. Please select User";
        $scope.errorMessageCheck=true;

      }

        
  };




}]);
