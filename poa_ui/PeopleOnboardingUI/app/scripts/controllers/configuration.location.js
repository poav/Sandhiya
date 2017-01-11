'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationLocationCtrl
 * @description
 * # ConfigurationLocationCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
  .controller('ConfigurationLocationCtrl', ['$scope','$rootScope','$http','$uibModal','$filter','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,gridOptionsService,$mdDialog,pobCoreServerUrl) {
  
  $scope.search="";

  
  $scope.$watch('search', function() {
        // do something here
      $scope.searchLength=0;
      $rootScope.filteredList=$rootScope.initialList;

        if($scope.search===null ||$scope.search==="")
        {
             $scope.searchLength=0;
        }
        else
        {
            if($scope.searchLength > ($scope.search.length))
            {
                $rootScope.filteredList=$filter('filter')($rootScope.initialList,"$scope.search" );

              // [$rootScope.initialList.email,
              //   $rootScope.initialList.projectBean.teamBean.teamName,
              //   $rootScope.initialList.projectBean.projectName,
              //   $rootScope.initialList.locationBean.city,
              //   $rootScope.initialList.isActive
              // ]
                
            }
            
            $rootScope.filteredList=  $filter('filter')( $rootScope.filteredList , $scope.search);
      
             
             $scope.searchLength= $scope.search.length;
        }

        $scope.gridOptions.data=$rootScope.filteredList;

    }, true);

$scope.activate= function (row,index) {

var obj=row.entity;
obj.locationId=row.entity.locationId;
obj.isActive=1;
$http.post(pobCoreServerUrl+"/changeLocationState",obj)
.then(function(){
  angular.element(document.getElementsByName('activateLocation')[index]).css('background-color',  "#56C50F");
  angular.element(document.getElementsByName('inActivateLocation')[index]).css('background-color',  "grey");

});

};

 $scope.ifLocationInActive=function(rowData)
  {
    if(rowData.isActive===false||rowData.isActive===0)
      return "#D64C4A";
  };

  $scope.ifLocationActive=function(rowData)
  {
    if(rowData.isActive===true||rowData.isActive===1)
      return "#56C50F";
  };

$scope.inactivate= function (row,index) {

var obj=row.entity;
obj.locationId=row.entity.locationId;
obj.isActive=0;
$http.post(pobCoreServerUrl+"/changeLocationState",obj)
.then(function(){
  angular.element(document.getElementsByName('activateLocation')[index]).css('background-color',  "grey");
  angular.element(document.getElementsByName('inActivateLocation')[index]).css('background-color', "#D64C4A");


 
});


};

	$scope.add= function () {

		var modalInstance = $uibModal.open({
    	animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.location.addNewLocation.html',
        controller: 'ConfigurationAddLocationCtrl',
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
    enablePaginationControls: true,
    paginationPageSizes:[10,20,30],
    rowHeight: 30,
    gridFooterHeight:30,
    gridHeaderHeight:30,
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


    });
    }
	};
    
  $scope.gridOptions.columnDefs = [
    { name: 'city', displayName: 'City' ,width: '30%'},
     { name: 'state', displayName: 'State',width: '30%' },
      { name: 'country', displayName: 'Country',width: '30%' },
       { name: 'isActive', displayName: 'IS Active',width: '10%',
       cellTemplate: '<button name="activateLocation" class=\" location-btn location-btn-default\" ng-click=\"grid.appScope.activate(row,rowRenderIndex)\"   style=\" background-color:{{grid.appScope.ifLocationActive(row.entity)}}\; width=50%; \"  ">Active</button><button name="inActivateLocation" class=\" location-btn location-btn-default\" ng-click=\"grid.appScope.inactivate(row,rowRenderIndex)\"  style=\"width=50%; background-color: {{grid.appScope.ifLocationInActive(row.entity)}}\; \" ">InActive</button>'  }
      
  ];
 
    $http.get(pobCoreServerUrl+"/retrieveLocations")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $rootScope.initialList=$scope.gridOptions.data;
        $scope.newBean=response.data[0];

        
        

       
    });

}]);

app.controller('ConfigurationAddLocationCtrl', function($scope, $uibModalInstance, $rootScope,newObject,

    $http,pobCoreServerUrl) {

    $http.get(pobCoreServerUrl+"/getLocationBean")
    .then(function(response) {

      $scope.newObject=response.data;
        $scope.newObject.isActive=1;

    });

    $scope.checkIfDuplicate=function()
    {
      $scope.isNameNotValid=false;
      $http.get(pobCoreServerUrl+"/retrieveLocations")
            .then(function(response) {
        $scope.locationList= response.data;
        var list=$scope.locationList;
        var location;

        for(var i=0; i<list.length; i++)
        {
            location=list[i];

            
          if(location.city === $scope.newObject.city){

            $scope.isNameNotValid=true;
                        break;
          }
          else
          {
            $scope.isNameNotValid=false; 

          }
        }
       
           
        
       
      });
    };
    
    $scope.add = function(obj) {

    	
    	obj.locationId=0;
    	

         $http.post(pobCoreServerUrl+"/addLocation",obj)
        .then(function(){

            $http.get(pobCoreServerUrl+"/retrieveLocations")
            .then(function(response) {
               	gridOptionsService.add(0,response.data);
                $scope.gridOptions.data=gridOptionsService.get(0);
               	$uibModalInstance.close(1);
            	  $uibModalInstance.dismiss('cancel');
       
            });

            

        });
    };

    $scope.cancel=function()
    {
    	$uibModalInstance.close(1);
        $uibModalInstance.dismiss('cancel');
    };

});




