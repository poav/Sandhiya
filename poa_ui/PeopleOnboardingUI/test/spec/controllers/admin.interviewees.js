'use strict';

describe('Controller: AdminIntervieweesCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var AdminIntervieweesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdminIntervieweesCtrl = $controller('AdminIntervieweesCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdminIntervieweesCtrl.awesomeThings.length).toBe(3);
  });
});
