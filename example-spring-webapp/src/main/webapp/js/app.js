require([ 'angular', './controllers', './services', "webjars!ui-bootstrap.js", "webjars!ui-bootstrap-tpls.js" ],
		function(angular, controllers, services) {
			angular.module("exampleSpring", [ "ui.bootstrap", "ui.bootstrap.tpls" ]);
		});
