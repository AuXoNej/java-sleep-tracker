package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Constants {

    // ========== SleeplessNights ==========
    public static final int HOUR_START_DAY = 12; //startPeriod

    public static final int NIGHT_DURATION_SECONDS = 21600; // 6 часов * 60 минут * 60 секунд
    public static final int DAY_DURATION_SECONDS = 86400;

    // ========== UserChronotype ==========
    public static final String CHRONOTYPE_OWL = "Сова";
    public static final String CHRONOTYPE_LARK = "Жаворонок";
    public static final String CHRONOTYPE_PIGEON = "Голубь";

    public static final LocalTime NIGHT_START_OWL = LocalTime.of(23, 0);
    public static final LocalTime NIGHT_END_OWL = LocalTime.of(9, 0);

    public static final LocalTime NIGHT_START_LARK = LocalTime.of(22, 0);
    public static final LocalTime NIGHT_END_LARK = LocalTime.of(7, 0);

    // ========== SleeplessNights ==========
    public static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd.MM.yy");

    // ========== SleepLogLoader ==========
    public static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

}
