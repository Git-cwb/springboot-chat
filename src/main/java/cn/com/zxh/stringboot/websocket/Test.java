package cn.com.zxh.stringboot.websocket;

import cn.com.zxh.stringboot.service.WebSocketService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Component
@RequestMapping("/")
public class Test {
    @Resource
    private WebSocketService webSocketService;

    @RequestMapping("/test")
    public void test(){
        webSocketService.findChat("1","2");
    }
}
