package com.wlezien.api;

import com.wlezien.json.JSON;
import com.wlezien.json.JSONParser;

import spark.Request;
import spark.Response;
import spark.Route;

public class JsonController {
	
	public static Route getJson = (Request request, Response response) -> {
		
		return "getJson page";
	
	};
	
	public static Route prettifyJson = (Request request, Response response) ->	{
		
		System.out.println("running prettifyJson");
		
		System.out.println("attributes: " + request.attributes());
		System.out.println("headers: " + request.headers());
		System.out.println("body: " + request.body());
		System.out.println("raw: " + request.raw());
		
		String input = request.body();
		
		if(input==null){
			
			input="{\"dfsdfd\":\"dfds\"}";
		}
		
		JSONParser parser = new JSONParser();
		
		JSON json = parser.parseInput(input);
		
		System.out.println("Returning \n	  " + json.toString() + " \nin route.");
		
		return json.toString();
		
		
	};
	
}
