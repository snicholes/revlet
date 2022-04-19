package com.revature.revlet;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.revature.revlet.http.FrontController;

public class RevletApp {
	// TODO allow user to set context path
	/**
	 * Starts the application on the default port 8080.
	 */
	public static void start() {
		start(8080);
	}
	/**
	 * Starts the application on your specified port number.
	 * @param port
	 */
	public static void start(int port) {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(port);
		tomcat.getConnector();
		
		Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
		Tomcat.addServlet(ctx, "front", new FrontController());
		ctx.addServletMappingDecoded("/*", "front");
		tomcat.setSilent(true);
		
		try {
			tomcat.start();
			System.out.println("Revlet has started!");
			System.out.println("Listening at http://localhost:" + port + ".");
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
