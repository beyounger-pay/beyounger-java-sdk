package com.beyounger.openapi;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beyounger.openapi.dto.*;
import com.beyounger.openapi.util.HttpUtil;


import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class PaymentTest {



//    String host = "https://api.sandbox.inst.money";
//    private static final String apiKey = "ddb2e451f9534b61a3476f6f4316087e";
//    private static final String apiSecret = "d9241402-99b0-4736-b592-6cf046c7af63";
//    private static final String apiPassphrase = "12345678a";

//    String host = "https://api.sandbox.inst.money";
//    private static final String apiKey = "59d07871c9cb4f5190ba468ea0a8b4ee";
//    private static final String apiSecret = "6edadeb7-7d3e-4137-9e52-d51bdd381c2a";
//    private static final String apiPassphrase = "12345678a";

    String host = "https://api.beyounger.com";
//    String host = "http://localhost:8585";
    private static final String merchantId = "d73d82c2801b47c8b5247ad9344d5711";
//    private static final String apiSecret = "xPiU+IHMtbkP1+If+52TBfLXvhAvEdmuDKAbkQndUS8kwnqs2zHe0ITi9HCdiaTU";
    private static final String apiSecret = "61a02d15-760d-41ca-8126-60cbb77728c8";





    @Before
    public void setUp() throws Exception {
        HttpUtil.init(host, apiSecret);
    }


    @Test
    public void postPaymentTest() throws Exception {

        String requestPath = "/api/v1/payment";

        PaymentReq req = new PaymentReq();
        req.setCurrency("USD");
        req.setAmount("100.05");
        req.setCust_order_id(UUID.randomUUID().toString());
        //支付方式 paypal sofort paysafecard creditcard等
        req.setPayment_method("creditcard");
        req.setReturn_url("https://api.beyounger.com/status.html");
        req.setNotification_url("https://api.beyounger.com/status.html");

        //用户信息(选填)
        Customer customer = new Customer();
        customer.setEmail("hello@inst.money");
        customer.setFirst_name("Jack");
        customer.setLast_name("Li");
        customer.setPhone("+12123434235");
        customer.setCountry("USA");
        customer.setState("A");
        customer.setCity("B");
        customer.setAddress("sgasgs,shfojsg,AA");
        customer.setZipcode("24000");
        req.setCustomer(customer);

        //商品信息(选填)
        JSONArray array = new JSONArray();
        array.add(new JSONObject()
                .fluentPut("name", "White GenericBrand handbag")
                .fluentPut("quantity", 1)
                .fluentPut("amount", "99.95")
                .fluentPut("currency", "CAD")
                .fluentPut("product_id", "Ag54352R7768kkO")
                .fluentPut("category", "Apparel and accessories")
        );
        req.setCart_items(array);

        //快递信息(选填)
        DeliveryDetails details = new DeliveryDetails();
        details.setDelivery_type("PHYSICAL");
        details.setDelivery_method("USPS - Ground Mail");
        details.setDelivery_time(1415273168L);
        req.setDelivery_details(details);

        //收件人信息(选填)
        DeliveryRecipient recipient = new DeliveryRecipient();
        recipient.setEmail("hello@gmail.com");
        recipient.setPhone("");
        recipient.setFirst_name("Jack");
        recipient.setLast_name("Li");
        recipient.setCountry("USA");
        recipient.setState("");
        recipient.setCity("");
        recipient.setAddress1("");
        recipient.setAddress2("");
        recipient.setZipcode("");
        req.setDelivery_recipient(recipient);

        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + req.getCust_order_id() +
                "&" + req.getAmount() +
                "&" + req.getCurrency() +
                "&" + apiSecret +
                "&" + timeStamp;

        HttpUtil.post(requestPath,req.toString(),signature,merchantId, timeStamp);
    }

    @Test
    public void checkout()throws Exception{

        String requestPath = "/v1/checkout";
        String requestQueryStr = "id=2307141431422614977";

        System.out.println("------ queryOrderTest start");

        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;

        HttpUtil.get(requestPath, requestQueryStr,signature,merchantId,timeStamp);
    }

    //选择支付方法
    @Test
    public void choosePayment()throws Exception{
        String requestPath = "/v1/choosePayment";
        Map req = new HashMap<>();
        req.put("id", "2307141431422614977");
        req.put("processor", "nextpay_processor");

        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;

        HttpUtil.post(requestPath, JSON.toJSONString(req),signature,merchantId,timeStamp);
    }

    @Test
    public void getRatesTest() throws Exception {
        String requestPath = "/api/v1/rates";
        String requestQueryStr = "from_currency=USD&to_coin=ETH";
        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId, timeStamp);
    }

    @Test
    public void getOrdersTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "currency=USD&page_size=20&status=1";
        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;
//        String requestQueryStr = "currency=USD&page_size=20&start_time=1626339331000&end_time=1626339331000";
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId, timeStamp);
    }
    @Test
    public void getCustAssetTest() throws Exception {
        String requestPath = "/api/v1/balance";
        String requestQueryStr = "";
        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId, timeStamp);
    }
    @Test
    public void getCustOrderTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "cust_order_id=1ec7b32b-0750-4308-b882-f00d45b4f712";
        long timeStamp = System.currentTimeMillis();
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId, timeStamp);
    }
    @Test
    public void refundTest() throws Exception {
        long timeStamp = System.currentTimeMillis();
        String requestPath = "/api/v1/refund";
        String requestQueryStr = "";
        Map req = new HashMap<>();
        req.put("cust_order_id","7c235b70-f0c1-487d-a4e6-02894a821aea");
        HttpUtil.post(requestPath,requestQueryStr, JSON.toJSONString(req), merchantId, timeStamp);
    }
    @Test
    public void queryOrderTest() throws Exception {

        String requestPath = "/v1/checkout";
        String requestQueryStr = "id=20210722083735119900000205";

        long timeStamp = System.currentTimeMillis();
        System.out.println("------ queryOrderTest start");
        String signature = merchantId +
                "&" + apiSecret +
                "&" + timeStamp;
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId, timeStamp);
        System.out.println("------ queryOrderTest end");
    }

}
