		var app = angular.module('app', []);
			
		
			app.config( function($httpProvider){
				
				delete $httpProvider.defaults.headers.post['Content-Type'];
				
			})
		
			app.controller('formCtrl', function($scope,$http,$sce) {

				console.log("controller");
			

				$scope.jsonoutput="";

    			$scope.submit = function() {

    				/*var output = $http({
    		            method: 'POST',
    		            url: 'localhost:8000/api/json',
    		            dataType: 'text/plain',
    		            data: $scope.json,
    		            headers: { 'Content-Type': 'application/text; charset=UTF-8' }
    		        })
    		        */
    		       
    				
    				var config = {
    		                headers : {
    		                    'Content-Type': 'application/text;charset=utf-8;'
    		                }
    		            }
    		           
    				
    				/*
    				$http.get("http://localhost:8000/api/json",config).then(function(response){
    					
    					console.log("yay get worked");
    					
    				})
    				
    			
    				$http.post("http://localhost:8000/api/json",$scope.json,config).then(function (data, status, headers, config) {
    					
    					console.log("data: " + data.data.json);
    					
    	                $scope.jsonoutput = data.data.json;
    	                
    	            })
    	            */
    	            
    	            
    	            $http({
    	            	
    	            	url: "http://jsonnow.herokuapp.com/api/json",
    	            	
    	            	method: "POST",
    	            	
    	            	headers: {
    	            		   'Content-Type': "application/text"
    	            	},	 
    	            	
    	            	data: $scope.json,
    	            	
    	            	transformResponse: [function (data) {
    	            		// Do whatever you want!
    	            		return $sce.trustAsHtml(data);
    	            	}],
    	            
    	            	responseType: "text"
    	            	
    	            }).then(function (data, status, headers, config) {
    					
    					console.log(data.data);
    					
    	                $scope.jsonoutput = data.data;
    	                
    	            });
    	            
					
    			}
			});