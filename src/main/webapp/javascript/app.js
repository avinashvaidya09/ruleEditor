var ruleEditorApp = angular.module('ruleEditorApp', [ 'ngRoute', 'ngResource',
		'bootstrapLightbox', 'ui.bootstrap','ngDialog']);




ruleEditorApp.config(['$routeProvider',
                 function($routeProvider) {
                   $routeProvider.
                   when('/viewRules', {
                 	  templateUrl:'html/viewRules.html'
                   }).
                     when('/editRule', {
                 	  templateUrl:'html/editRule.html'
                   }).when('/createRule',{
                	   templateUrl : 'html/createRule.html'
                   }).otherwise({
                	   redirectTo : '/viewRules'
                   })
}]);


ruleEditorApp.run(function($rootScope) {
    $rootScope.wsConnected = false;
    $rootScope.ruleEdit = {};
    
    $rootScope.ruleList = [];
    
    $rootScope.handleNotificationDelete = function(){
    	$("#addRuleConfigurationId").hide();
		$("#deleteRuleObjId").hide();
		$("#updateRuleObjId").hide();
		$('#findRuleObjId').attr("disabled",true);
		$("#configKeyValueId").hide();
		$rootScope.ruleEdit = {};
	};
	
	
	
   
});