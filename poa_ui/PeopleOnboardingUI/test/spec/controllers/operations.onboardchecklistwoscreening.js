'use strict';

describe('Controller: OperationsOnboardchecklistwoscreeningCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsOnboardchecklistwoscreeningCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsOnboardchecklistwoscreeningCtrl = $controller('OperationsOnboardchecklistwoscreeningCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsOnboardchecklistwoscreeningCtrl.awesomeThings.length).toBe(3);
  });
});
