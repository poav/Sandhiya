'use strict';

describe('Controller: OperationsIntervieweesCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var OperationsIntervieweesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperationsIntervieweesCtrl = $controller('OperationsIntervieweesCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OperationsIntervieweesCtrl.awesomeThings.length).toBe(3);
  });
});
