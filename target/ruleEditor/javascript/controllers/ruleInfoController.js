ruleEditorApp.controller('ruleInfoController', function($rootScope,$scope,$q,$window,$location,appService,dataFactory,ngDialog){
	
	$scope.socket = {
		    client: null,
		    stompClient: null
		};

	
	
	$scope.common = {
			ruleList : [],
			flags : {
				showRuleTable : false,
				showEmptyTableMsg : false,
				showRefreshButton : false
			}
	}
	
	function showResult(responseData){
		var updatedData = JSON.parse(responseData);
		
		if(updatedData.ruleDeleted){
			$rootScope.ruleList = [];
			for(var i in dataFactory.ruleObj){
				$rootScope.ruleList.push(dataFactory.ruleObj[i])
			}
			if($rootScope.ruleList != null && $rootScope.ruleList.length > 0){
				for(var i =0;i<$rootScope.ruleList.length;i++){
					
					if($rootScope.ruleList[i].ruleId == updatedData.ruleId){
						$rootScope.ruleList.splice(i,1);
						break;
					}
				}
			}
			delete dataFactory.ruleObj[updatedData.ruleId];
			$rootScope.handleNotificationDelete();
			$scope.$apply();
			//$rootScope.$digest();
			ngDialog.open({ template: 'html/deleteNotification.html',controller: 'deleteNotificationCtrl', className: 'ngdialog-theme-default', data:updatedData});
			
		}else{
			dataFactory.ruleObj[updatedData.ruleId] = updatedData;
			if($rootScope.ruleList != null && $rootScope.ruleList.length > 0){
			for(var i =0;i<$rootScope.ruleList.length;i++){
				
				if($rootScope.ruleList[i].ruleId == updatedData.ruleId){
					$rootScope.ruleList[i] = updatedData;
					//$scope.$digest();
					// this is for updating the edit screen details in case of edit
					$rootScope.ruleEdit = updatedData;
					break;
				}
			}
			}
			$scope.$apply();
			//$rootScope.$digest();
			ngDialog.open({ template: 'html/notification.html',controller: 'notificationCtrl', className: 'ngdialog-theme-default', data:updatedData});
			
			
		}
		
		
		
	}
	
	
	
	 $scope.disconnect = function() {
		 $scope.socket.stompClient.disconnect();
         console.log("Disconnected");
     }
	 
	$scope.connectWSServer = function(){
		try{
			
		if(!$rootScope.wsConnected){
		$scope.socket.client = new SockJS('/ruleEditor/connect');
		$scope.socket.stompClient = Stomp.over($scope.socket.client);
		$scope.socket.stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            $scope.socket.stompClient.subscribe('/topic/showAlert', function(updateResponse){
            	showResult((updateResponse.body));
            });
        });
		$rootScope.wsConnected = true;
		}
		
		}catch(ex){
			console.log(ex);
			$rootScope.wsConnected = false;
		}
		appService.showLoader();
		console.log(dataFactory);
		if(Object.keys(dataFactory.ruleObj).length == 0){
			appService.makeGetCall(getRulesUrl,null).then(getActiveRuleSuccess,getActiveRuleError);
		}else{
			/*for(var i in dataFactory.ruleObj){
				$rootScope.ruleList.push(dataFactory.ruleObj[i])
			}*/
			$scope.common.flags.showRuleTable = true;
			$scope.common.flags.showEmptyTableMsg = false;
			$scope.common.flags.showRefreshButton = true;
			appService.hideLoader();
		}
			
			
		
	
	};
	
	function getActiveRuleSuccess(data){
		
		
		if(data.ruleUiList.length == 0){
			$scope.common.flags.showRuleTable = false;
			$scope.common.flags.showEmptyTableMsg = true;
			$scope.common.flags.showRefreshButton = false;
			appService.hideLoader();
			
		}else{
		
		for(var i = 0; i < data.ruleUiList.length; i ++){
			
			dataFactory.ruleObj[data.ruleUiList[i].ruleId] = data.ruleUiList[i];
			
		}
		for(var i in dataFactory.ruleObj){
			$rootScope.ruleList.push(dataFactory.ruleObj[i])
		}
		
		$scope.common.flags.showRuleTable = true;
		$scope.common.flags.showEmptyTableMsg = false;
		$scope.common.flags.showRefreshButton = true;
		appService.hideLoader();
		}
	};
	
	function getActiveRuleError(error){
		appService.hideLoader();
		$scope.common.flags.showRuleTable = true;
		$scope.common.flags.showEmptyTableMsg = false;
		$scope.common.flags.showRefreshButton = true;
		ngDialog.open({ template: 'html/errorHandler.html',controller: 'errorHandleController', className: 'ngdialog-theme-default', data:error.ruleInfoErrors[0]});
	};
	
	
	$scope.refreshData = function(){
		appService.showLoader();
		dataFactory.ruleObj = {};
		$rootScope.ruleList = [];
		appService.makeGetCall(getActiveRules,null).then(getActiveRuleSuccess,getActiveRuleError);
	};
	
	
	$scope.editRule = function(ruleObj){
		$rootScope.ruleEdit = ruleObj;
		$location.path('/editRule');
	};
	
});

ruleEditorApp.controller('notificationCtrl', function($scope, ngDialog) {
	var data = $scope.ngDialogData;
	$scope.common = {
			ruleId : data.ruleId,
			ruleName : data.ruleName,
			status : data.status
	}
	
});

ruleEditorApp.controller('deleteNotificationCtrl', function($scope, ngDialog) {
	var data = $scope.ngDialogData;
	$scope.common = {
			ruleId : data.ruleId,
			ruleName : data.ruleName,
			status : data.status
	}
	
});