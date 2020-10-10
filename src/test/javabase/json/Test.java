package javabase.json;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javabase.json.dto.User;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/8/29
 */
public class Test {
    public static void main(String[] args) {
        User user = init();
        String jsonString = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes);
        System.out.println(jsonString);

        serialize();
        deserialize();
    }

    /**
     * 将 对象转换成 json
     */
    private static void serialize() {
        User user = new User();
        user.setId(11L);
        user.setName("西安");
        user.setCreateTime(new Date());
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);
    }

    /**
     * 将 json 转换成 对象
     */
    private static void deserialize() {
        // 将 json 转换成 User 对象
        String jsonString = "{\"createTime\":\"2018-08-17 14:38:38\",\"id\":11,\"name\":\"西安\"}";
        User user = JSON.parseObject(jsonString, User.class);
        System.out.println(user.getName());
        System.out.println(user.getCreateTime());

        // 将 json 转换成 List<User> 对象
        String json = "[{\"id\":\"1098\",\"name\":\"张三\"},{\"id\":\"1099\",\"name\":\"李四\"}]";
        List<User> users = JSON.parseArray(json, User.class);
        for (User u : users) {
            System.out.println(u.getId());
            System.out.println(u.getName());
        }
    }

    /**
     * 初始化 User
     * @return User
     */
    private static User init(){
        User user = new User();
        // 定义 user 对象
        user.setId(11L);
//        user.setName("西安");
        user.setCreateTime(new Date());
       return user;
    }
}
