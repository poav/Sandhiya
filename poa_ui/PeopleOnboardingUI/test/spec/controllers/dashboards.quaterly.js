'use strict';

describe('Controller: DashboardsQuaterlyCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var DashboardsQuaterlyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardsQuaterlyCtrl = $controller('DashboardsQuaterlyCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardsQuaterlyCtrl.awesomeThings.length).toBe(3);
  });
});
