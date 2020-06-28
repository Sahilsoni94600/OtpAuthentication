package com.example.utils;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

public class PasswordUtils {

    public static String encryptPassword(String password) {
        return DatatypeConverter.printBase64Binary(password.getBytes());
    }

    public static String decryptPassword(String encryptPassword) {
        return new String(DatatypeConverter.parseBase64Binary(encryptPassword));
    }
}
