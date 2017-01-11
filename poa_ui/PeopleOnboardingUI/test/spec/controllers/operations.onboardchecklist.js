'use strict';

describe('Controller: OperationsOnboardchecklistCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsOnboardchecklistCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsOnboardchecklistCtrl = $controller('OperationsOnboardchecklistCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsOnboardchecklistCtrl.awesomeThings.length).toBe(3);
  });
});
