'use strict';

describe('Service: todo', function () {

  // load the service's module
  beforeEach(module('ExampleSpringApp'));

  // instantiate service
  var todo;
  beforeEach(inject(function (_todo_) {
    todo = _todo_;
  }));

  it('should do something', function () {
    expect(!!todo).toBe(true);
  });

});
