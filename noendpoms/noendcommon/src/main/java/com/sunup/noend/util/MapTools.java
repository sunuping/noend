package com.sunup.noend.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapTools {
    public static ConcurrentMap<String, Object> getConcurrentMap() {
        return new ConcurrentHashMap<String, Object>(1);
    }
}
