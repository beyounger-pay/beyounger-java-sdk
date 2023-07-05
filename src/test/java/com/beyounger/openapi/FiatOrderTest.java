package com.beyounger.openapi;


import com.alibaba.fastjson.JSON;
import com.beyounger.openapi.dto.Customer;
import com.beyounger.openapi.dto.PaymentReq;
import com.beyounger.openapi.util.HttpUtil;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class FiatOrderTest {



//    String host = "https://api.sandbox.inst.money";
//    private static final String apiKey = "ddb2e451f9534b61a3476f6f4316087e";
//    private static final String apiSecret = "d9241402-99b0-4736-b592-6cf046c7af63";
//    private static final String apiPassphrase = "12345678a";

//    String host = "https://api.sandbox.inst.money";
//    private static final String apiKey = "59d07871c9cb4f5190ba468ea0a8b4ee";
//    private static final String apiSecret = "6edadeb7-7d3e-4137-9e52-d51bdd381c2a";
//    private static final String apiPassphrase = "12345678a";

    String host = "https://api.sandbox.inst.money";
    private static final String merchantId = "59d07871c9cb4f5190ba468ea0a8b4ee";
    private static final String apiSecret = "6edadeb7-7d3e-4137-9e52-d51bdd381c2a";





    @Before
    public void setUp() throws Exception {
        HttpUtil.init(host,merchantId,apiSecret);
    }


    @Test
    public void postPaymentTest() throws Exception {

        String requestPath = "/api/v1/payment";
        String requestQueryStr = "";

        PaymentReq req = new PaymentReq();
        req.setCurrency("USD");
        req.setAmount("1.3");
        req.setCust_order_id(UUID.randomUUID().toString());
        req.setReturn_url("https://sandbox.inst.money/status.html");
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

        HttpUtil.post(requestPath,requestQueryStr,req.toString());
    }

    @Test
    public void getRatesTest() throws Exception {
        String requestPath = "/api/v1/rates";
        String requestQueryStr = "from_currency=USD&to_coin=ETH";
        HttpUtil.get(requestPath,requestQueryStr);
    }

    @Test
    public void getOrdersTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "currency=USD&page_size=20&status=1";
//        String requestQueryStr = "currency=USD&page_size=20&start_time=1626339331000&end_time=1626339331000";
        HttpUtil.get(requestPath,requestQueryStr);
    }
    @Test
    public void getCustAssetTest() throws Exception {
        String requestPath = "/api/v1/balance";
        String requestQueryStr = "";
        HttpUtil.get(requestPath,requestQueryStr);
    }
    @Test
    public void getCustOrderTest() throws Exception {
        String requestPath = "/api/v1/orders";
        String requestQueryStr = "cust_order_id=1ec7b32b-0750-4308-b882-f00d45b4f712";
        HttpUtil.get(requestPath,requestQueryStr);
    }
    @Test
    public void refundTest() throws Exception {

        String requestPath = "/api/v1/refund";
        String requestQueryStr = "";
        Map req = new HashMap<>();
        req.put("cust_order_id","7c235b70-f0c1-487d-a4e6-02894a821aea");
        HttpUtil.post(requestPath,requestQueryStr, JSON.toJSONString(req));
    }
    @Test
    public void queryOrderTest() throws Exception {

        String requestPath = "/v1/checkout";
        String requestQueryStr = "id=20210722083735119900000205";

        System.out.println("------ queryOrderTest start");
        HttpUtil.get(requestPath, requestQueryStr);
        System.out.println("------ queryOrderTest end");
    }

}
