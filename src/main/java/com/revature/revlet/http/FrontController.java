package com.revature.revlet.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 71854041834383966L;
	private RequestMapper rh;
	private static ObjectMapper om;
	
	{rh=new RequestMapper();
	om=new ObjectMapper();}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) {
		Handler handler = rh.mapRequest(req);
		
		if (handler != null) {
			HttpReq httpReq = new HttpReq(req, om, handler.getPath());
			HttpResp httpResp = new HttpResp(resp, om);
			
			switch (req.getMethod()) {
			case "GET":
				handler.get(httpReq, httpResp);
				break;
			case "POST":
				handler.post(httpReq, httpResp);
				break;
			case "PUT":
				handler.put(httpReq, httpResp);
				break;
			case "DELETE":
				handler.delete(httpReq, httpResp);
				break;
			case "PATCH":
				handler.patch(httpReq, httpResp);
				break;
			case "OPTIONS":
				break;
			default:
				resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED_405);
			}
		} else {
			resp.setStatus(HttpStatus.NOT_FOUND_404);
		}
	}
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) {
		process(req,resp);
	}
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("something");
		process(req,resp);
	}
	
	@Override
	protected void doPut (HttpServletRequest req, HttpServletResponse resp) {
		process(req,resp);
	}
	
	@Override
	protected void doDelete (HttpServletRequest req, HttpServletResponse resp) {
		process(req,resp);
	}
	
	@Override
	protected void doOptions (HttpServletRequest req, HttpServletResponse resp) {
		process(req,resp);
	}
}
