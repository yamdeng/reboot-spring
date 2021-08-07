package com.yamdeng.template.common;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalApplicationEventListener {

    @EventListener
    public void handleStartedEvent(ApplicationStartedEvent startedEvent) {
        log.info("startedEvent : " + startedEvent);
    }

    @EventListener
    public void handleWebServerInitializedEvent(WebServerInitializedEvent webServerInitializedEvent) {
        log.info("webServerInitializedEvent : " + webServerInitializedEvent);
    }
    
}
