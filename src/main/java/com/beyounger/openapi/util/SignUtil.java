package com.beyounger.openapi.util;


import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUtil {
    public static MessageDigest MessageDigest;


    public static String sign(String signature) throws Exception {
        if (StringUtils.isEmpty(signature)) {
            throw new Exception("signature cannot be empty");
        }
        System.out.println("origin sign data:{}" + signature);
        return getSHA256Hash(signature);
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

    private static String getSHA256Hash(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
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

