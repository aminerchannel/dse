package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired 
	ConnectionTester connectionTester;
	@Test
	void testValidConnection() {
		ConnectionRequest connectionRequest = new ConnectionRequest("localhost", 5432, "mydb", "admin","admin");
		ConnectionTester connectionTester =new ConnectionTester();
		boolean res= connectionTester.test(connectionRequest);
		assertTrue(res);

	}
	@Test
	void testInValidConnection() {
		ConnectionRequest connectionRequest = new ConnectionRequest("localhost", 5431, "mydb", "admin","admin");
		 
		boolean res= connectionTester.test(connectionRequest);
		assertFalse(res);

	}

}
