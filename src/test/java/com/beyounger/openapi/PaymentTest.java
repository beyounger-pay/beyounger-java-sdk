package com.beyounger.openapi;


import com.alibaba.fastjson.JSON;
import com.beyounger.openapi.dto.*;
import com.beyounger.openapi.util.HttpUtil;


import com.beyounger.openapi.util.SHA512Util;
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

    String host = "https://api.beyounger.com/";
    private static final String merchantId = "12345678910";
    private static final String apiSecret = "6edadeb7-7d3e-4137-9e52-d51bdd381c2a";





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
        req.setPayment_method("paypal");
        req.setMerchant_name("");
        req.setSite_id(1);
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
        List<CartItem> cartItems = new ArrayList<>();
        CartItem item = new CartItem();
        item.setName("White GenericBrand handbag");
        item.setQuantity(1);
        item.setAmount("99.95");
        item.setCurrency("CAD");
        item.setProduct_id("Ag54352R7768kkO");
        item.setCategory("Apparel and accessories");
        cartItems.add(item);
        req.setCart_items(cartItems);

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


        String signature = merchantId +
                "&" + req.getCust_order_id() +
                "&" + req.getAmount() +
                "&" + req.getCurrency() +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();

        HttpUtil.post(requestPath,req.toString(),signature,merchantId);
    }

    @Test
    public void getRatesTest() throws Exception {
        String requestPath = "/api/v1/rates";
        String requestQueryStr = "from_currency=USD&to_coin=ETH";
        String signature = merchantId +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId);
    }

    @Test
    public void getOrdersTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "currency=USD&page_size=20&status=1";
        String signature = merchantId +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();
//        String requestQueryStr = "currency=USD&page_size=20&start_time=1626339331000&end_time=1626339331000";
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId);
    }
    @Test
    public void getCustAssetTest() throws Exception {
        String requestPath = "/api/v1/balance";
        String requestQueryStr = "";
        String signature = merchantId +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId);
    }
    @Test
    public void getCustOrderTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "cust_order_id=1ec7b32b-0750-4308-b882-f00d45b4f712";
        String signature = merchantId +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId);
    }
    @Test
    public void refundTest() throws Exception {

        String requestPath = "/api/v1/refund";
        String requestQueryStr = "";
        Map req = new HashMap<>();
        req.put("cust_order_id","7c235b70-f0c1-487d-a4e6-02894a821aea");
        HttpUtil.post(requestPath,requestQueryStr, JSON.toJSONString(req), merchantId);
    }
    @Test
    public void queryOrderTest() throws Exception {

        String requestPath = "/v1/checkout";
        String requestQueryStr = "id=20210722083735119900000205";

        System.out.println("------ queryOrderTest start");
        String signature = merchantId +
                "&" + apiSecret +
                "&" + System.currentTimeMillis();
        HttpUtil.get(requestPath,requestQueryStr,signature,merchantId);
        System.out.println("------ queryOrderTest end");
    }

}
