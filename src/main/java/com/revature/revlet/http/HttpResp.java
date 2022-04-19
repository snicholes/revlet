package com.revature.revlet.http;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpResp {
	private HttpServletResponse resp;
	private ObjectMapper om;
	HttpResp(HttpServletResponse resp, ObjectMapper om) {
		this.resp=resp;
		this.om=om;
	}
	
	public HttpServletResponse getServletResponse() { return resp; }
	
	public void writeResponseBody(String body) throws IOException {
		resp.getWriter().write(body);
	}
	
	public void writeResponseBodyAsJson(Object obj) throws JsonProcessingException, IOException {
		writeResponseBody(om.writeValueAsString(obj));
	}
	
	public void setStatus(int statusCode) {
		resp.setStatus(statusCode);
	}
}
