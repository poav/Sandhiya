'use strict';


angular.module('poepleOnboardingAppUiApp')


.controller('InterviewerScreeningdetailsCtrl', ["$http", "pobCoreServerUrl", "$scope", function($http, pobCoreServerUrl, $scope) {

    $scope.myIndex = 10;

    $scope.retriveProject = function(teamName) {
        $http.get(pobCoreServerUrl + "/retriveProjectsForTeam", { params: { "teamName": teamName } })
            .then(function(response) {
                $scope.projectList2 = response.data;
                console.log($scope.projectList2);
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
        $scope.fulltopicBean = {};
        $http.get(pobCoreServerUrl + "/getPositionsByDomain", { params: { "domainName": domainName } })
            .then(function(response) {
                $scope.positionList = response.data;

            });
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
            //console.log($scope.teamList);
        });

    $http.get(pobCoreServerUrl + "/retrieveAllCategories")
        .then(function(response) {
            $scope.domainList = response.data;
            //console.log("Domain List: ");
            // console.log($scope.domainList);
        });

    $http.get(pobCoreServerUrl + "/retrieveAllLocations")
        .then(function(response) {
            $scope.locationList = response.data;

        });

    $http.get(pobCoreServerUrl + "/getEmptyPersonStaffingBean")
        .then(function(response) {
            $scope.personStaffingBean = response.data;
            // console.log($scope.personStaffingBean);
        });

    $http.get(pobCoreServerUrl + "/getEmptyPersonScreeningDetailsBean")
        .then(function(response) {
            $scope.personScreeningDetailsBean = response.data;
            // console.log($scope.personScreeningDetailsBean);
        });

    $http.get(pobCoreServerUrl + "/getEmptyRateBean")
        .then(function(response) {
            $scope.rateBean = response.data;
            // console.log($scope.rateBean);
        });

    $http.get(pobCoreServerUrl + "/getEmptyOnboardingChecklistBean")
        .then(function(response) {
            $scope.onboardingCheckListBean = response.data;
            //console.log($scope.onboardingCheckListBean);
        });

    $http.get(pobCoreServerUrl + "/getEmptyScoreBean")
        .then(function(response) {
            $scope.scoreBean = response.data;
            //console.log($scope.scoreBean);
        });

    $scope.submit = function() {
       
       $scope.personScreeningDetailsBean.statusBean={};
        
        var statusBean={};
        statusBean.resultName = 'NA';
        statusBean.statusName = 'New';
        $scope.personScreeningDetailsBean.statusBean=statusBean;

        var screenerBean = {};
        screenerBean.personOracleId = '115183';
        $scope.personScreeningDetailsBean.screenerBean = screenerBean;

        var score2 = $scope.generateOneDArray($scope.score);
        $scope.onboardingCheckListBean.personScreeningDetails = $scope.personScreeningDetailsBean;
        $scope.onboardingCheckListBean.personScreeningDetails.personBean = $scope.personScreeningDetailsBean.personBean;
        var screeningDetailsEntity = {};
        screeningDetailsEntity.onboardingCheckListBean = $scope.onboardingCheckListBean;
        screeningDetailsEntity.scoreListFromUI = score2;

        $http.post(pobCoreServerUrl + "/submitScreeningForm", screeningDetailsEntity)
            .then(function(response) {
                    console.log("Form submitted");
                },
                function(response) { // optional
                    console.log("Network is busy. Unable to submit ");
                });
    };
    $scope.getCriteriaList = function(domainName) {
        $http.get(pobCoreServerUrl + "/getTopicsOfDomain", { params: { "domainName": domainName } })
            .then(function(response) {
                $scope.criteriaList = response.data;
                //console.log($scope.criteriaList);
            });
    };

    $scope.fulltopicBean = {};
    $scope.criteriaSelection = [];
    $scope.score = {};
    var oneDScoreArry = [];

    $scope.generateOneDArray = function(twoDarray) {
        var m = objSize(twoDarray);
        var n;

        var i, j = 0;
        for (i = 0; i < m; i++) {
            n = objSize(twoDarray[i]);
            for (j = 0; j < n; j++) {
                //oneDScoreArry[k++] = twoDarray[i][j];
                oneDScoreArry.push(twoDarray[i][j]);
            }
        }
        return oneDScoreArry;
    };


    var objSize = function(obj) {
        var count = 0;

        if (typeof obj === "object") {

            if (Object.keys) {
                count = Object.keys(obj).length;
            } else if (window._) {
                count = _.keys(obj).length;
            } else if (window.$) {
                count = $.map(obj, function() {
                    return 1;
                }).length;
            } else {
                for (var key in obj){
                    if (obj.hasOwnProperty(key)) 
                        count++;
                }
            }

        }

        return count;
    };

    $scope.getTopics = function(criteriaID) {
        $http.get(pobCoreServerUrl + "/retriveTopics", {
                params: {
                    "criteriaID": criteriaID
                }
            })
            .then(function(response) {

                $scope.topicBean = response.data;
                //console.log("Heres your topicBean: ")
                //console.log($scope.topicBean);

                $scope.fulltopicBean[$scope.cName] = $scope.topicBean;
                //console.log("Heres your Full topicBean: ")
                //console.log($scope.fulltopicBean);
            });
    };

    $scope.toggleCriteriaSelection = function(criteriaName, criteriaID) {
        var idx = $scope.criteriaSelection.indexOf(criteriaName);
        if (idx > -1) {
            $scope.criteriaSelection.splice(idx, 1);
            $('#' + criteriaName).remove();
            delete $scope.fulltopicBean[criteriaName];
        } else {
            $scope.criteriaSelection.push(criteriaName);
            $scope.cName = criteriaName;
            $scope.getTopics(criteriaID, criteriaName);
        }
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
}]);
