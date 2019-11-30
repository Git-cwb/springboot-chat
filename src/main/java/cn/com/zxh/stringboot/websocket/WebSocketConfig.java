package cn.com.zxh.stringboot.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//开启WebSocket服务端自动注册
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointConfig(){
        return new ServerEndpointExporter();
    }
}
