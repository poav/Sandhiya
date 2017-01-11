'use strict';

describe('Controller: DashboardAnnualCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var DashboardAnnualCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardAnnualCtrl = $controller('DashboardAnnualCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardAnnualCtrl.awesomeThings.length).toBe(3);
  });
});
