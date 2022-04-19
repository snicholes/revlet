package com.revature.revlet.http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestMapper {
	private static Map<String, Handler> handlers;

	static {
		handlers = new HashMap<>();
	}

	/**
	 * Register a new HTTP handler implementation with the mapper.
	 * 
	 * @param handler
	 */
	static void register(Handler handler) {
		// TODO throw exception when paths are already taken?
		// TODO log
		handlers.put(handler.getPathMatcher(), handler);
	}

	Handler mapRequest(HttpServletRequest req) {
		String uriString = getPathFromURI(req.getRequestURI(),
				req.getContextPath().length());

		for (String pathMatcher : handlers.keySet()) {
			if (uriString.matches(pathMatcher)) {
				return handlers.get(pathMatcher);
			}
		}
		
		return handlers.get(uriString.toString());
	}

	static String getPathFromURI(String requestURI, int contextPathLength) {
		StringBuilder uriString = new StringBuilder(requestURI);
		// TODO log
		// at this point, uriString = /cats/4 or /CatApp/cats/4 if context path

		uriString.replace(0, contextPathLength + 1, "");
		// at this point, uriString = /cats/4
		
		return uriString.toString();
	}
}
