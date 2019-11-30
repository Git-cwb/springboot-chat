package cn.com.zxh.stringboot.service.impl;

import cn.com.zxh.stringboot.service.WebSocketService;
import cn.com.zxh.stringboot.utils.FileUtils;
import cn.com.zxh.stringboot.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    /**
     * 存储聊天信息
     *
     * @param sender   发送者
     * @param message  聊天内容
     * @param time     时间
     * @param receiver 接收者
     */
    @Override
    public void addChat(String sender, String message, String time, String receiver) {
        Map<String, String> chattingRecords = new ConcurrentHashMap<>();
        chattingRecords.put("sender", sender);
        chattingRecords.put("receiver", receiver);
        chattingRecords.put("time", time);
        chattingRecords.put("message", message);

        //添加数据
        new FileUtils().write(new StringUtils().mapToString(chattingRecords),"chattingRecords", 1);
    }


    /**
     * 获取；聊天记录
     *
     * @param sender   发送者
     * @param receiver 接收者
     * @return 历史记录
     */
    @Override
    public List<Map> findChat(String sender, String receiver) {
        List<Map> chatMaps = new ArrayList<>();
        List<Map> mapList = new StringUtils().stringToMapList(new FileUtils().read("chattingRecords"));
        //判断该条历史记录是否与发送者有关
        for (Map map : mapList) {
            if (map.get("sender").equals(sender) || map.get("receiver").equals(sender)) {
                //判断该条记录是否与接收者有关
                if (map.get("sender").equals(receiver) || map.get("receiver").equals(receiver)) {
                    //添加进 chatMaps中
                    chatMaps.add(map);
                }
            }
        }
        return chatMaps;
    }
}
