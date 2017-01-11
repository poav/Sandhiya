'use strict';

/**
 * @ngdoc function
 * @name testMaterial3App.controller:AdminUsersCtrl
 * @description
 * # AdminUsersCtrl
 * Controller of the testMaterial3App
 */
var app = angular.module('poepleOnboardingAppUiApp');
app.controller('ConfigurationProjectCtrl', ['$scope', '$mdDialog', '$mdMedia', '$http', '$rootScope', 'uiGridConstants','pobCoreServerUrl',
    function($scope, $mdDialog, $mdMedia, $http, uiGridConstants, $rootScope,pobCoreServerUrl) {

        $scope.gridApi = [];
        $scope.mySelections = [];

        $scope.gridOptions = {
            enablePaginationControls: true,
            paginationPageSizes: [10, 20, 30],
            enableRowSelection: true,

            enableRowHeaderSelection: false,
            modifierKeysToMultiSelect: true,
            multiSelect: true,
            columnDefs: [
                
                { name: 'projectName', displayName: 'Project Name' },
                { name: 'clientProjectId', displayName: 'client Project Id', enableCellEdit: false },
                { name: 'clientTimeTrackingId', displayName: 'Client Time Tracking Id' },
                { name: 'clientTimeTrackingProjectName', displayName: 'Client Time Tracking Project Name' },
                { name: 'clientProjectName', displayName: 'Client Project Name' },
                { name: 'projectStartDate', displayName: 'Start Date' },
                { name: 'projectEndDate', displayName: 'End Date' },
                { name: 'teamBean.teamName', displayName: 'Team Name' },
                { name: 'isActive', displayName: 'Is Active' }
            ],
            onRegisterApi: function(gridApi) {
                $scope.gridApi = gridApi;
                
                gridApi.selection.on.rowSelectionChanged($scope, function(rows) {
                    $scope.mySelections = gridApi.selection.getSelectedRows();
                    //$rootScope.mySelections = $scope.mySelections;
                });
            },
             appScopeProvider: { 
       
        editDialog : function(ev,row) {

      $mdDialog.show({
        controller: "ProjectEditCtrl",
templateUrl: 'views/configuration.project.addEditProject.html',
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
    rowTemplate: "<div ng-dblclick=\"grid.appScope.editDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"


        };
       

        $http.get(pobCoreServerUrl+"/retrieveAllProjects")
            .then(function(response) {
                $scope.gridOptions.data = response.data;
            });


        $scope.selectedRow='';
        $scope.status = '  ';
        $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
        // $scope.showEditDialog = function(ev) {
           
        //     var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
        //     $mdDialog.show({
        //             locals:{dataToPass: $scope.mySelections},
        //             controller: editController,
        //             templateUrl: 'views/configuration.project.addEditProject.html',
        //             parent: angular.element(document.body),
        //             targetEvent: ev,
        //             clickOutsideToClose: true,
        //             fullscreen: useFullScreen
        //         })
        //         .then(function(answer) {
        //             $scope.status = 'You said the information was "' + answer + '".';

        //         }, function() {
        //             $scope.status = 'You cancelled the dialog.';
        //         });

          
        //     $scope.$watch(function() {
        //         return $mdMedia('xs') || $mdMedia('sm');
        //     }, function(wantsFullScreen) {
        //         $scope.customFullscreen = (wantsFullScreen === true);
        //     });
        // };

        $scope.showAddDialog = function(ev) {
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
            $mdDialog.show({
                    controller: addController,
                    templateUrl: 'views/configuration.project.addEditProject.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: useFullScreen
                })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';

                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });

           
            $scope.$watch(function() {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function(wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        };
    }
]);


function editController($scope, $mdDialog, $rootScope,dataToPass) {
    $scope.action="Edit ";
    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);

        /*this is final project which we will send to db*/
        console.log("After Edit Submit");
        console.log(answer);
    };
    $scope.user = dataToPass;
   
}


function addController($scope, $mdDialog,$http) {
    $scope.action="Add ";
    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);
        /*this is final project which we will send to db*/
        console.log("Project is getting added");
        $scope.projectToBeAdded=answer;
        console.log(answer);
        $http.post(pobCoreServerUrl+"/addProject",$scope.projectToBeAdded);
    };

}

app.controller('ProjectEditCtrl',function(editedObject,$scope,$rootScope,$http,$mdDialog,pobCoreServerUrl){
    $scope.user=editedObject;

   $http.get(pobCoreServerUrl+"/retrieveAllTeamNames").then(function(response) {
        $scope.teamList = response.data;
    });


    $scope.edit=function(obj)
    {
        $http.post(pobCoreServerUrl+"/editProject",obj);

    }
    


});
