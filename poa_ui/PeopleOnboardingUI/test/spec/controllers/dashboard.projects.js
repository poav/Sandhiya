'use strict';

describe('Controller: DashboardProjectsCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var DashboardProjectsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardProjectsCtrl = $controller('DashboardProjectsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardProjectsCtrl.awesomeThings.length).toBe(3);
  });
});
