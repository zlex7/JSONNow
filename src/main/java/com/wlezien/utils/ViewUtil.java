package com.wlezien.utils;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;

import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

public class ViewUtil {
	
	public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.put("msg", "file with strings for application");
        model.put("currentUser", "who the current user is");
        model.put("URLS", Path.Urls.class); // Access application URLs from templates
        return strictVelocityEngine().render(new ModelAndView(model, templatePath));
    }
	
	
	private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.references.strict", true);
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
    }

}
