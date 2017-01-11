'use strict';

describe('Controller: DeveloperIssuetrackerCtrl', function () {

  // load the controller's module
  beforeEach(module('poepleOnboardingAppUiApp'));

  var DeveloperIssuetrackerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DeveloperIssuetrackerCtrl = $controller('DeveloperIssuetrackerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DeveloperIssuetrackerCtrl.awesomeThings.length).toBe(3);
  });
});
