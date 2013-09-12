'use strict';

angular.module('ExampleSpringApp').factory('Todos', [ '$resource', function($resource) {
  return $resource('/api/todos/:todoId', {
    todoId : '@id'
  }, {
    done : {
      method : 'PUT'
    },
    isArray : false
  });
} ]);
