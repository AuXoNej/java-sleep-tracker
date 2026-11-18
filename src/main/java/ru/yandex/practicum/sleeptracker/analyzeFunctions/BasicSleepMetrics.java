package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.QualitySleep;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class BasicSleepMetrics {

    public Pair<List<LocalDateTime>, Long> getMinDurationSleep(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(s -> new Pair<>(
                List.of(s.getStartSleeping(), s.getEndSleeping()),
                Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes()
            ))
            .min(Comparator.comparingLong(Pair::getRight))
            .orElseThrow(() -> new RuntimeException("Не найдено ни одной сессии сна"));
    }

    public Pair<List<LocalDateTime>, Long> getMaxDurationSleep(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(s -> new Pair<>(
                List.of(s.getStartSleeping(), s.getEndSleeping()),
                Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes()
            ))
            .max(Comparator.comparingLong(Pair::getRight))
            .orElseThrow(() -> new RuntimeException("Не найдено ни одной сессии сна"));
    }

    public Long getAverageDurationSleep(List<SleepingSession> sessions) {
        return Math.round(sessions.stream()
            .mapToLong(s -> Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes())
            .average()
            .orElse(0));
    }

    public Long getCountBadQualitySleepSessions(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(SleepingSession::getSleepQuality)
            .filter(s -> s.equals(QualitySleep.BAD))
            .count();
    }
}
