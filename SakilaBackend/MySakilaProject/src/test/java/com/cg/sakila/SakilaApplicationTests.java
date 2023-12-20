package com.cg.sakila;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = SakilaApplicationTests.TestConfiguration.class)
class SakilaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class TestConfiguration {

	}

}
