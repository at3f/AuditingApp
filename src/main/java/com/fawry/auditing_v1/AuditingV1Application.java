package com.fawry.auditing_v1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableRabbit
@SpringBootApplication
public class AuditingV1Application {

	public static void main(String[] args) {
		SpringApplication.run(AuditingV1Application.class, args);
	}

}
