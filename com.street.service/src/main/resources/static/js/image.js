var app = angular.module("httpApp", []);

	app.controller("imageCtrl", ["imageService", "$scope", "$http", function(imageService, $scope, $http) {
		//在此配置$scope.image={};
		$scope.image = imageService.selectedImage;
		$scope.pageInfo = {"pageSize":10,"pageNum":1};
		$scope.editStatus = imageService.editStatus;
		
		$scope.loadImages = function() {
			imageService.queryImages($scope.image,$scope.pageInfo).then(
			function(data) {
				//读取完将imageService.selectedImage，imageService.editStatus初始化
				imageService.restoreInitStatus();
				$scope.images = data.data;
			},	
			function(err) {
				//TODO,错误处理
				alert(er);
			});
		}
		
		$scope.deleteImage = function(imageId) {
					imageService.onDelete(imageId).then(
						function(data) {
						    imageService.restoreInitStatus();
							alert(data.data.message);
							$scope.loadImages();
						},
						function(err) {
							//TODO,错误处理
							alert(err.data.message);
							$scope.loadImages();
					});
		}
		
		//添加
		$scope.addImage = function() {
			imageService.onEdit();
		}
		//修改
		$scope.editImage = function(image) {
			imageService.onEdit(image);
		}
		
		//改变每页显示条数
		$scope.changeSize = function(){
			$scope.loadImages();
		}
		
		//第几页
		$scope.changeNum = function(){
			$scope.loadImages();
		}
		
		//上一页
		$scope.previousPage = function(){
			if($scope.pageInfo.pageNum-1>0){
				$scope.pageInfo.pageNum=$scope.pageInfo.pageNum-1;
			}
			else{
				$scope.pageInfo.pageNum=1;
			}
			$scope.loadImages();
		}
		
		//下一页
		$scope.nextPage = function(){
			if($scope.pageInfo.pageNum+1<=$scope.images.pages){
				$scope.pageInfo.pageNum=$scope.pageInfo.pageNum+1;
			}
			else{
				$scope.pageInfo.pageNum=$scope.images.pages;
			}
			$scope.loadImages();
		}
		
		//查询
		$scope.loadImages();
		 
		 

}]);
	
	app.controller("imageEditCtrl", ["imageService", "$scope", "$http", function(imageService, $scope, $http) {
				$scope.image = imageService.selectedImage;
				$scope.editStatus = imageService.editStatus;
				$scope.msg="上传照片成功后, 窗口不自动关闭, 您可以继续上传…";

				$scope.saveImage = function(isValid) {
					if (isValid){
						if($scope.editStatus==1){
							
							var fd = new FormData(); 
							var fileInfo =$('#imageFile').prop('files')
							if(fileInfo==null||fileInfo.length<=0){
								alert("please select an image");
							}else{
								for(var i=0;i<fileInfo.length;i++){
									
									fd.append("file", fileInfo[i]);
								}
								fd.append("type",$scope.image.type);
								fd.append("title",$scope.image.title);
								fd.append("memo",$scope.image.memo);
								fd.append("creator",$scope.image.creator);
								 
								 $.ajax({
					             url: "../image/add",
					             type: "POST",
					             processData: false, 
					             contentType: false, 
					             dataType: 'json', 
					             data: fd, 
					             success:function(data){
					            		 alert(data.message);
					            	 },
					            	 error:function(data){
						            	 alert(data.message);
						             }
						         });
							}
						}
				         if($scope.editStatus==2){
					         //修改
					        imageService.onSave($scope.image).then(
							function(data) {
							    imageService.restoreInitStatus();
								alert(data.data.message);
							},
							function(err) {
								//TODO,错误处理
								alert(err.data.message);
							});
				         }
						
					}else{
						alert("数据校验失败");
					}
				};
			    
			    $scope.$watch(function(){
			        return imageService.editStatus;
			     }, function(newVal, oldVal){
			        	$scope.image = imageService.selectedImage;
			        	$scope.editStatus = imageService.editStatus;
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

	app.service('imageService', ['$rootScope', '$http', function($rootScope, $http){
		var service = {
            images: [],
			selectedImage: {},
			//0:为读，1:新增，2：修改
			editStatus: 0,
			
			queryImages: function(image,pageInfo) {
				return $http.post('../image/list/page/'+pageInfo.pageSize+'/'+pageInfo.pageNum,image);
			},
			
			onDelete: function(id) {
				return $http.delete('../image/delete/'+id);
			},
			
			onEdit: function(image) {
						if(image) {
							//selectedImage=image,不能正确设值到servic.selectedImage
							service.selectedImage = JSON.parse(JSON.stringify(image));
							service.editStatus = 2
						} else {
							service.selectedImage = {};
							service.editStatus = 1
						}
			},
			
			onSave: function(image) {
					return $http.put('../image/update',image);
			},
			
			restoreInitStatus(){
			    service.selectedImage={};
			    service.editStatus=0;
			}
		}
		return service;
	}]);
	
		app.service('userService', ['$rootScope', '$http', function($rootScope, $http){
		var service = {
			
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
