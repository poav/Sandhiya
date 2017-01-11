'use strict';

describe('Controller: OperationsProjectsCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsProjectsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsProjectsCtrl = $controller('OperationsProjectsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsProjectsCtrl.awesomeThings.length).toBe(3);
  });
});
