'use strict';

angular.module('ExampleSpringApp').controller('TodoCtrl', function($scope, Todos) {
  $scope.todos = Todos.query();

  $scope.addTodo = function() {
    $scope.todos.push($scope.todo);
    $scope.todo = '';
  };

  $scope.done = function(index) {
    $scope.todos.splice(index,1);
  };
  
  $scope.remove = function(index) {
    $scope.todos.splice(index,1);
  };
});
