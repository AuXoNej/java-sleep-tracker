package ru.yandex.practicum.sleeptracker.analyze.function;

import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyze.MinDurationSleep;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class MinDurationSleepFunction implements MinDurationSleep {

    @Override
    public Pair<List<LocalDateTime>, Long> getMinDurationSleep(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(s -> new Pair<>(
                List.of(s.getStartSleeping(), s.getEndSleeping()),
                Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes()
            ))
            .min(Comparator.comparingLong(Pair::getRight))
            .orElseThrow(() -> new RuntimeException("Не найдено ни одной сессии сна"));
    }
}
