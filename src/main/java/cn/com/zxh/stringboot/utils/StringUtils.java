package cn.com.zxh.stringboot.utils;

import java.util.*;

//string工具类 用来进行string 数据与其他类型的转换
public class StringUtils {

    /**
     * map 转 String
     */
    public String mapToString(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        //给数组排序(升序)
        Arrays.sort(keyArray);
        //创建字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            sb.append(keyArray[i]).append(":").append(String.valueOf(map.get(keyArray[i]))).append(",");
            //在末尾添加 # 是为了区分不同的Map集合
            if (i == keyArray.length - 1) {
                sb.append("#\r\n");
            }
        }
        return sb.toString();
    }


    /**
     * string 转 list<Map>
     */
    public List<Map> stringToMapList(String str) {
        //创建List集合
        List<Map> mapList = new ArrayList<>();
        //创建Map对象
        Map<String, Object> map = new HashMap<>();

        //根据逗号截取字符串数组
        String[] str1 = str.split(",");

        //循环加入map集合
        for (int i = 0; i < str1.length; i++) {

            String[] str2 = str1[i].split(":",2);

            if (str2[0].equals("#")) {
                //如果 读取的值 为 # 则将集合添加进行list中
                mapList.add(map);
                //添加完后，重新创建一个Map集合  防止数据覆盖
                map = new HashMap<>();
            } else if (str2.length == 1) {
                //如果无value，则添加一个空的值
                map.put(str2[0], "");
            } else {
                //str2[0]为KEY,str2[1]为值
                map.put(str2[0], str2[1]);
            }
        }

        return mapList;
    }

}
