ruleEditorApp.controller('errorHandleController', function($scope, ngDialog) {
	var data = $scope.ngDialogData;
	
	$scope.common = {
			errorCode : data.errorCode,
			errorMessage : data.errorMessage
	};
	
});
