package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.MinDurationSleep;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDurationSleepTest {

    private final MinDurationSleep metrics = new MinDurationSleep();


    @Test
    void getMinDurationSleep_singleSession_returnsThatSession() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 0);

        var sessions = List.of(
            new SleepingSession(start, end, QualitySleep.GOOD)
        );

        var result = metrics.apply(sessions).value();

        assertEquals(8 * 60L, result);
    }

    @Test
    void getMinDurationSleep_multipleSessions_returnsShortest() {

        var start = LocalDateTime.of(2026, 1, 2, 23, 0);
        var end = LocalDateTime.of(2026, 1, 3, 1, 0);

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                start, end, QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 30),
                LocalDateTime.of(2026, 1, 4, 5, 30),
                QualitySleep.NORMAL
            )
        );

        var result = metrics.apply(sessions).value();

        assertEquals(2 * 60L, result);
    }
}
