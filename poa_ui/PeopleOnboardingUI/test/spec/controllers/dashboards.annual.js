'use strict';

describe('Controller: DashboardsAnnualCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var DashboardsAnnualCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardsAnnualCtrl = $controller('DashboardsAnnualCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardsAnnualCtrl.awesomeThings.length).toBe(3);
  });
});
