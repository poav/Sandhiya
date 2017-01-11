'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsOnboardchecklistCtrl
 * @description
 * # OperationsOnboardchecklistCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
var app = angular.module('poepleOnboardingAppUiApp');

app.controller('OperationsOnboardchecklistCtrl', ['$scope', '$rootScope', '$http', '$uibModal', 'gridOptionsService', '$mdDialog', 'pobCoreServerUrl', function($scope, $rootScope, $http, $uibModal, gridOptionsService, $mdDialog, pobCoreServerUrl) {

    $scope.onboardChecklist = {};

    $scope.requestDocumentResubmission = function(ev) {
        $mdDialog.show({
                controller: "requestDocumentResubmissionCtrl",
                templateUrl: 'views/operations.onboardChecklist.requestDocumentResubmission.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                locals: {
                    onboardChecklist: $scope.onboardChecklist

                },
                clickOutsideToClose: true
            })
            .then(function(saveSuccessMsg, msgEnabled) {
                $scope.saveSuccessMsg = saveSuccessMsg;
                $scope.msgEnabled = msgEnabled;
                $http.get(pobCoreServerUrl + "/retrieveAllFeedBacks")
                    .then(function(response) {
                        gridOptionsService.add(0, response.data);
                        $scope.gridOptions.data = gridOptionsService.get(0);
                        $scope.newBean = response.data[0];

                    });
            });
    };

    $rootScope.gridOptions = {
        enablePaginationControls: true,
        paginationPageSizes: [10, 20, 30],
        paginationPageSize: 10,
        multiSelect: false,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;

            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

                if (row.isSelected)
                    $scope.onboardChecklist = row.entity;


                console.log(onboardChecklist);
            });
        },
        appScopeProvider: {
            onDblClick: function(row) {

                console.log(row.entity);


                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'views/operations.onboardCheckList.editOnboardCheckList.html',
                    controller: 'onboardCheckListEditCtrl',
                    size: 'lg',
                    resolve: {
                        editedObject: function() {
                            return row.entity;
                        }
                    }
                });

                modalInstance.result.then(function(selectedItem) {
                    $scope.selected = selectedItem;
                });

            },
            showTabDialog: function(ev, row) {

                $mdDialog.show({
                        templateUrl: 'views/operations.onboardCheckList.editOnboardCheckList.html',
                        controller: 'onboardCheckListEditCtrl',
                        parent: angular.element(document.body),
                        targetEvent: ev,
                        locals: {
                            editedObject: row.entity
                        },
                        clickOutsideToClose: true
                    })
                    .then(function(successMsg, msgEnabled) {
                        $scope.successMsg = successMsg;
                        $scope.msgEnabled = msgEnabled;
                    });
            }

        },
        rowTemplate: "<div ng-dblclick=\"grid.appScope.showTabDialog($event,row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell ></div>"

    };

    $scope.gridOptions.columnDefs = [
        { name: 'personStaffingBean.person.personOracleId', displayName: 'Person Oracle Id' },
        { name: 'personStaffingBean.person.personName', displayName: 'Name' },
        { name: 'isOnsite', displayName: 'Is Onsite' },
        { name: 'addedInDl', displayName: 'Added in dl' },
        { name: 'addedInRS3', displayName: 'added in RS3' },
        { name: 'hasPreviouslyWorkedForClient', displayName: 'Has previously worked for Client' },
        { name: 'hasSapientLaptop', displayName: 'Has Sapient Laptop' },
        { name: 'personStaffingBean.person.isContractor', displayName: 'Is Contractor' }
    ];

    $scope.resendBgcDocuments = function() {

        $http.post(pobCoreServerUrl + "/resendBGCDocumentsToCandidate", $scope.onboardChecklist)
            .then(function() {
                $scope.resendBgcDocumentMsg = "BGC Documents sent to Candidate";
            });

    };
    $http.get(pobCoreServerUrl + "/retrieveOnboardingChecklist")
        .then(function(response) {
            gridOptionsService.add(0, response.data);
            $scope.gridOptions.data = gridOptionsService.get(0);
            $scope.newBean = response.data[0];


        });


}]);

app.controller('onboardCheckListEditCtrl', function(editedObject, $scope, $rootScope, $http, $mdDialog, pobCoreServerUrl) {

    $scope.editedObject = editedObject;
    //console.log($scope.editedObject.datePersonApprovedInClientVendorManagementSystem);

    if ($scope.editedObject.personApprovedInClientVendorManagementSystemDate != null)
        $scope.editedObject.personApprovedInClientVendorManagementSystemDate = new Date($scope.editedObject.personApprovedInClientVendorManagementSystemDate);
    if ($scope.editedObject.personAddedInClientVendorManagementSystemDate != null)
        $scope.editedObject.personAddedInClientVendorManagementSystemDate = new Date($scope.editedObject.personAddedInClientVendorManagementSystemDate);
    if ($scope.editedObject.backgroundCheckDoneDate != null)
        $scope.editedObject.backgroundCheckDoneDate = new Date($scope.editedObject.backgroundCheckDoneDate);
    if ($scope.editedObject.backgroundCheckSubmittedDate != null)
        $scope.editedObject.backgroundCheckSubmittedDate = new Date($scope.editedObject.backgroundCheckSubmittedDate);

    $scope.bgcStatusList = ["Pass", "Fail"];
    $scope.optionsList = ["Yes", "No"];


    $scope.selectedValue = function(option) {
        if (option === "Yes")
            return true;
        else
            return false;
    };



    $http.get(pobCoreServerUrl + "/getStatusListForOnboardingInProgress")
        .then(function(response) {
            $scope.statusList = response.data;
        });

    $scope.update = function() {
        var arr = [];
        $scope.editedObject.addedInDl = ($scope.editedObject.addedInDl2);
        $scope.editedObject.isOnsite = ($scope.editedObject.isOnsite2);
        $scope.editedObject.addedInRS3 = ($scope.editedObject.addedInRS32);
        $http.post(pobCoreServerUrl + "/editOnboardingChecklist", $scope.editedObject)
            .then(function() {
                $scope.updateSuccessMsg = "Feedback is updated successfully.";
                $scope.msgEnabled = "true";
                $mdDialog.hide($scope.updateSuccessMsg, $scope.msgEnabled);
            });
    };

    $scope.selectedValueInvert = function(option) {
        if (option === true)
            return "Yes";
        else
            return "No";
    };



});


app.controller('requestDocumentResubmissionCtrl', function($scope, onboardChecklist, pobCoreServerUrl,

    $http, $mdDialog) {



    $scope.details = [
        "InCorrect Details",
        "Missing Details",
        "Indian PassPort/Indian Driver licence",
        "Indian VoterId/license/Indian Pan Card"
    ];


    $scope.detailsListToBeSubmitted = [];



    $scope.selected = [false, false, false, false];
    $scope.toggle = function(index) {
        $scope.selected[index] = !$scope.selected[index];
    };



    $scope.save = function() {
        $scope.detailsListToBeSubmitted = [];
        var ind = 0;
        var i;
        for (i in $scope.selected) {
            if ($scope.selected[i] === true) {
                $scope.detailsListToBeSubmitted.push($scope.details[ind]);
            }
            ind++;
        }

        var parameters = {

            details: $scope.detailsListToBeSubmitted,
            comments: $scope.comments,
            onboardingCheckList: onboardChecklist

        }


        $http.get(pobCoreServerUrl + "/requestdocumentResubmission", parameters)
            .then(function() {

                $scope.saveSuccessMsg = "Feedback is reported successfully. It will be updated soon";
                $scope.msgEnabled = "true";

                $mdDialog.hide($scope.saveSuccessMsg, $scope.msgEnabled);
            });
    };

});
