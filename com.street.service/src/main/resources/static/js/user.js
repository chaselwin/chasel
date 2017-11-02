var app = angular.module("httpApp", []);

	app.controller("userCtrl", ["userService", "$scope", "$http", function(userService, $scope, $http) {
		//在此配置$scope.user={};
		$scope.user = userService.selectedUser;
		$scope.pageInfo = {"pageSize":10,"pageNum":1};
		$scope.editStatus = userService.editStatus;
		
		$scope.loadUsers = function() {
			userService.queryUsers($scope.user,$scope.pageInfo).then(
			function(data) {
				//读取完将userService.selectedUser，userService.editStatus初始化
				userService.restoreInitStatus();
				$scope.users = data.data;
			},	
			function(err) {
				//TODO,错误处理
				alert(er);
			});
		}
		
		$scope.deleteUser = function(userId) {
					userService.onDelete(userId).then(
						function(data) {
						    userService.restoreInitStatus();
							alert(data.data.message);
							$scope.loadUsers();
						},
						function(err) {
							//TODO,错误处理
							alert(err.data.message);
							$scope.loadUsers();
					});
		}
		
		//添加
		$scope.addUser = function() {
			userService.onEdit();
		}
		//修改
		$scope.editUser = function(user) {
			userService.onEdit(user);
		}
		
		//改变每页显示条数
		$scope.changeSize = function(){
			$scope.loadUsers();
		}
		
		//第几页
		$scope.changeNum = function(){
			$scope.loadUsers();
		}
		
		//上一页
		$scope.previousPage = function(){
			if($scope.pageInfo.pageNum-1>0){
				$scope.pageInfo.pageNum=$scope.pageInfo.pageNum-1;
			}
			else{
				$scope.pageInfo.pageNum=1;
			}
			$scope.loadUsers();
		}
		
		//下一页
		$scope.nextPage = function(){
			if($scope.pageInfo.pageNum+1<=$scope.users.pages){
				$scope.pageInfo.pageNum=$scope.pageInfo.pageNum+1;
			}
			else{
				$scope.pageInfo.pageNum=$scope.users.pages;
			}
			$scope.loadUsers();
		}
		
		//查询
		$scope.loadUsers();
		 
		 

}]);
	
	app.controller("userEditCtrl", ["userService", "$scope", "$http", function(userService, $scope, $http) {
				$scope.user = userService.selectedUser;
				$scope.editStatus = userService.editStatus;

				$scope.saveUser = function(isValid) {
					if (isValid){
						userService.onSave($scope.user).then(
						function(data) {
						    userService.restoreInitStatus();
							alert(data.data.message);
						},
						function(err) {
							//TODO,错误处理
							alert(err.data.message);
						});
					}else{
						alert("数据校验失败");
					}
				};
			    
			    $scope.$watch(function(){
			        return userService.editStatus;
			     }, function(newVal, oldVal){
			        	$scope.user = userService.selectedUser;
			        	$scope.editStatus = userService.editStatus;
			      }
			    ,true);

			}]);
	
	//判断是否登录
	app.controller("myCtrl", ["userService", "$scope", "$http","$interval", function(userService, $scope, $http,$interval) {
				$scope.loadName = function() {
					userService.selectName().then(
					function(data) {
						if("未登录"==data.data.message){
							window.location="../login/login.html";
						}else if("已登录"==data.data.message){
							$scope.userName=data.data.userName;
						}
					},
					function(err) {
						alert(err.data.message);
					});
				}
				
				$scope.loadName();
				
				
				/*var stop;
				 $scope.$on('$ionicView.beforeLeave', function() {
			            $interval.cancel(stop);//离开页面后停止轮询
			    });

			 //轮询
		      stop = $interval($scope.loadName(), 6000);*/
				
			}]);
	//注销		
	app.controller("logoutCtrl", ["userService", "$scope", "$http", function(userService, $scope, $http) {
				$scope.logout = function() {
					userService.onLogout().then(
					function(data) {
						if("注销成功"==data.data.message){
							window.location="../login/login.html";
						}
					},
					function(err) {
						alert(err.data.message);
					});
				}
				
			}]);

	app.service('userService', ['$rootScope', '$http', function($rootScope, $http){
		var service = {
            users: [],
			selectedUser: {},
			//0:为读，1:新增，2：修改
			editStatus: 0,
			
			queryUsers: function(user,pageInfo) {
				return $http.post('../user/list/page/'+pageInfo.pageSize+'/'+pageInfo.pageNum,user);
			},
			
			onDelete: function(id) {
				return $http.delete('../user/delete/'+id);
			},
			
			onEdit: function(user) {
						if(user) {
							//selectedUser=user,不能正确设值到servic.selectedUser
							service.selectedUser = JSON.parse(JSON.stringify(user));
							service.editStatus = 2
						} else {
							service.selectedUser = {};
							service.editStatus = 1
						}
			},
			
			onSave: function(user) {
						if(service.editStatus==1){
							return $http.post('../user/add',user);
						}else{
							return $http.put('../user/update',user);
						}
			},
			
			selectName: function(){
				return $http.post('../login/ifLogin');
			},
			
			onLogout:function(){
				return $http.post('../login/logout');
			},
			
			restoreInitStatus(){
			    service.selectedUser={};
			    service.editStatus=0;
			}
		}
		return service;
	}]);
