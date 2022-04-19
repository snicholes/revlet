package com.revature.revlet.http;

import java.util.LinkedList;
import java.util.List;

/**
 * Extend this class to set up HTTP handling for a particular endpoint group.
 * 
 * Implement the abstract methods get(), post(), put(), delete(), patch() based on 
 * your needs for each HTTP method.
 * 
 * Set the path when instantiating.
 * 
 * @author SierraNicholes
 *
 */
public abstract class Handler {
	private final String path;
	private final String pathMatcher;
	List<String> pathParams;
	/**
	 * Create a handler for handling HTTP requests to the
	 * specified path. Path parameters should be surrounded by
	 * curly braces, like in the following example:
	 * 
	 * "resources/{id}"
	 * 
	 * @param path
	 * @throws Exception 
	 */
	public Handler(String path) {
		this.pathParams = new LinkedList<>();
		this.path = processPath(path);
		this.pathMatcher = setPathMatcher(path);
		RequestMapper.register(this);
	}
	/**
	 * Handler method for a GET request to the handler's path.
	 * @param req The HttpReq object constructed in the front controller servlet.
	 * @param resp The HttpResp object constructed in the front controller servlet.
	 */
	protected void get(HttpReq req, HttpResp resp) {
		resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
	}
	/**
	 * Handler method for a POST request to the handler's path.
	 * @param req The HttpReq object constructed in the front controller servlet.
	 * @param resp The HttpResp object constructed in the front controller servlet.
	 */
	protected void post(HttpReq req, HttpResp resp) {
		resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
	}
	/**
	 * Handler method for a PUT request to the handler's path.
	 * @param req The HttpReq object constructed in the front controller servlet.
	 * @param resp The HttpResp object constructed in the front controller servlet.
	 */
	protected void put(HttpReq req, HttpResp resp) {
		resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
	}
	/**
	 * Handler method for a DELETE request to the handler's path.
	 * @param req The HttpReq object constructed in the front controller servlet.
	 * @param resp The HttpResp object constructed in the front controller servlet.
	 */
	protected void delete(HttpReq req, HttpResp resp) {
		resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
	}
	/**
	 * Handler method for a PATCH request to the handler's path.
	 * @param req The HttpReq object constructed in the front controller servlet.
	 * @param resp The HttpResp object constructed in the front controller servlet.
	 */
	protected void patch(HttpReq req, HttpResp resp) {
		resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
	}
	String getPath() {
		return this.path;
	}
	/**
	 * 
	 * @return the regex for matching the handler's path
	 */
	String getPathMatcher() {
		return this.pathMatcher;
	}
	private String processPath(String path) {
		if (!path.matches("/?[a-z]+(/[{]?[a-z]+}?)*/?")) throw new RuntimeException("Invalid path for handler.");
		
		StringBuilder pathModifier = new StringBuilder(path);
		if (pathModifier.charAt(0)=='/') pathModifier.deleteCharAt(0);
		
		while (pathModifier.indexOf("{") != -1) {
			String pathParamKey = pathModifier
					.substring(pathModifier.indexOf("{")+1,pathModifier.indexOf("}"));
			pathParams.add(pathParamKey);
			pathModifier.deleteCharAt(pathModifier.indexOf("{"));
		}
		
		return path;
	}
	private String setPathMatcher(String path) {
		StringBuilder matcher = new StringBuilder("");
		matcher.append("/?");
		
		String[] pieces = path.split("/");
		
		for (String piece : pieces) {
			if (!piece.equals("")) {
				if (!piece.startsWith("{")) {
					matcher.append(piece);
				} else {
					matcher.append("\\w+");
				}
				matcher.append("/");
			}
		}
		matcher.append("?");
		
		return matcher.toString();
	}
}
