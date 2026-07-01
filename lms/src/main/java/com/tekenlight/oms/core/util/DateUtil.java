package com.tekenlight.oms.core.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static LocalDateTime getCurrentTimeStamp() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}