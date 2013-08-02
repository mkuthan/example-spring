'use strict';

angular.module('exampleSpringApp').controller('TodoCtrl', function($scope) {
  $scope.todos = [ 'action 1', 'action 2', 'action 3' ];

  $scope.addTodo = function() {
    $scope.todos.push($scope.todo);
    $scope.todo = '';
  };

  $scope.done = function(index) {
    
  };
  
  $scope.remove = function(index) {
    $scope.todos.splice(index,1);
  };
});
