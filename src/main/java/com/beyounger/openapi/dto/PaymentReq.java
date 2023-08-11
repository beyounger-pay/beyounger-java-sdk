package com.beyounger.openapi.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class PaymentReq {

    private String currency;

    private String amount;

    private String cust_order_id;

    private String payment_method;

    private String notification_url;
    private String return_url;

    private Customer customer;
    private JSONArray cart_items;
    private DeliveryDetails delivery_details;
    private DeliveryRecipient delivery_recipient;
    private Long expire;
    private String fixed_card_number;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCust_order_id() {
        return cust_order_id;
    }

    public void setCust_order_id(String cust_order_id) {
        this.cust_order_id = cust_order_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }


    public String getNotification_url() {
        return notification_url;
    }

    public void setNotification_url(String notification_url) {
        this.notification_url = notification_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public JSONArray getCart_items() {
        return cart_items;
    }

    public void setCart_items(JSONArray cart_items) {
        this.cart_items = cart_items;
    }

    public DeliveryDetails getDelivery_details() {
        return delivery_details;
    }

    public void setDelivery_details(DeliveryDetails delivery_details) {
        this.delivery_details = delivery_details;
    }

    public DeliveryRecipient getDelivery_recipient() {
        return delivery_recipient;
    }

    public void setDelivery_recipient(DeliveryRecipient delivery_recipient) {
        this.delivery_recipient = delivery_recipient;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
