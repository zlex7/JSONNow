package com.wlezien.index;


import java.util.HashMap;
import java.util.Map;

import com.wlezien.utils.Path;
import com.wlezien.utils.ViewUtil;

import spark.Request;
import spark.Response;
import spark.Route;

public class IndexController {
	
	
	  public static Route serveIndexPage = (Request request, Response response) -> {
	        Map<String, Object> model = new HashMap<>();
	        model.put("1", "2");
	        model.put("2", "2");
	        return ViewUtil.render(request, model, Path.Templates.INDEX);
	  };
	
}
