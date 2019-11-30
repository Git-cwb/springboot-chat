package cn.com.zxh.stringboot.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//传递历史聊天记录的内容
public class ChattingRecords implements Serializable {
    private String state;           //状态  0：登录  1：在线（发言）  2：离线  3：查询历史记录
    private String user;            //当前用户
    private List<Map> chatMaps;     //历史记录

    public static String jsonStr(String state, List<Map> chatMaps, String user) {
        return JSON.toJSONString(new ChattingRecords(state, user, chatMaps));
    }

    public ChattingRecords(String state, String user, List<Map> chatMaps) {
        this.state = state;
        this.user = user;
        this.chatMaps = chatMaps;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Map> getChatMaps() {
        return chatMaps;
    }

    public void setChatMaps(List<Map> chatMaps) {
        this.chatMaps = chatMaps;
    }
}
