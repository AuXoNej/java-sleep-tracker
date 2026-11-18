package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.SleeplessNights;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightsTest {

    private final SleeplessNights analyzeFunc = new SleeplessNights();


    @Test
    void sessionFullyInsideNight_nightIsNotSleepless() {

        var sessions = List.of(
            new SleepingSession(

                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(0, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void sessionPartiallyOverlappingNight_nightIsNotSleepless() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 23, 0),
                LocalDateTime.of(2026, 1, 2, 2, 0),
                QualitySleep.NORMAL
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(0, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void sessionTooShortToCoverNight_nightIsSleepless() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 10, 0),
                LocalDateTime.of(2026, 1, 1, 11, 0),
                QualitySleep.BAD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(1, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void multipleNights_twoOfThreeCovered_returnsOne() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23, 0),
                LocalDateTime.of(2026, 1, 4, 5, 0),
                QualitySleep.NORMAL
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        // Ночь 1/2 — покрыта (сессия 22:00-06:00)
        // Ночь 2/3 — бессонная
        // Ночь 3/4 — покрыта (сессия 23:00-05:00)
        assertEquals(1, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void multipleNights_allCovered_returnsZero() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 22, 0),
                LocalDateTime.of(2026, 1, 3, 6, 0),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 22, 0),
                LocalDateTime.of(2026, 1, 4, 6, 0),
                QualitySleep.BAD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(0, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void multipleNights_noneCovered_returnsTotal() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 10, 0),
                LocalDateTime.of(2026, 1, 1, 12, 0),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 14, 0),
                LocalDateTime.of(2026, 1, 2, 16, 0),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 8, 0),
                LocalDateTime.of(2026, 1, 3, 9, 0),
                QualitySleep.BAD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(3, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void startPeriodAfterNoon_nightStartsFromNextDay() {

        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 15, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 2, 15, 0);

        List<SleepingSession> sessions = List.of();

        // start.hour > 12 → первая ночь считается с 00:00 2 января
        assertEquals(1, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void startPeriodBeforeNoon_nightStartsFromSameDay() {

        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 2, 15, 0);

        List<SleepingSession> sessions = List.of();

        // start.hour <= 12 → первая ночь считается с 00:00 1 января
        assertEquals(2, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void startPeriodAtNoon_nightStartsFromSameDay() {

        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 2, 15, 0);

        List<SleepingSession> sessions = List.of();

        assertEquals(2, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void sessionBoundaryExactMatch_nightIsNotSleepless() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                QualitySleep.GOOD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        // Сессия ровно по границе ночного интервала (00:00-06:00)
        assertEquals(0, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void sessionJustOutsideNightBoundary_nightIsSleepless() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 6, 1),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                QualitySleep.NORMAL
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        assertEquals(1, analyzeFunc.countSleeplessNights(sessions, start, end));
    }

    @Test
    void sessionSpanningMultipleNights_allCovered() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 22, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                QualitySleep.GOOD
            )
        );

        var start = sessions.getFirst().getStartSleeping();
        var end = sessions.getLast().getEndSleeping();

        // Длинная сессия перекрывает ночи 1/2, 2/3 и частично 3/4
        assertEquals(0, analyzeFunc.countSleeplessNights(sessions, start, end));
    }
}
