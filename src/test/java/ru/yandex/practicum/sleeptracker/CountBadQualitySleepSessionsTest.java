package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.CountBadQualitySleepSessions;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountBadQualitySleepSessionsTest {

    private final CountBadQualitySleepSessions metrics = new CountBadQualitySleepSessions();


    @Test
    void getCountBadQualitySleepSessions_noBadSessions_returnsZero() {

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
            )
        );

        assertEquals(0L, metrics.apply(sessions).value());
    }

    @Test
    void getCountBadQualitySleepSessions_allBadSessions_returnsSize() {

        var sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 7, 0),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 30),
                LocalDateTime.of(2026, 1, 4, 5, 30),
                QualitySleep.BAD
            )
        );

        assertEquals(3L, metrics.apply(sessions).value());
    }

    @Test
    void getCountBadQualitySleepSessions_mixedQuality_returnsCorrectCount() {

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
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 22, 0),
                LocalDateTime.of(2026, 1, 5, 6, 0),
                QualitySleep.BAD
            )
        );

        assertEquals(2L, metrics.apply(sessions).value());
    }
}
