package cn.com.zxh.stringboot.service;

import java.util.List;
import java.util.Map;

public interface WebSocketService {
    /**
     * 存储聊天信息
     * @param sender    发送者
     * @param message   聊天内容
     * @param time      时间
     * @param receiver  接收者
     */
    void addChat(String sender,String message,String time,String receiver);

    /**
     *  获取；聊天记录
     * @param sender    发送者
     * @param receiver  接收者
     * @return  历史记录
     */
    List<Map> findChat(String sender,String receiver);
}
