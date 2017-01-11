'use strict';

/**
 * @ngdoc function
 * @name poepleOnboardingAppUiApp.controller:OperationsOnboardchecklistwoscreeningCtrl
 * @description
 * # OperationsOnboardchecklistwoscreeningCtrl
 * Controller of the poepleOnboardingAppUiApp
 */
angular.module('poepleOnboardingAppUiApp')
    .controller('OperationsOnboardchecklistwoscreeningCtrl', function($http, pobCoreServerUrl, $scope) {
        $scope.isRatePresent = 'true';
        $scope.rateTypeList = [
            { name: 'Monthly', value: 'MONTHLY' }
        ];
        $scope.rateBean = {};
        $scope.rateBean.rateType = $scope.rateTypeList[0];
        $scope.rateCategoryList = [
            { name: 'Offshore', value: 'offshore' },
            { name: 'Onshore', value: 'onshore' }
        ];
        $scope.rateBean.rateCategory = $scope.rateCategoryList[0];


        $scope.retriveProject = function(teamName) {
            $http.get(pobCoreServerUrl + "/retriveProjectsForTeam", { params: { "teamName": teamName } })
                .then(function(response) {
                    $scope.projectList2 = response.data;
                    $scope.projectList = $scope.projectList2.records;
                });
        };


        $scope.retriveProjectImpDates = function(projectName) {
            $http.get(pobCoreServerUrl + "/getProjectStartDate", { params: { "projectName": projectName } })
                .then(function(response) {
                    $scope.projectStartDate = response.data;
                });
            $http.get(pobCoreServerUrl + "/getProjectEndDate", { params: { "projectName": projectName } })
                .then(function(response) {
                    $scope.projectEndDate = response.data;
                });
        };

        $scope.retrivePositionsByDomain = function(domainName) {
            $http.get(pobCoreServerUrl + "/getPositionsByDomain", { params: { "domainName": domainName } })
                .then(function(response) {
                    $scope.positionList = response.data;
                });
        };

        $scope.retriveEmailsDLs = function(projectName, location) {

            if (!projectName || !location) {

            } else {
                $http.get(pobCoreServerUrl + "/getDlListByProjectNameAndLocation", {
                        params: {
                            "projectName": projectName,
                            "location": location
                        }
                    })
                    .then(function(response) {
                        $scope.dLsList = response.data;
                        console.log(dLsList);
                    });
            }
        }
        $scope.calcRate2 = function(location, position, domain, rateCategory, rateType) {

            if (!location || !position || !domain || !rateCategory || !rateType) {

            } else {

                $http.get(pobCoreServerUrl + "/getRate", {
                        params: {
                            "location": location,
                            "position": position,
                            "domain": domain,
                            "rateCategory": rateCategory,
                            "rateType": rateType

                        }
                    })
                    .then(function(response) {

                        $scope.rateBean2 = response.data;

                    }).then(function(response) {

                    });

            }
        };

        $scope.isEmpty = function(object) {
            if (!object) {
                return true;
            } else {
                return false;
            }
        };

        $http.get(pobCoreServerUrl + "/displayAllteams")
            .then(function(response) {
                $scope.teamList = response.data;

            });

        $http.get(pobCoreServerUrl + "/retrieveAllCategories")
            .then(function(response) {
                $scope.domainList = response.data;
            });

        $http.get(pobCoreServerUrl + "/retrieveAllLocations")
            .then(function(response) {
                $scope.locationList = response.data;

            });

        $http.get(pobCoreServerUrl + "/getEmptyPersonStaffingBean")
            .then(function(response) {
                $scope.personStaffingBean = response.data;

            });

        $http.get(pobCoreServerUrl + "/getEmptyPersonScreeningDetailsBean")
            .then(function(response) {
                $scope.personScreeningDetailsBean = response.data;

            });

        $http.get(pobCoreServerUrl + "/getEmptyRateBean")
            .then(function(response) {
                $scope.rateBean = response.data;

            });

        $http.get(pobCoreServerUrl + "/getEmptyOnboardingChecklistBean")
            .then(function(response) {
                $scope.onboardingCheckListBean = response.data;

            });

        $scope.submit = function() {

            $scope.personScreeningDetailsBean.screenerBean = {};
            var screenerBean = {};
            screenerBean.personOracleId = '115183';
            $scope.personScreeningDetailsBean.screenerBean = screenerBean;

            $scope.personStaffingBean.rateBean = $scope.rateBean2;

            $scope.personScreeningDetailsBean.statusBean = {};
            var statusBean = {};
            statusBean.resultName = 'Forms requested';
            statusBean.statusName = 'Onboarding in progress';
            $scope.personScreeningDetailsBean.statusBean = statusBean;

            $scope.onboardingCheckListBean.personScreeningDetails = $scope.personScreeningDetailsBean;
            $scope.onboardingCheckListBean.personScreeningDetails.personBean = $scope.personScreeningDetailsBean.personBean;

            $scope.onboardingCheckListBean.personStaffingBean = $scope.personStaffingBean;

            var screeningDetailsEntity = {};
            screeningDetailsEntity.onboardingCheckListBean = $scope.onboardingCheckListBean;

            $http.post(pobCoreServerUrl + "/sumitOnboardingWithoutScreeningForm", screeningDetailsEntity)
                .then(function(response) {
                        console.log("Form submitted");
                    },
                    function(response) { // optional
                        console.log("Network is busy. Unable to submit ");
                    });








        };







        $scope.getPersonNames = function(name) {

            $http.post(pobCoreServerUrl + "/displayPersonBeanForName", name)
                .then(function(response) {

                    $scope.personBeanList = response.data;
                });

            return $scope.personBeanList;
        };

        $scope.updatePSD = function(item, model, label, event, user) {

            var personBean = {};
            personBean.personOracleId = item.oracleId;
            personBean.personEmailId = item.email;
            personBean.personNtId = item.username;
            personBean.personName = item.name;
            personBean.isTemp = user.isNewHire;
            $scope.personScreeningDetailsBean.personBean = personBean;
        };

    });
