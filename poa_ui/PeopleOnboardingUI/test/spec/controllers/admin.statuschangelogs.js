'use strict';

describe('Controller: AdminStatuschangelogsCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var AdminStatuschangelogsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdminStatuschangelogsCtrl = $controller('AdminStatuschangelogsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdminStatuschangelogsCtrl.awesomeThings.length).toBe(3);
  });
});
