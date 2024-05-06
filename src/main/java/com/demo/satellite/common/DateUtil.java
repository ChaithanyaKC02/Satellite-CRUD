package com.demo.satellite.common;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateUtil
{
    public static OffsetDateTime convertToOffsetDateTime(LocalDateTime localDateTime)
    {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return localDateTime.atOffset(offset);
    }
}
