package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyze.function.AverageDurationSleepFunction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageDurationSleepFunctionTest {

    private final AverageDurationSleepFunction metrics = new AverageDurationSleepFunction();


    @Test
    void getAverageDurationSleep_singleSession_returnsThatDuration() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 0);

        var sessions = List.of(
                new SleepingSession(start, end, QualitySleep.GOOD)
        );

        var result = metrics.getAverageDurationSleep(sessions);

        assertEquals(8 * 60, result);
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
        var result = metrics.getAverageDurationSleep(sessions);

        assertEquals(360, result);
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

        var result = metrics.getAverageDurationSleep(sessions);

        assertEquals(8 * 60, result);
    }
}
