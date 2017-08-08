ruleEditorApp.controller('editRuleInfoController', function($rootScope, $scope,$q,$window,$location,appService,dataFactory,ngDialog){
	
	$scope.common = {
		rule : {},
		removedConfigList : [],
		flags : {
			
		}
	};
	
	$scope.onEditChange = function(){
		if($('#editRuleName').val() != null &&  $('#editRuleName').val().length > 0){
			$('#findRuleObjId').attr("disabled",false);
		}else{
			$('#findRuleObjId').attr("disabled",true);
		}
	};
	
	
	$scope.addRuleConfiguration = function(){
		var ruleConfigObj = {
				configId : new Date()/1000|0,
				configName : "",
				configValue : "",
				remove : false,
				newConfig : true
		};
		
		if($rootScope.ruleEdit.ruleConfigUiList == undefined || $rootScope.ruleEdit.ruleConfigUiList == null){
			$rootScope.ruleEdit.ruleConfigUiList = [];
		}
		
		$rootScope.ruleEdit.ruleConfigUiList.push(ruleConfigObj)
		
		toggleEditRuleConfigUiList();
		toggleConfigKeyId();
	};
	
	
	$scope.loadRuleForEdit = function(){
		$('#findRuleObjId').attr("disabled",false);
		$("#configKeyValueId").hide();
		console.log(dataFactory);
		$scope.common.rule = $rootScope.ruleEdit;
		if($rootScope.ruleEdit != null && $rootScope.ruleEdit.ruleId != undefined){
			showEditButtons();
			toggleConfigKeyId();
		}else{
			hideEditButtons();
			$('#findRuleObjId').attr("disabled",true);
			
		}
		
	}
	
	$scope.removeConfigFromList = function(ruleConfigObj){
		ruleConfigObj.remove = true;
		if(!ruleConfigObj.newConfig){
		$scope.common.removedConfigList.push(ruleConfigObj);
		}
		var tempRuleConfigArr = $rootScope.ruleEdit.ruleConfigUiList;
		for(var i =0;i< tempRuleConfigArr.length;i++){
			if(ruleConfigObj.configId == tempRuleConfigArr[i].configId){
				tempRuleConfigArr.splice(i,1);
				break;
			}
		}
		toggleEditRuleConfigUiList();
		toggleConfigKeyId();
	};
	
	
	$scope.deleteRule = function(){
		appService.showLoader();
		var deleteRuleObj = $rootScope.ruleEdit;
		
		appService.makePostCall(deleteRuleUrl,deleteRuleObj).then(deleteRuleSuccess,deleteRuleError);
		
	};
	
	
	function deleteRuleSuccess(data){
		console.log(data);
		appService.hideLoader();
		//ngDialog.open({ template: 'html/modalContent.html',controller: 'deleteDialogCtrl', className: 'ngdialog-theme-default', data:$scope.common.rule});
		$scope.common.rule = {};
		$rootScope.ruleEdit = {};
		hideEditButtons();
		$('#findRuleObjId').attr("disabled",true);
		$("#configKeyValueId").hide();
		
	};
	
	function deleteRuleError(error){
		ngDialog.open({ template: 'html/errorHandler.html',controller: 'errorHandleController', className: 'ngdialog-theme-default', data:error.ruleInfoErrors[0]});
		appService.hideLoader();
	};
	
	$scope.findRule = function(){
		var ruleName =  $rootScope.ruleEdit.ruleName;
		appService.showLoader();
		var finalUrl = getRuleByNameUrl+"/"+ruleName
		appService.makeGetCall(finalUrl,null).then(findRuleSuccess,findRuleError);
		
	};
	
	function findRuleSuccess(data){
		console.log(data);
		$rootScope.ruleEdit = data.ruleUiList[0];
		showEditButtons();
		appService.hideLoader();
	};
	
	function findRuleError(error){
		ngDialog.open({ template: 'html/errorHandler.html',controller: 'errorHandleController', className: 'ngdialog-theme-default', data:error.ruleInfoErrors[0]});
		hideEditButtons();
		$rootScope.ruleEdit.ruleConfigUiList = [];
		$("#configKeyValueId").hide();
		appService.hideLoader();
	};
	
	
	$scope.UpdateRule = function(){
		appService.showLoader();
		var updateRuleObj = $rootScope.ruleEdit;
		
		for(var i =0;i< $scope.common.removedConfigList.length;i++){
			if(updateRuleObj.ruleConfigUiList == undefined || updateRuleObj.ruleConfigUiList == null){
				updateRuleObj.ruleConfigUiList = [];
			}
			
				updateRuleObj.ruleConfigUiList.push($scope.common.removedConfigList[i]);
			
		}
		
		for(var i = 0 ; i < updateRuleObj.ruleConfigUiList.length; i++){
			var tempConfig = updateRuleObj.ruleConfigUiList[i];
			
			if(tempConfig.newConfig && tempConfig.configName.trim() == ""){
				updateRuleObj.ruleConfigUiList.splice(i,1);
			}
			
		}
		
		appService.makePostCall(updateRuleUrl,updateRuleObj).then(updateRuleSuccess,updateRuleError);
	};
	
	function updateRuleSuccess(data){
		appService.hideLoader();
		console.log(data);
		var updatedRuleObj = data.ruleUiList[0];
		if(dataFactory.ruleObj != undefined && dataFactory.ruleObj != null){
			dataFactory.ruleObj[updatedRuleObj.ruleId] = updatedRuleObj;
		}
		
		$rootScope.ruleEdit = updatedRuleObj;
		dataFactory.editRuleObj = updatedRuleObj;
		$scope.common.removedConfigList = [];
		
	};
	
	function updateRuleError(error){
		console.log(error);
		ngDialog.open({ template: 'html/errorHandler.html',controller: 'errorHandleController', className: 'ngdialog-theme-default', data:error.ruleInfoErrors[0]});
		hideEditButtons();
		appService.hideLoader();
	};
	
	function hideEditButtons(){
		$("#addRuleConfigurationId").hide();
		$("#deleteRuleObjId").hide();
		$("#updateRuleObjId").hide();
		
	};
	
	function showEditButtons(){
		$("#addRuleConfigurationId").show();
		$("#deleteRuleObjId").show();
		$("#updateRuleObjId").show();
	};
	
	function toggleEditRuleConfigUiList(){
		if($rootScope.ruleEdit.ruleConfigUiList.length == 5){
			$('#addRuleConfigurationId').attr("disabled",true);
		}else{
			$('#addRuleConfigurationId').attr("disabled",false);
		}
	};
	
	function toggleConfigKeyId(){
		if($rootScope.ruleEdit.ruleConfigUiList.length == 0){
			$("#configKeyValueId").hide();
		}else if ($rootScope.ruleEdit.ruleConfigUiList.length > 0){
			$("#configKeyValueId").show();
		}
	};
	
});

ruleEditorApp.controller('deleteDialogCtrl', function($scope, ngDialog) {
	var data = $scope.ngDialogData;
	
	$scope.message = "Rule Deleted !!!";
	
	
	$scope.common = {
			ruleId : data.ruleId,
			ruleName : data.ruleName,
			status : data.status
	}
	
	
	
});