package com.beyounger.openapi;

import com.alibaba.fastjson.JSONObject;
import com.beyounger.openapi.util.HmacSHA256Base64Util;
import org.junit.Test;

import java.util.TreeMap;


public class NotificationTest {
    @Test
    public void notificationTest() {
        //what you receive in header
        String timeStamp = "1585810920286";
        String signature = "y9/rsn79nMlP4EgIzVHWvt6GLvPx06dG+mDkUSLbmoA=";
        //action, params and your secret
        String action = "order_result";
        String secret = "40e4cdb39f764ad48b0829e675b67b90";
        String params = "{\n" +
                "    \"action\": \"order_result\",\n" +
                "    \"events\": [\n" +
                "        \"{\\\"id\\\":\\\"bc76488ddda4\\\",\\\"create_time\\\":1585293811000,\\\"params\\\":{\\\"order_id\\\":\\\"1223\\\",\\\"status\\\":1}}\"\n" +
                "    ]\n" +
                "}";

        TreeMap<String, Object> treeMap = JSONObject.parseObject(params, TreeMap.class);
//        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        //verify
        try {
            String sign = HmacSHA256Base64Util.sign(secret, signature);
            System.out.println(sign);
            if (sign.equals(signature)) {
                System.out.println("signature is right");
            }else {
                System.out.println("signature is wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
