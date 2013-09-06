'use strict';

angular.module('exampleSpringApp').factory('Todos', function($resource) {
  return $resource('/api/todos/:todoId', {
    todoId : '@id'
  }, {
    done : {
      method : 'PUT'
    },
    isArray : false
  });
});
