package com.revature.revlet.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpReq {
	private HttpServletRequest req;
	private ObjectMapper om;
	private Map<String, String> pathParams;
	private Map<String, String[]> queryParams;
	
	HttpReq(HttpServletRequest req, ObjectMapper om, String path) {
		this.req = req;
		this.om = om;
		this.pathParams = new HashMap<>();
		buildMaps(path);
	}
	
	public String getBodyAsString() {
		StringBuilder body = new StringBuilder("");
		
		try {
			BufferedReader reader = req.getReader();
			String line = "";
			while ((line=reader.readLine())!=null) {
				body.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return body.toString();
	}
	public <T> T getJsonBodyAsObject(Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		T obj = om.readValue(req.getInputStream(), type);
		return obj;
	}
	
	public HttpServletRequest getServletRequest() { return req; }
	
	public String getPathParam(String key) {
		return pathParams.get(key);
	}
	
	public String[] getQueryParam(String key) {
		return queryParams.get(key);
	}
	
	private void buildMaps(String path) {
		String[] pathPieces = cleanPathArray(path.split("/"));
		String[] reqUriPieces = cleanPathArray(RequestMapper.getPathFromURI(
				req.getRequestURI(), req.getContextPath().length())
				.split("/"));
		
		// path params
		for (int i=0; i<pathPieces.length && i<reqUriPieces.length; i++) {
			if (pathPieces[i].startsWith("{"))
				pathParams.put(pathPieces[i].replaceAll("[{}]", ""), reqUriPieces[i]);
		}
		
		// query params
		this.queryParams = req.getParameterMap();
	}
	
	private String[] cleanPathArray(String[] arr) {
		return Stream.of(arr)
				.filter(str -> !str.equals(""))
				.toArray(String[]::new);
	}
}
