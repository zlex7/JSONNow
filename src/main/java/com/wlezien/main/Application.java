package com.wlezien.main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.wlezien.about.AboutController;
import com.wlezien.api.JsonController;
import com.wlezien.index.IndexController;
import com.wlezien.utils.Path;

import spark.Spark;

public class Application 
{
	public static void main(String[] args) {

        // Instantiate your dependencies
		System.out.println("running main method");

        // Configure Spark
        port(8000);
        staticFiles.location("/static");
        staticFiles.expireTime(600L);
        
        
        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
        
       // before("*",                  Filters.addTrailingSlashes);
       // before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get(Path.Urls.HOME, IndexController.serveIndexPage);
        get(Path.Urls.ABOUT, AboutController.serveAboutPage);
        get(Path.Urls.API,JsonController.getJson);
        
        post(Path.Urls.API, JsonController.prettifyJson);
        


       // after("*",                   Filters.addGzipHeader);

    }
}
