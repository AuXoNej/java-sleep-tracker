package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyze.function.CountSleepingSessionsFunction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSleepingSessionsFunctionTest {

    private final CountSleepingSessionsFunction analyzeFunc = new CountSleepingSessionsFunction();

    @Test
    void singleSession_returnsOne() {

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            )
        );

        assertEquals(1, analyzeFunc.getCount(sessions));
    }

    @Test
    void multipleSessions_returnsCorrectCount() {

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                    LocalDateTime.of(2026, 1, 2, 23, 0),
                    LocalDateTime.of(2026, 1, 3, 7, 0),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 30),
                LocalDateTime.of(2026, 1, 4, 5, 30),
                QualitySleep.BAD
            )
        );

        assertEquals(3, analyzeFunc.getCount(sessions));
    }

}
