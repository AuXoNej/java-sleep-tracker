package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.BasicSleepMetrics;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicSleepMetricsTest {

    private final BasicSleepMetrics metrics = new BasicSleepMetrics();


    // ========== getMinDurationSleep ==========
    @Test
    void getMinDurationSleep_singleSession_returnsThatSession() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 0);

        var sessions = List.of(
            new SleepingSession(start, end, QualitySleep.GOOD)
        );

        var result = metrics.getMinDurationSleep(sessions);

        assertEquals(8 * 60, result.getRight());
        assertEquals(List.of(start, end), result.getLeft());
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

        var result = metrics.getMinDurationSleep(sessions);

        assertEquals(2 * 60, result.getRight());
        assertEquals(List.of(start, end), result.getLeft());
    }


    // ========== getMaxDurationSleep ==========
    @Test
    void getMaxDurationSleep_singleSession_returnsThatSession() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 0);

        var sessions = List.of(
            new SleepingSession(start, end, QualitySleep.GOOD)
        );

        var result = metrics.getMaxDurationSleep(sessions);

        assertEquals(8 * 60, result.getRight());
        assertEquals(List.of(start, end), result.getLeft());
    }

    @Test
    void getMaxDurationSleep_multipleSessions_returnsLongest() {

        var start = LocalDateTime.of(2026, 1, 1, 22, 0);
        var end = LocalDateTime.of(2026, 1, 2, 6, 5);

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

        var result = metrics.getMaxDurationSleep(sessions);

        assertEquals(8 * 60 + 5, result.getRight());
        assertEquals(List.of(start, end),result.getLeft());
    }


    // ========== getAverageDurationSleep ==========
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


    // ========== getCountBadQualitySleepSessions ==========
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

        assertEquals(0, metrics.getCountBadQualitySleepSessions(sessions));
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

        assertEquals(3, metrics.getCountBadQualitySleepSessions(sessions));
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

        assertEquals(2, metrics.getCountBadQualitySleepSessions(sessions));
    }
}
