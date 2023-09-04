package com.beyounger.openapi.util;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @date 2020/3/27
 */
public class HttpUtil {

    private static String host = "";
    private static String apiSecret = "";


    public static void init(String apihost,  String apisecret) {
        host = apihost;
        apiSecret = apisecret;
    }

    public static void get(String requestPath, String requestQueryStr, String signature, String apiKey, long timeStamp) throws Exception {
        String SIGN_SEPARATOR = ":";

        String sign = SignUtil.sign(signature);

        String authorizationStr =
                        apiKey
                        + SIGN_SEPARATOR
                        + timeStamp
                        + SIGN_SEPARATOR
                        + sign;
        System.out.println(host + requestPath);
        System.out.println("Request Url:" + host + requestPath + "?" + requestQueryStr);
        System.out.println("Authorization:" + authorizationStr);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(host + requestPath + "?" + requestQueryStr)
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorizationStr)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("error... " + response.body().string());
        }
    }

    public static void post(String requestPath, String reqString, String signature, String apiKey, long timeStamp) throws Exception {
        String SIGN_SEPARATOR = ":";

        String sign = SignUtil.sign(signature);

        String authorizationStr = apiKey
                + SIGN_SEPARATOR
                + timeStamp
                + SIGN_SEPARATOR
                + sign;
        System.out.println(host + requestPath);
        System.out.println("Authorization:" + authorizationStr);
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(90, TimeUnit.SECONDS).build();
        ;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, reqString);
        Request request = new Request.Builder()
                .url(host + requestPath)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorizationStr)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("error... " + response.body().string());
        }
    }

    public static void put(String requestPath, String reqString, String signature, String apiKey) throws Exception {
        String SIGN_SEPARATOR = ":";
        String timeStampStr = String.valueOf(System.currentTimeMillis());

        String sign = SignUtil.sign(signature);

        String authorizationStr = apiKey
                + SIGN_SEPARATOR
                + timeStampStr
                + SIGN_SEPARATOR
                + sign;
        System.out.println(host + requestPath);
        System.out.println("Authorization:" + authorizationStr);

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, reqString);
        Request request = new Request.Builder()
                .url(host + requestPath)
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorizationStr)
                .build();
        Response response = client.newCall(request).execute();
        //System.out.println("result="+response.isSuccessful());
        System.out.println();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("error... " + response.body().string());
        }
    }

}
