package com.dushyant.learn_spring_boot.courses.Helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class MyDBHealthService implements HealthIndicator {

	private static final String DB_SERVICE = "DATABASE SERVICE";

	public boolean isHealthGood() {
		return true;
	}
	
	@Override
	public Health health() {
		// TODO Auto-generated method stub
		if(isHealthGood()) {
			return Health.up().withDetail(DB_SERVICE, "Database server is up").build();
		}
		return Health.down().withDetail(DB_SERVICE, "Database server is down").build();
	}

}
