package ru.yandex.practicum.sleeptracker.analyze.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyze.AverageDurationSleep;

import java.time.Duration;
import java.util.List;

public class AverageDurationSleepFunction implements AverageDurationSleep {

    @Override
    public Long getAverageDurationSleep(List<SleepingSession> sessions) {

        return Math.round(sessions.stream()
            .mapToLong(s -> Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes())
            .average()
            .orElse(0));
    }
}
