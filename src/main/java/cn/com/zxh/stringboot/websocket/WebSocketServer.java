package cn.com.zxh.stringboot.websocket;
//聊天室服务端

import cn.com.zxh.stringboot.domain.ChattingRecords;
import cn.com.zxh.stringboot.domain.Message;
import cn.com.zxh.stringboot.service.WebSocketService;
import cn.com.zxh.stringboot.service.impl.WebSocketServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/server/{userName}")//声明此类为服务端
@Component
public class WebSocketServer {

    private static WebSocketService webSocketService;
    @Autowired
    public  void setWebSocketService(WebSocketService webSocketService) {
        WebSocketServer.webSocketService = webSocketService;
    }


    //创建一个map 用来存储所有在线会话的数据
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    //创建一个set集合  存放在线用户名
    private static Set<String> onlineUsers = new HashSet<>();


    /**
     * 客户登录时：
     */
    @OnOpen
    public void onOpen(@PathParam("userName") String userName, Session session) {
        onlineSessions.put(session.getId(), session);
        onlineUsers.add(userName);
        sendMessageToAll(Message.jsonStr("0", userName, "", "", "", onlineUsers));
    }

    /**
     * 用户发送消息时
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //获取前端数据
        Message message = JSON.parseObject(jsonStr, Message.class);
        //判断数据类型
        if (message.getState().equals("1")) {
            //将聊天内容存入本地文件中
           webSocketService.addChat(message.getSender(), message.getMessage(), message.getTime(), message.getReceiver());
            //发言
            sendMessageToAll(Message.jsonStr("1", message.getSender(), message.getReceiver(), message.getTime(), message.getMessage(), onlineUsers));
        } else if (message.getState().equals("3")) {
            //从查询对应的历史记录
            List<Map> chatMaps = webSocketService.findChat(message.getSender(), message.getReceiver());
            //传输数据
            sendMessageToAll(ChattingRecords.jsonStr("3", chatMaps, message.getSender()));
        }
    }

    /**
     * 用户下线时
     */
    @OnClose
    public void onClose(@PathParam("userName") String userName, Session session) {
        onlineSessions.remove(session.getId(), session);
        onlineUsers.remove(userName);
        sendMessageToAll(Message.jsonStr("2", userName, "", null, "", onlineUsers));
    }

    /**
     * 用户通信异常时
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 将信息发送给所有用户
     */
    private static void sendMessageToAll(String msg) {
//        System.out.println(msg);
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

