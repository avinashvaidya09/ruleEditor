
ruleEditorApp.service('appService', function($q, $http, $resource) {
	
	
	this.makePostCall = function(requestUrl, request){
		
		var def = $q.defer();
	
		try{
			$resource(requestUrl, {}, {
				method : 'POST'
			}).save(request).$promise.then(function(response){
				if(response.ruleInfoErrors == null){
					def.resolve(response);
				}else{
					def.reject(response);
				}
			}, function(){
				def.reject(response);
			});	
		}catch(ex){
			console.log(ex);
		}
		return def.promise;
	};
	
	
	this.makeGetCall = function(requestUrl, request){
		
		var def = $q.defer();
	
		var config = null;
		
		$http.get(requestUrl, request, config)
        .success(function (data, status, headers, config) {
        	if(data.ruleInfoErrors == null){
        		def.resolve(data);
        	}else{
        		def.reject(data);
        	}
        	
        })
        .error(function (data, status, header, config) {
        	def.reject(data);
        });
		
		
	return def.promise;
	};
	
	this.showLoader = function (){
		$('#screen').css({'display': 'block'});
		$('#screen').css({ opacity: 0.5, 'width':$(document).width(),'height':$(document).height()});
	    $('html').css({'overflow':'hidden'});
	    $('#imageBox').css({'display': 'block'});
	};
	
	this.hideLoader= function(){
		    $('html').css({'overflow':'block'});
		    $('#imageBox').css({'display': 'none'});
		    $('#screen').css({'display': 'none'});
	};
	
	
});