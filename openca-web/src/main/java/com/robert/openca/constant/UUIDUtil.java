package com.robert.openca.constant;

import java.util.UUID;

public class UUIDUtil {

    public static String get() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}
