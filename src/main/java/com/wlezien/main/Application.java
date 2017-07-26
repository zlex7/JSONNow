package com.wlezien.main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import com.wlezien.index.IndexController;

public class Application 
{
	public static void main(String[] args) {

        // Instantiate your dependencies


        // Configure Spark
        port(8000);
        staticFiles.location("/static");
        staticFiles.expireTime(600L);

       // before("*",                  Filters.addTrailingSlashes);
       // before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get("/", IndexController.serveIndexPage());
        


       // after("*",                   Filters.addGzipHeader);

    }
}
