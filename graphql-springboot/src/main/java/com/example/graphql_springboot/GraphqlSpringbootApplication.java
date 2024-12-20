package com.example.graphql_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GraphqlSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlSpringbootApplication.class, args);
	}

}
