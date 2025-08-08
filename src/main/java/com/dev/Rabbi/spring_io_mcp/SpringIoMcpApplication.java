package com.dev.Rabbi.spring_io_mcp;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringIoMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIoMcpApplication.class, args);
	}

	@Bean
	public List<ToolCallback> springIOSessionTools(SessionTools sessionTools) {
		return List.of(ToolCallbacks.from(sessionTools));
	}

}
