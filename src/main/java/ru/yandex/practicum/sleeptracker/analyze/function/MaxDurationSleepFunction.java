package ru.yandex.practicum.sleeptracker.analyze.function;

import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyze.MaxDurationSleep;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class MaxDurationSleepFunction implements MaxDurationSleep {

    @Override
    public Pair<List<LocalDateTime>, Long> getMaxDurationSleep(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(s -> new Pair<>(
                List.of(s.getStartSleeping(), s.getEndSleeping()),
                Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes()
            ))
            .max(Comparator.comparingLong(Pair::getRight))
            .orElseThrow(() -> new RuntimeException("Не найдено ни одной сессии сна"));
    }
}
