package com.infy.food.serviceimpl;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class XRequestId {

    public static ThreadLocal<String> X_REQUEST_ID = new ThreadLocal<>();

    public static void generateXRequestID() {
        X_REQUEST_ID.set(UUID.randomUUID().toString());
    }
    public static String getXRequestID() {
        return X_REQUEST_ID.get();
    }

    public static void clearXRequestID() {
        X_REQUEST_ID.remove();
    }

}
