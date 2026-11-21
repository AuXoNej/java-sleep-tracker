package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.AverageDurationSleep;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageDurationSleepTest {

    private final AverageDurationSleep metrics = new AverageDurationSleep();


    @Test
    void getAverageDurationSleep_singleSession_returnsThatDuration() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 0);

        var sessions = List.of(
                new SleepingSession(start, end, QualitySleep.GOOD)
        );

        var result = metrics.apply(sessions).value();

        assertEquals(8 * 60L, result);
    }

    @Test
    void getAverageDurationSleep_multipleSessions_returnsAverage() {

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 1, 0),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 30),
                LocalDateTime.of(2026, 1, 4, 5, 30),
                QualitySleep.NORMAL
            )
        );

        // 480 + 120 + 480 = 1080 / 3 = 360
        var result = metrics.apply(sessions).value();

        assertEquals(360L, result);
    }

    @Test
    void getAverageDurationSleep_equalDurations_returnsThatDuration() {

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 30),
                LocalDateTime.of(2026, 1, 4, 5, 30),
                QualitySleep.NORMAL
            )
        );

        var result = metrics.apply(sessions).value();

        assertEquals(8 * 60L, result);
    }
}
