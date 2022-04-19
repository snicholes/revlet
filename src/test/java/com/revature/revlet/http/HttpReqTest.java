package com.revature.revlet.http;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HttpReqTest {
	@Mock
	HttpServletRequest mockReq;
	
	HttpReq testReq;
	
	@Test
	public void pathParamMapping() {
		when(mockReq.getRequestURI()).thenReturn("localhost:8080/pets/5/adopt");
		when(mockReq.getContextPath()).thenReturn("localhost:8080");
		
		testReq = new HttpReq(mockReq, null, "pets/{id}/adopt");
		System.out.println(testReq.getPathParam("id"));
	}
}
