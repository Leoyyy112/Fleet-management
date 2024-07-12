// src/main/java/com/ly/fleet_management/config/WebSocketConfig.java

package com.ly.fleet_management.config;

import com.ly.fleet_management.websocket.VehicleStatusHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final VehicleStatusHandler vehicleStatusHandler;


    public WebSocketConfig(VehicleStatusHandler vehicleStatusHandler) {
        this.vehicleStatusHandler = vehicleStatusHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(vehicleStatusHandler, "/vehicle-status").setAllowedOrigins("*");

    }
}
