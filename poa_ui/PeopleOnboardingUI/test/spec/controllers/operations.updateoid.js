'use strict';

describe('Controller: OperationsUpdateoidCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsUpdateoidCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsUpdateoidCtrl = $controller('OperationsUpdateoidCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsUpdateoidCtrl.awesomeThings.length).toBe(3);
  });
});
