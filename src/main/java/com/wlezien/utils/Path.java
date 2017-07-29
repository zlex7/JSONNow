package com.wlezien.utils;

public class Path {
	
	
	public static class Urls{
		
		public static final String HOME= "/";
        public static final String ABOUT = "/about";
        public static final String API = "/api/json";
        public static final String TBD = "/tbd";
        public static final String TBD2 = "/tbd2";
        public static final String TBD3 = "/tbd3";
        
        public static String getHome(){
        	
        	return HOME;
        }
        
        public static String getAbout(){
        	
        	return ABOUT;
        }
        
        public static String getApi(){
        	
        	return API;
        }
	}
	public static class Templates{
		
		public static final String INDEX = "/velocity/templates/index.vm";
        public static final String ABOUT = "/velocity/templates/about.vm";
        public static final String TBD = "/velocity/book/all.vm";
        public static final String TBD2 = "/velocity/book/one.vm";
        public static final String TBD3 = "/velocity/notFound.vm";
        
	}
}
