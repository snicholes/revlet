package com.revature.revlet.http;

import org.junit.jupiter.api.Test;

public class HandlerTest {
	
	@Test
	public void testValidPath() {
		Handler handler = new HandlerTester();
		System.out.println(handler.getPath());
		System.out.println(handler.getPathMatcher());
	}
}

class HandlerTester extends Handler {

	public HandlerTester() {
		super("/pets/{id}/");
	}
	
}