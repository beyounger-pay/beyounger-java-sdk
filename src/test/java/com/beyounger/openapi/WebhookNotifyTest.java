package com.beyounger.openapi;

import com.alibaba.fastjson.JSONObject;
import com.beyounger.openapi.util.SHA512Util;
import org.junit.Test;

import java.util.HashMap;


public class WebhookNotifyTest {

    String host = "https://api.beyounger.com";
    private static final String apiKey = "d73d82c2801b47c8b5247ad9344d5711";
    private static final String apiSecret = "61a02d15-760d-41ca-8126-60cbb77728c8";
    @Test
    public void notificationTest() {
        //what you receive in header
        String timeStamp = "1585810920286";
        String signature = "dd89ef8f20c4ef8e624fc7e52ba01c9376fd7ec63ca3ca847016b3a40ce86250c279c6f03d0e632d6f06d85763a1ba9fbb8fbb9453ad97a2801224a21fbd1a84";

        String request = "{\"data\":\"e1wiYWN0aW9uXCI6XCJvcmRlcl9yZXN1bHRcIixcImV2ZW50XCI6e1wib3JkZXJfaWRcIjpcIjEyM1wiLFwiY3VzdF9vcmRlcl9pZFwiOlwiMTIzXCIsXCJjcmVhdGVfdGltZVwiOlwiMjAyMzA3MDMxNDI0MjRcIixcIm1lc3NhZ2VcIjpcInBheW1lbmd0ZmFpbFwiLFwic3RhdHVzXCI6Mn19\"}";

        HashMap map = JSONObject.parseObject(request, HashMap.class);
        String signatureData = apiKey + "&"+(String)map.get("data") + "&" + apiSecret +"&" +timeStamp;
        //verify
        try {
            String sign = SHA512Util.sign(signatureData);
            System.out.println("sign: "+sign);
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
