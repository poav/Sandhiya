'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:ConfigurationDomainCtrl
 * @description
 * # ConfigurationDomainCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app=angular.module('poepleOnboardingAppUiApp');
  
  app.service('gridOptionsService',function(){

    var gridOptionsData=[];

    var addData= function(index,value)
    {
      gridOptionsData[index]=value;
    };

    var getValue= function(index)
    {
      return gridOptionsData[index];
    };

    return {

      add: addData,
      get: getValue
    };


  });

  app.controller('ConfigurationDomainCtrl',['$scope','$rootScope','$http','$uibModal','$filter','gridOptionsService','$mdDialog','pobCoreServerUrl',function ($scope,$rootScope,$http,$uibModal,$filter,gridOptionsService,$mdDialog,pobCoreServerUrl) {
    $scope.domainCollapsed = true;
    $scope.criteriaDisabled=true;
    $scope.topicDisabled=true;


    $scope.isDomain=function()
    {
      $scope.domainCollapsed = false;
      $scope.criteriaCollapsed = true;
      $scope.topicsCollapsed = true;

    };

     $scope.isCriteria=function()
    {
    $scope.domainCollapsed = true;
    $scope.criteriaCollapsed = false;
    $scope.topicsCollapsed = true;

    };

 	  $scope.isTopic=function()
    {
      $scope.domainCollapsed = true;
      $scope.criteriaCollapsed = true;
      $scope.topicsCollapsed = false;

    };

    $scope.topicGridOptions = {
            rowHeight: 40,

      enablePaginationControls: true,
      paginationPageSizes:[10,20,30],
      columnDefs: [
        { name: 'topicName', displayName: 'Topic' },
        { name: 'isUsed', displayName: 'Active'}  
        ]
      };

    $scope.criteriaGridOptions = {
            rowHeight: 40,

      enablePaginationControls: true,
      paginationPageSizes:[10,20,30],
      columnDefs: [
        { name: 'criteriaName', displayName: 'Criteria' },
        { name: 'isUsed', displayName: 'Active'}  
        ],
      appScopeProvider: { 
        onCriteriaClick : function(row) {
          console.log(row.entity);

          $scope.domainCollapsed = true;
          $scope.criteriaCollapsed = true;
          $scope.topicsCollapsed = false;
          $scope.criteriaDisabled=false;
          $scope.topicDisabled=false;


          $http.post(pobCoreServerUrl+"/getAllTopicBeans",row.entity)
          .then(function (response) {
            gridOptionsService.add(2,response.data);
            console.log(gridOptionsService.get(2));
            $scope.topicGridOptions.data=gridOptionsService.get(2);

            $scope.newTopicBean=response.data[0];  

            if($scope.newTopicBean===undefined)
              {
                $http.get(pobCoreServerUrl+"/getTopicBean")
                .then(function(response){
                 $scope.newTopicBean=response.data;
                 $scope.newTopicBean.criteriaBean=row.entity;
                });
              }   
          });

         }
    },
    rowTemplate: "<div ng-click=\"grid.appScope.onCriteriaClick(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
   
    };
 
    $scope.domainGridOptions = {
      rowHeight: 40,
      enablePaginationControls: true,
      paginationPageSizes:[10,20,30],
      columnDefs: [
        { name: 'categoryName', displayName: 'Domain' },
        { name: 'isUsed', displayName: 'Active'}  
        ],
      
      appScopeProvider: { 

        onDomainClick : function(row) {
          console.log(row.entity);

          $scope.domainCollapsed = true;
          $scope.criteriaCollapsed = false;
          $scope.topicsCollapsed = true;
          $scope.criteriaDisabled=false;
          $scope.topicDisabled=true;

          $http.post(pobCoreServerUrl+"/getAllCriterias",row.entity.categoryId)
          .then(function (response) {
              gridOptionsService.add(1,response.data);
              $scope.criteriaGridOptions.data=gridOptionsService.get(1);
              $scope.newCriteriaBean=response.data[0];

              if($scope.newCriteriaBean===undefined)
              {
                $http.get(pobCoreServerUrl+"/getCriteriaBean")
                .then(function(response){
                 $scope.newCriteriaBean=response.data;
                 $scope.newCriteriaBean.categoryBean=row.entity;
                });
              }

          });

         }
    },
    rowTemplate: "<div ng-click=\"grid.appScope.onDomainClick(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"
   
    };
 
    $http.get(pobCoreServerUrl+"/retrieveAllCategoryBean")
    .then(function (response) {
        gridOptionsService.add(0,response.data);
        $scope.domainGridOptions.data=gridOptionsService.get(0);
        $scope.newDomainBean=response.data[0];    

        $scope.criteriaCollapsed = true;
        $scope.topicsCollapsed = true; 
    });

   



  $scope.addCriteria= function () {

    var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.domain.addCriteria.html',
        controller: 'ConfigurationAddCriteriaCtrl',
        size: 'md',
        resolve: {
        newObject: function () {
          return $scope.newCriteriaBean;
        }
       }
      });

      modalInstance.result.then(function () {
      $scope.criteriaGridOptions.data=gridOptionsService.get(1);
      });

    };

    $scope.addTopic = function () {

    var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.domain.addTopic.html',
        controller: 'ConfigurationAddTopicCtrl',
        size: 'md',
        resolve: {
        newObject: function () {
          return $scope.newTopicBean;
        }
       }
      });

      modalInstance.result.then(function () {
      $scope.topicGridOptions.data=gridOptionsService.get(2);
      });

    };

    $scope.addDomain= function () {

    var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
        templateUrl: 'views/configuration.domain.addDomain.html',
        controller: 'ConfigurationAddDomainCtrl',
        size: 'md',
        resolve: {
        newObject: function () {
          return $scope.newDomainBean;
        }
       }
      });

      modalInstance.result.then(function () {
      $scope.domainGridOptions.data=gridOptionsService.get(0);
      });

    };

    
}]);



app.controller('ConfigurationAddDomainCtrl', function($scope, $uibModalInstance, $rootScope,newObject, $http,gridOptionsService,pobCoreServerUrl) {

  //$scope.newObject=newObject;

    $http.get(pobCoreServerUrl+"/getCategoryBean")
        .then(function(response){
     $scope.newObject=response.data;
    });
  
   $scope.checkIfDuplicate=function()
    {
      $http.get(pobCoreServerUrl+"/retrieveAllCategories")
      .then(function (response) {
        $scope.categoryList= response.data;
        var list=$scope.categoryList;
        for(var domain in list)
        {
           if(domain === $scope.newObject.categoryName)
            $scope.isNameNotValid=false;
          else
            $scope.isNameNotValid=true; 
        }
       
      });
    };

    $scope.save = function(obj) {
      obj.categoryId=0;

         $http.post(pobCoreServerUrl+"/addCategory",obj)
        .then(function(){

            $http.get(pobCoreServerUrl+"/retrieveAllCategoryBean")
            .then(function(response) {
                gridOptionsService.add(0,response.data);
                console.log(gridOptionsService.get(0));
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

app.controller('ConfigurationAddTopicCtrl', function($scope, $uibModalInstance, $rootScope,newObject, $http,gridOptionsService,pobCoreServerUrl) {

  $scope.newObject=newObject;

  $scope.newObject.topicName=null;
  $scope.newObject.isUsed=1;

   $scope.checkIfDuplicate=function()
    {
      $scope.isNameNotValid=false;
     $http.post(pobCoreServerUrl+"/getAllTopicBeans",$scope.newObject.criteriaBean)
            .then(function(response) {
        $scope.topicList= response.data;
        var list=$scope.topicList;
        var topic;

        for(var i=0; i<list.length; i++)
        {
            topic=list[i];

            
          if(!(topic.topicName === $scope.newObject.topicName))
            $scope.isNameNotValid=false;
          else
          {
            $scope.isNameNotValid=true; 
            break;
          }
        }
          
      });
    };

    $scope.save = function(obj) {
      obj.topicId=0;

         $http.post(pobCoreServerUrl+"/addTopic",obj)
        .then(function(){

            $http.post(pobCoreServerUrl+"/getAllTopicBeans",obj.criteriaBean)
            .then(function(response) {
                gridOptionsService.add(2,response.data);
                console.log(gridOptionsService.get(2));
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

app.controller('ConfigurationAddCriteriaCtrl', function($scope, $uibModalInstance, $rootScope,newObject, $http,gridOptionsService,pobCoreServerUrl) {

  $scope.newObject=newObject;

  $scope.newObject.criteriaName=null;
  $scope.newObject.isUsed=1;

   $scope.checkIfDuplicate=function()
    {
      $scope.isNameNotValid=false;
     $http.post(pobCoreServerUrl+"/getAllCriterias",$scope.newObject.categoryBean.categoryId)
            .then(function (response) {
        $scope.criteriaList= response.data;
        var list=$scope.criteriaList;
        var criteria;

        for(var i=0; i<list.length; i++)
        {
            criteria=list[i];

            
          if(!(criteria.criteriaName === $scope.newObject.criteriaName))
            $scope.isNameNotValid=false;
          else
          {
            $scope.isNameNotValid=true; 
            break;
          }
        }
       
           
        
       
      });
    };

    $scope.save = function(obj) {
      obj.criteriaId=0;

         $http.post(pobCoreServerUrl+"/addCriteria",obj)
        .then(function(){

            $http.post(pobCoreServerUrl+"/getAllCriterias",obj.categoryBean.categoryId)
            .then(function (response) {
              gridOptionsService.add(1,response.data);
              console.log(gridOptionsService.get(1));
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