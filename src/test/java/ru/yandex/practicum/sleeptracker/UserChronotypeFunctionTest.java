package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyze.function.UserChronotypeFunction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserChronotypeFunctionTest {

    private final UserChronotypeFunction chronotype = new UserChronotypeFunction();


    // ========== Чистые хронотипы ==========
    @Test
    void allOwlSessions_returnsOwl() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 23, 0),
                LocalDateTime.of(2025, 1, 3, 9, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 23, 15),
                LocalDateTime.of(2025, 1, 4, 10, 15),
                QualitySleep.BAD
            )
        );

        assertEquals("Сова", chronotype.getUserChronotype(sessions));
    }

    @Test
    void allLarkSessions_returnsLark() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 21, 0),
                LocalDateTime.of(2025, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 20, 30),
                LocalDateTime.of(2025, 1, 3, 6, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 21, 45),
                LocalDateTime.of(2025, 1, 4, 5, 45),
                QualitySleep.BAD
            )
        );

        assertEquals("Жаворонок", chronotype.getUserChronotype(sessions));
    }

    @Test
    void allPigeonSessions_returnsPigeon() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 22, 30),
                LocalDateTime.of(2025, 1, 2, 7, 30),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 22, 0),
                LocalDateTime.of(2025, 1, 3, 8, 0),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 23, 0),
                LocalDateTime.of(2025, 1, 4, 8, 30),
                QualitySleep.BAD
            )
        );

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }


    // ========== 2v1 ==========
    @Test
    void twoLarksVsOnePigeon_returnsLark() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 21, 0),
                LocalDateTime.of(2025, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 20, 30),
                LocalDateTime.of(2025, 1, 3, 6, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 22, 30),
                LocalDateTime.of(2025, 1, 4, 7, 30),
                QualitySleep.BAD
            )
        );

        assertEquals("Жаворонок", chronotype.getUserChronotype(sessions));
    }

    @Test
    void twoOwlsVsOnePigeon_returnsOwl() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 23, 20),
                LocalDateTime.of(2025, 1, 3, 9, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 22, 30),
                LocalDateTime.of(2025, 1, 4, 7, 30),
                QualitySleep.BAD
            )
        );

        assertEquals("Сова", chronotype.getUserChronotype(sessions));
    }


    // ========== Ничьи — "Голубь" ==========
    @Test
    void tieOwlVsLark_returnsPigeon() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 21, 0),
                LocalDateTime.of(2025, 1, 3, 6, 0),
                QualitySleep.NORMAL
            )
        );

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }

    @Test
    void tieOwlVsPigeon_returnsPigeon() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 22, 30),
                LocalDateTime.of(2025, 1, 3, 7, 30),
                QualitySleep.NORMAL
            )
        );

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }

    @Test
    void tieLarkVsPigeon_returnsPigeon() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 21, 0),
                LocalDateTime.of(2025, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 22, 30),
                LocalDateTime.of(2025, 1, 3, 7, 30),
                QualitySleep.NORMAL
            )
        );

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }

    @Test
    void tieAllThree_returnsPigeon() {
        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 21, 0),
                LocalDateTime.of(2025, 1, 3, 6, 0),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 22, 30),
                LocalDateTime.of(2025, 1, 4, 7, 30),
                QualitySleep.BAD
            )
        );

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }


    // ========== Пустой список ==========
    @Test
    void emptySessions_returnsPigeon() {

        List<SleepingSession> sessions = List.of();

        assertEquals("Голубь", chronotype.getUserChronotype(sessions));
    }


    // ========== Граничные случаи ==========
    @Test
    void startExactly2300_notOwl() {
        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 0, 0),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 23, 0, 1),
                LocalDateTime.of(2025, 1, 3, 10, 0),
                QualitySleep.NORMAL
            )
        );

        assertNotEquals("Сова", chronotype.getUserChronotype(sessions));
    }

    @Test
    void endExactly0900_notOwl() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 0),
                LocalDateTime.of(2025, 1, 2, 9, 0, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 23, 30),
                LocalDateTime.of(2025, 1, 3, 9, 0, 1),
                QualitySleep.NORMAL
            )
        );

        assertNotEquals("Сова", chronotype.getUserChronotype(sessions));
    }

    @Test
    void startExactly2200_notLark() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 22, 0, 1),
                LocalDateTime.of(2025, 1, 2, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 21, 59, 59),
                LocalDateTime.of(2025, 1, 3, 6, 0),
                QualitySleep.NORMAL
            )
        );

        assertNotEquals("Жаворонок", chronotype.getUserChronotype(sessions));
    }

    @Test
    void endExactly0700_notLark() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 21, 0),
                LocalDateTime.of(2025, 1, 2, 7, 0, 1),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 21, 0),
                LocalDateTime.of(2025, 1, 3, 6, 59, 59),
                QualitySleep.NORMAL
            )
        );

        assertNotEquals("Жаворонок", chronotype.getUserChronotype(sessions));
    }


    // ========== Смешанные с явным победителем ==========
    @Test
    void threeOwlsTwoLarksOnePigeon_returnsOwl() {

        List<SleepingSession> sessions = List.of(
            new SleepingSession(
                LocalDateTime.of(2025, 1, 1, 23, 30),
                LocalDateTime.of(2025, 1, 2, 10, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 2, 23, 30),
                LocalDateTime.of(2025, 1, 3, 9, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 3, 23, 15),
                LocalDateTime.of(2025, 1, 4, 10, 15),
                QualitySleep.BAD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 4, 21, 0),
                LocalDateTime.of(2025, 1, 5, 6, 0),
                QualitySleep.GOOD
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 5, 20, 30),
                LocalDateTime.of(2025, 1, 6, 6, 30),
                QualitySleep.NORMAL
            ),
            new SleepingSession(
                LocalDateTime.of(2025, 1, 6, 22, 30),
                LocalDateTime.of(2025, 1, 7, 7, 30),
                QualitySleep.BAD
            )
        );

        assertEquals("Сова", chronotype.getUserChronotype(sessions));
    }
}
