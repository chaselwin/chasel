var app = angular.module("httpApp", []);
	
	app.controller("loginCtrl", ["userService", "$scope", "$http", function(userService, $scope, $http) {
				$scope.user = {};
				$scope.login = function() {
					userService.onLogin($scope.user).then(
					function(data) {
						if("欢迎回来"==data.data.message){
							window.location="../user/users.html";
						}else{
							$scope.msg=data.data.message;
						}
					},
					function(err) {
						alert(err.data.message);
					});
				}
			}]);

	app.service('userService', ['$rootScope', '$http', function($rootScope, $http){
		var service = {
			msg:{},
			onLogin: function(user) {
				return $http.post('userLogin',user);
			}
		}
		return service;
	}]);