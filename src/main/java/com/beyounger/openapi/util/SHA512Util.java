package com.beyounger.openapi.util;


import org.apache.commons.lang3.StringUtils;

import javax.management.RuntimeErrorException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512Util {
    public static MessageDigest MessageDigest;

//    static {
//        try {
//            MessageDigest = MessageDigest.getInstance("SHA-512");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeErrorException(new Error("Can't get MessageDigest's instance."));
//        }
//    }

    public static String sign(String signature) throws Exception {
        if (StringUtils.isEmpty(signature)) {
            throw new Exception("signature cannot be empty");
        }
        System.out.println("origin sign data:{}" + signature);
        return getSHA512Hash(signature);
    }

    private static String getSHA512Hash(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

