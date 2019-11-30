package cn.com.zxh.stringboot.websocket;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//客户端
@Component
@RequestMapping("/")
public class WebSocketClient {

    /**
     * 访问登录界面
     */
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    /**
     * 登录聊天室界面
     */
    @RequestMapping("/loginChar")
    public String loginChar(Model model,String userName){
    model.addAttribute("userName",userName);
        return "chat";
    }
}
