ruleEditorApp.controller('createRuleInfoController', function($rootScope,$scope,$q,$window,$location,appService,dataFactory,ngDialog){
	
	$scope.common = {
			rule : {},
			createRuleSuccessMsg : null,
			successMessageFlag : false
		};
	
	$('#createRuleObjId').attr("disabled",true);
	
	$scope.addDefaultConfigurations = function(){
		
		
		console.log(dataFactory);
		for(var i = 0;i< 5; i++){
			var ruleConfigObj = {
					configId : i,
					configName : "",
					configValue : "",
					remove : false
			};
			
			if($scope.common.rule.ruleConfigUiList == undefined){
				$scope.common.rule.ruleConfigUiList = [];
			}
			
			$scope.common.rule.ruleConfigUiList.push(ruleConfigObj);
		}
		
		$('#addRuleConfigurationId').attr("disabled",true);
		
		toggleRuleConfigUiListFields();
		
	};
	
	$scope.onChange = function(){
		
		if(($('#ruleName').val() !=null && $('#ruleName').val().length > 0) && ($('#ruleStatus').val() !=null && $('#ruleStatus').val().length > 0)){
			$('#createRuleObjId').attr("disabled",false);
		}else{
			$('#createRuleObjId').attr("disabled",true);
		}
	};
	
	
	$scope.addRuleConfiguration = function(){
		var ruleConfigObj = {
				configId : 0,
				configName : "",
				configValue : "",
				remove : false
		};
		if($scope.common.rule.ruleConfigUiList == undefined){
			$scope.common.rule.ruleConfigUiList = [];
		}
		var count = $scope.common.rule.ruleConfigUiList.length + 1;
		ruleConfigObj.configId = count;
		$scope.common.rule.ruleConfigUiList.push(ruleConfigObj);
		
		toggleRuleConfigAddButton();
		toggleRuleConfigUiListFields();
	};
	
	
	$scope.removeConfigFromList = function(ruleConfigObj){
		ruleConfigObj.remove = true;
		var tempRuleConfigArr = $scope.common.rule.ruleConfigUiList;
		for(var i =0;i< tempRuleConfigArr.length;i++){
			if(ruleConfigObj.configId == tempRuleConfigArr[i].configId){
				tempRuleConfigArr.splice(i,1);
				break;
			}
		}
		
		toggleRuleConfigAddButton();
		toggleRuleConfigUiListFields();
	};
	
	
	$scope.createRule = function(){
		console.log($scope.common.rule);
		var finalRuleConfigArr = [];
		var tempRuleConfigArr = $scope.common.rule.ruleConfigUiList;
		for(var i =0;i< tempRuleConfigArr.length;i++){
			var tempRuleConfig = tempRuleConfigArr[i];
			
			if(!tempRuleConfig.configName.trim() == "" || !tempRuleConfig.configName == undefined){
				finalRuleConfigArr.push(tempRuleConfig)
			}
		}
		
		$scope.common.rule.ruleConfigUiList = finalRuleConfigArr;
		appService.showLoader();
		appService.makePostCall(createRuleUrl,$scope.common.rule).then(createRuleSuccess,createRuleError);
	};
	
	
	function createRuleSuccess(data){
		console.log(data);
		dataFactory.ruleObj[data.ruleUiList[0].ruleId] = data.ruleUiList[0];
		$rootScope.ruleList.push(data.ruleUiList[0]);
		$scope.common.createRuleSuccessMsg = "Rule #: " +  data.ruleUiList[0].ruleName + " created."
		$scope.common.successMessageFlag = true;
		$("#meesageDivId").css('color', 'green');
		appService.hideLoader();
		$scope.clearForm();
		ngDialog.open({ template: 'html/modalContent.html',controller: 'dialogCtrl', className: 'ngdialog-theme-default', data:data.ruleUiList[0]});
	};
	
	function createRuleError(error){
		ngDialog.open({ template: 'html/errorHandler.html',controller: 'errorHandleController', className: 'ngdialog-theme-default', data:error.ruleInfoErrors[0]});
		$("#createConfigKeyValueId").hide();
		toggleRuleConfigAddButton();
		appService.hideLoader();
	};
	
	$scope.clearForm = function(){
		$scope.common.rule = {};
		$scope.addDefaultConfigurations();
		$scope.common.successMessageFlag = false;
		$('#createRuleObjId').attr("disabled",true);
	};
	
	function toggleRuleConfigAddButton(){
		if($scope.common.rule.ruleConfigUiList.length == 5){
			$('#addRuleConfigurationId').attr("disabled",true);
		}else{
			$('#addRuleConfigurationId').attr("disabled",false);
		}
	};
	
	function toggleRuleConfigUiListFields(){
		if($scope.common.rule.ruleConfigUiList.length == 0){
			$("#createConfigKeyValueId").hide();
		}else if ($scope.common.rule.ruleConfigUiList.length > 0){
			$("#createConfigKeyValueId").show();
		}
	};
	
});


ruleEditorApp.controller('dialogCtrl', function($scope, ngDialog) {
	var data = $scope.ngDialogData;
	
	$scope.message = "Rule Created Successfully!!!";
	
	$scope.common = {
			ruleId : data.ruleId,
			ruleName : data.ruleName,
			status : data.status
	}
	
});