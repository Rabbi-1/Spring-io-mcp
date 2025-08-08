package com.dev.Rabbi.spring_io_mcp;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Component
public class SessionTools {
    private static final Logger log = LoggerFactory.getLogger(SessionTools.class);
    private List<Session> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper;

    public SessionTools(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Tool(name = "spring-io-sessions", description = "Returns all sessions for Spring I/O 2025 Conference")
    public List<Session> findAllSession() {
        return sessions;
    }

    @PostConstruct
    public void init() {
        log.info("Loading Sessions from JSON file 'session.json'");
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/sessions.json")) {
            var conference = objectMapper.readValue(inputStream, Conference.class);
            this.sessions = conference.sessions();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    }

}
