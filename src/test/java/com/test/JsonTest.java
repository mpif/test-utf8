package com.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @Author: caishengzhi
 * @Date: 2017-05-09 17:09
 */
public class JsonTest {

    private static final JsonParser JSON_PARSER = new JsonParser();

    @Test
    public void test() {
        String jsonStr = "{\"op\":\"range\",\"rules\":[{\"min\":100,\"max\":300,\"detailId\":152},{\"min\":300,\"max\":500,\"detailId\":153},{\"min\":500,\"max\":700,\"detailId\":154},{\"min\":700,\"max\":20000000,\"detailId\":155}],\"key\":\"orderFeeAmount\"}";
        JsonElement jsonElement = JSON_PARSER.parse(jsonStr);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String key = jsonObject.get("key").getAsString();
        String op = jsonObject.get("op").getAsString();

        long keyValue = 0;
        Field f = ReflectionUtils.findField(OrderInfoVo.class, key);

        System.out.println("key:" + key);
        System.out.println("op:" + op);


    }


}

class OrderInfoVo {

}
