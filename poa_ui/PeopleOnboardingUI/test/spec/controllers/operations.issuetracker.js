'use strict';

describe('Controller: OperationsIssuetrackerCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsIssuetrackerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsIssuetrackerCtrl = $controller('OperationsIssuetrackerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsIssuetrackerCtrl.awesomeThings.length).toBe(3);
  });
});
