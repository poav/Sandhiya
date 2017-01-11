'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationRateCtrl
 * @description
 * # ConfigurationRateCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
  
   
app.controller('ConfigurationRateCtrl', ['$scope','$rootScope','$http','$uibModal','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,gridOptionsService,$mdDialog,pobCoreServerUrl) {

	$scope.add= function () {

	var modalInstance = $uibModal.open({
  	animation: $scope.animationsEnabled,
    templateUrl: 'views/configurations.rate.addnewrate.html',
    controller: 'ConfigurationAddRateCtrl',
    size: 'md',
    resolve: 
      {
        newRateObj: function () {
          return $scope.newRateBean;
          }
      }
   });

    modalInstance.result.then(function () {
      $scope.gridOptions.data=gridOptionsService.get(0);
    	});

    };

   $scope.pageNumList =  [
      '20','30','50'
    ];

$scope.updatePageList=function()
{
  $scope.itemsPerPage=$scope.numberOfPages

};


 $scope.gridOptions = {
    columnDefs: [
      { name: 'rate', displayName: 'Rate', width: 100 },
      { name: 'rateCategory', displayName: 'Rate Category' ,width:250},
      { name: 'location.city',displayName: 'Location', enableCellEdit: false ,width:350},
      { name: 'category.categoryName', displayName: 'CategoryName',width:250 },
      { name: 'position.positionName', displayName: 'Position' ,width:400},
      { name: 'rateType', displayName: 'Rate Type' ,width:180},
      { name: 'isUsed', displayName: 'Is Active' ,cellFilter: 'isActiveFilter:this' ,width:150 }
     
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

        $scope.totalLength=$scope.gridOptions.data.length;

        if($scope.totalLength < newPage*pageSize)
        $scope.pageSize=$scope.totalLength-(newPage-1)*pageSize;
        
        angular.element(document.getElementsByClassName('gridTable')[0]).css('height',  ($scope.pageSize*30)+65 + 'px');

        //$scope.gridApi.grid.gridHeight = ($scope.pageSize*40)+80;    


      });
    },     
    appScopeProvider: { 

        onDblClick : function(row) {
          console.log(row.entity);

          var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'views/configurations.rate.editrate.html',
          controller: 'ConfigurationEditRateCtrl',
          size: 'md',
          resolve: {
          rateObj: function () {
                          return row.entity;
                        }
                   }
          });

            modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
            });

         },

         showAlert: function(){
          alert = $mdDialog.alert({
          title: 'Attention',
          textContent: 'This is an example of how easy dialogs can be!',
          ok: 'Close'
        });

        $mdDialog
        .show( alert )
        .finally(function() {
          alert = undefined;
        });
         }
         ,

         openMenu : function(ev) {
          originatorEv = ev;
          $mdOpenMenu(ev);
        }

    },
    rowTemplate: "<div ng-dblclick=\"grid.appScope.onDblClick(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
    
 };

    $http.get(pobCoreServerUrl+"/displayAllRates")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.gridOptions.data=gridOptionsService.get(0);
        $scope.newRateBean=response.data[0];
 
    });

}]);

app.controller('ConfigurationEditRateCtrl', function($scope, pobCoreServerUrl,$uibModalInstance,rateObj,gridOptionsService,
    $http) {

    $scope.rateObj=rateObj;

    $scope.radioButtonSelected=function()
    {
      $scope.radioButtonsSelected=true;
      $scope.enableRadioMsg=false;

    };

    $scope.isSelected = function(rateObj) {

        if(rateObj.isUsed===true)
        return true;

    };

    $scope.isNotSelected = function(rateObj) {

        if(rateObj.isUsed===false)
        return true;
    
    };

    $scope.editRate = function() {

		var obj= $scope.rateObj;


    

        $http.post(pobCoreServerUrl+"/editRate",obj)
        .then(function(){
        	$uibModalInstance.close(1);
          $uibModalInstance.dismiss('cancel');
        });


    };

    $scope.cancel = function() {

			$uibModalInstance.close(1);
      $uibModalInstance.dismiss('cancel');
    }

});

app.controller('ConfigurationAddRateCtrl', function($scope,pobCoreServerUrl, $uibModalInstance, $rootScope,newRateObj,gridOptionsService,

    $http) {

    $scope.isRateNotValid=false;


    $http.get(pobCoreServerUrl+"/getRateBean").then(function(response) {
        $scope.newRateObject = response.data;
    });
	

    $http.get(pobCoreServerUrl+"/retrieveAllCategories").then(function(response) {
        $scope.rateCategoryNames = response.data;
    });

    $http.get(pobCoreServerUrl+"/retrieveAllPositions").then(function(response) {
        $scope.ratePositions = response.data;
    });

    $http.get(pobCoreServerUrl+"/retrieveAllLocations").then(function(response) {
        $scope.rateLocations = response.data;
    });

    $scope.rateTypes = ['MONTHLY'];
    $scope.rateCategories = ['Offshore', 'Onshore'];
    
    $scope.checkIfRateExists=function()
    {
      if($scope.newRateObject.rate>0)
      {
        
        $http.post(pobCoreServerUrl+"/checkIfRateIsDuplicateOrNot",$scope.newRateObject).then(function(response) {
        $scope.isRateNotValid = response.data;
        });
      }
    }
    $scope.addRate = function(rateObject) {

    	rateObject.rateId=0;
    	rateObject.location.locationId=0;
    	rateObject.position.positionId=0;
    	rateObject.category.categoryId=0;

         $http.post(pobCoreServerUrl+"/saveRate",rateObject)
        .then(function(response){
            

            $http.get(pobCoreServerUrl+"/displayAllRates")
            .then(function(response) {
              gridOptionsService.add(0,response.data);
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
