package cn.com.zxh.stringboot.domain;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

//传递即时聊天
public class Message implements Serializable {
    private String state;       //状态  0：登录  1：在线（发言）  2：离线  3：查询历史记录
    private String sender;      //消息发送者
    private String receiver;    //消息接收者
    private String time;          //消息发送时间
    private String message;     //聊天内容
    private Set onlineUsers;    //在线用户

    public static String jsonStr(String state, String sender, String receiver, String time, String message, Set onlineUsers) {
        return JSON.toJSONString(new Message(state, sender, receiver, time, message, onlineUsers));
    }

    public Message(String state, String sender, String receiver, String time, String message, Set onlineUsers) {
        this.state = state;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.message = message;
        this.onlineUsers = onlineUsers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(Set onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
