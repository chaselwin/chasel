var app = angular.module("httpApp", []);

	app.controller("imageCtrl", ["imageService", "$scope", "$http", function(imageService, $scope, $http) {
		
		$scope.loadImages = function() {
			imageService.queryImages().then(
			function(data) {
				$scope.images = data.data;
			},	
			function(err) {
				//TODO,错误处理
				alert(er);
			});
		}
		
		//查询
		$scope.loadImages();
		 
		 

}]);
	

	app.service('imageService', ['$rootScope', '$http', function($rootScope, $http){
		var service = {
            images: [],
			
			queryImages: function() {
				return $http.post('../image/findAll');
			}
			
		}
		return service;
	}]);
