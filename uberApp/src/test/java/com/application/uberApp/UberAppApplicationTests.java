package com.application.uberApp;

import com.application.uberApp.services.EmailSender;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {
	@Autowired
	private EmailSender emailSender;

	@Test
	void contextLoads() {
		emailSender.sendMail("goldyramchandani@gmail.com",
				"Lehendu",
				"Testing mail.");

	}

}
