'use strict';

/**
 * @ngdoc overview
 * @name poepleOnboardingAppUiApp
 * @description
 * # poepleOnboardingAppUiApp
 *
 * Main module of the application.
 */
var app=angular
    .module('poepleOnboardingAppUiApp', [
        'ngAnimate',
        'ngAria',
        'ngCookies',
        'ngMessages',
        'ngResource',
        'ngRoute',
        'ngMaterial',
        'ui.router',
        'ngSanitize',
        'ui.bootstrap',
        'ui.grid.selection',
        'ui.grid',
        'ui.grid.pagination',
        'ui.grid.edit',
        'ui.grid.cellNav',
        'ui.grid.autoResize'

    ]);

app.config(function($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl',
                controllerAs: 'loginCtrl'
            })
            .state('about', {
                url: '/about',
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl',
                controllerAs: 'about'
            })
            .state('admin', {
                url: '/admin',
                templateUrl: 'views/admin.html',
                controller: 'AdminCtrl',
                controllerAs: 'admin'
            })
            .state('operations', {
                url: '/operations',
                templateUrl: 'views/operations.html',
                controller: 'OperationsCtrl',
                controllerAs: 'operations'
            })
            .state('developer', {
                url: '/developer',
                templateUrl: 'views/developer.html',
                controller: 'DeveloperCtrl',
                controllerAs: 'developer'
            })
            .state('interviewer', {
                url: '/interviewer',
                templateUrl: 'views/interviewer.html',
                controller: 'InterviewerCtrl',
                controllerAs: 'interviewer'
            })
            .state('dashboards', {
                url: '/dashboards',
                templateUrl: 'views/dashboards.html',
                controller: 'DashboardsCtrl',
                controllerAs: 'dashboards'
            })
            .state('configuration', {
                url: '/configuration',
                templateUrl: 'views/configuration.html',
                controller: 'ConfigurationCtrl',
                controllerAs: 'configuration'
            })
            .state('admin.users', {
                views: {
                    'users': {
                        url: '/admin.users',
                        templateUrl: 'views/admin.users.html',
                        controller: 'AdminUsersCtrl',
                        controllerAs: 'admin.users'
                    }
                }

            })
            .state('admin.statusChangeLogs', {

                views: {
                    'statusChangeLogs': {
                        url: '/admin.statusChangeLogs',
                        templateUrl: 'views/admin.statuschangelogs.html',
                        controller: 'AdminStatuschangelogsCtrl',
                        controllerAs: 'admin.statusChangeLogs'
                    }
                }
            })
            .state('admin.interviewees', {
                views: {
                    'interviewees': {
                        url: '/admin.interviewees',
                        templateUrl: 'views/interviewer.viewinterviewees.html',
                        controller: 'InterviewerViewintervieweesCtrl',
                        controllerAs: 'interviewer.viewInterviewees'
                    }
                }
            })
            .state('developer.issueTracker', {
                url: '/issueTracker',
                views: {
                    'issueTracker': {
                        
                    templateUrl: 'views/operations.issuetracker.html',
                    controller: 'OperationsIssuetrackerCtrl',
                    controllerAs: 'operations.issueTracker'

                        
                    }
                }
            })


        .state('dashboards.quaterly', {
                url: '/quaterly',
                views: {
                    'quaterly': {

                        templateUrl: 'views/dashboards.quaterly.html',
                        controller: 'DashboardsQuaterlyCtrl',
                        controllerAs: 'dashboards.quaterly'
                    }
                }
            })
            .state('dashboards.annual', {
                url: '/annual',
                views: {

                    'annual': {

                        templateUrl: 'views/dashboards.annual.html',
                        controller: 'DashboardsAnnualCtrl',
                        controllerAs: 'dashboards.annual'
                    }
                }
            })

        .state('dashboards.projects', {
            url: '/projects',
            views: {
                'projects': {

                    templateUrl: 'views/dashboards.projects.html',
                    controller: 'DashboardsProjectsCtrl',
                    controllerAs: 'dashboards.projects'
                }
            }
        })


        .state('operations.issueTracker', {
                url: '/issueTracker',
                views: {
                    'issueTracker': {

                        templateUrl: 'views/operations.issuetracker.html',
                        controller: 'OperationsIssuetrackerCtrl',
                        controllerAs: 'operations.issueTracker'
                    }
                }
            })
            .state('operations.OnboardChecklistWOScreening', {
                url: '/OnboardChecklistWOScreening',
                views: {
                    'OnboardChecklistWOScreening': {
                        templateUrl: 'views/operations.onboardchecklistwoscreening.html',
                        controller: 'OperationsOnboardchecklistwoscreeningCtrl',
                        controllerAs: 'operations.OnboardChecklistWOScreening'
                    }
                }
            })
            .state('operations.OnboardChecklist', {
                url: '/OnboardChecklist',
                views: {
                    'OnboardChecklist': {
                        templateUrl: 'views/operations.onboardchecklist.html',
                        controller: 'OperationsOnboardchecklistCtrl',
                        controllerAs: 'operations.OnboardChecklist'
                    }
                }
            })
            .state('operations.interviewees', {
                resolve: {
                    check: function($rootScope)
                    {
                        $rootScope.isOperationMenu=true;
                    }
                },
                url: '/interviewees',
                views: {
                    'interviewees': {
                         templateUrl: 'views/interviewer.viewinterviewees.html',
                        controller: 'InterviewerViewintervieweesCtrl',
                        controllerAs: 'interviewer.viewInterviewees'
                    }
                }
            })
            .state('operations.people', {
                url: '/people',
                views: {
                    'people': {
                        templateUrl: 'views/operations.people.html',
                        controller: 'OperationsPeopleCtrl',
                        controllerAs: 'operations.people'
                    }
                }
            })
            .state('operations.projects', {
                url: '/projects',
                views: {
                    'projects': {
                        templateUrl: 'views/operations.projects.html',
                        controller: 'OperationsProjectsCtrl',
                        controllerAs: 'operations.projects'
                    }
                }
            })
            .state('configuration.rate', {
                url: '/rate',
                views: {
                    'rate': {
                        templateUrl: 'views/configuration.rate.html',
                        controller: 'ConfigurationRateCtrl',
                        controllerAs: 'configuration.rate'
                    }
                }
            })
            .state('configuration.team', {
                url: '/team',
                views: {
                    'team': {
                        templateUrl: 'views/configuration.team.html',
                        controller: 'ConfigurationTeamCtrl',
                        controllerAs: 'configuration.team'
                    }
                }
            })
            .state('configuration.project', {
                url: '/project',
                views: {
                    'project': {
                        templateUrl: 'views/configuration.project.html',
                        controller: 'ConfigurationProjectCtrl',
                        controllerAs: 'configuration.project'
                    }
                }
            })
            .state('configuration.domain', {
                url: '/domain',
                views: {
                    'domain': {
                        templateUrl: 'views/configuration.domain.html',
                        controller: 'ConfigurationDomainCtrl',
                        controllerAs: 'configuration.domain'
                    }
                }
            })
            .state('configuration.location', {
                url: '/location',
                views: {
                    'location': {
                        templateUrl: 'views/configuration.location.html',
                        controller: 'ConfigurationLocationCtrl',
                        controllerAs: 'configuration.location'
                    }
                }
            })
            .state('configuration.status', {
                url: '/status',
                views: {
                    'status': {
                        templateUrl: 'views/configuration.status.html',
                        controller: 'ConfigurationStatusCtrl',
                        controllerAs: 'configuration.status'
                    }
                }
            })
            .state('configuration.title', {
                url: '/title',
                views: {
                    'title': {
                        templateUrl: 'views/configuration.title.html',
                        controller: 'ConfigurationTitleCtrl',
                        controllerAs: 'configuration.title'
                    }
                }
            })
            .state('configuration.emails', {
                url: '/emails',
                views: {
                    'emails': {
                        templateUrl: 'views/configuration.emails.html',
                        controller: 'ConfigurationEmailsCtrl',
                        controllerAs: 'configuration.emails'
                    }
                }
            })
            .state('configuration.dLs', {
                url: '/dLs',
                views: {
                    'dLs': {
                        templateUrl: 'views/configuration.dls.html',
                        controller: 'ConfigurationDlsCtrl',
                        controllerAs: 'configuration.dLs'
                    }
                }
            })
            .state('configuration.dBDesign', {
                url: '/dBDesign',
                views: {
                    'dBDesign': {
                        templateUrl: 'views/configuration.dbdesign.html',
                        controller: 'ConfigurationDbdesignCtrl',
                        controllerAs: 'configuration.dBDesign'
                    }
                }
            })
            .state('configuration.backupDB', {
                url: '/backupDB',
                views: {
                    'backupDB': {
                        templateUrl: 'views/configuration.backupdb.html',
                        controller: 'ConfigurationBackupdbCtrl',
                        controllerAs: 'configuration.backupDB'
                    }
                }
            })
            .state('configuration.restoreDB', {
                url: '/restoreDB',
                views: {
                    'restoreDB': {
                        templateUrl: 'views/configuration.restoredb.html',
                        controller: 'ConfigurationRestoredbCtrl',
                        controllerAs: 'configuration.restoreDB'
                    }
                }
            })
            .state('interviewer.screeningDetails', {
                url: '/screeningDetails',
                views: {
                    'screeningDetails': {
                        templateUrl: 'views/interviewer.screeningdetails.html',
                        controller: 'InterviewerScreeningdetailsCtrl',
                        controllerAs: 'interviewer.screeningDetails'
                    }
                }
            })
            .state('interviewer.viewInterviewees', {
                 resolve: {
                    check: function($rootScope)
                    {
                        $rootScope.isOperationMenu=false;
                    }
                },
                url: '/viewInterviewees',
                views: {
                    'viewInterviewees': {
                        templateUrl: 'views/interviewer.viewinterviewees.html',
                        controller: 'InterviewerViewintervieweesCtrl',
                        controllerAs: 'interviewer.viewInterviewees'
                    }
                }

            })
            .state('interviewer.reportIssue', {
                url: '/reportIssue',
                views: {
                    'reportIssue': {
                        templateUrl: 'views/operations.issuetracker.html',
                        controller: 'OperationsIssuetrackerCtrl',
                        controllerAs: 'operations.issueTracker'
                    }
                }
            })
        .state('operations.updateOID', {
            url:'/updateOID',
            views:{
                'updateOID':{
          templateUrl: 'views/operations.updateoid.html',
          controller: 'OperationsUpdateoidCtrl',
          controllerAs: 'operations.updateOID'
      }
  }
        });
               
         $urlRouterProvider.otherwise('/interviewer/screeningDetails');

    });


app.controller('toastController', function($scope, $mdToast, $document) {

    $scope.settingToast  = function() {
        $mdToast.show(
            $mdToast.simple()
            .position("top right")
            .theme("success-toast")
            .textContent('Please wait while we provide you permissions !')
            .hideDelay(3000)
        );
    };

    $scope.showLogoutToast = function() {
        var toast = $mdToast.simple()
            .textContent('You will be logout Shortly !')
            .position("top right")
            .action('OK')
            .theme("success-toast");
           /* .highlightAction(false);*/
        $mdToast.show(toast).then(function(response) {
            if (response === 'ok') {
                //alert('You clicked \'OK\'.');
            }
        });
    };

     $scope.yetToImplementToast  = function() {
        $mdToast.show(
            $mdToast.simple()
            .position("fit center")
            .theme("success-toast")
            .textContent('Yet to Implement !')
            .hideDelay(1000)
        );
    };
});

//app.constant('pobCoreServerUrl', "http://10.207.238.36:8082");
app.constant('pobCoreServerUrl', "http://10.150.226.85:8080");

app.controller('roleController',function(){

    this.tab=1;
    this.selectTab=function(setTab){
        this.tab=setTab;
    };
    this.isSelected=function(checkTab){
            return this.tab===checkTab;
    };


});

