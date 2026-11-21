package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;


public class AverageDurationSleep implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Средняя продолжительность сессии сна (в минутах): ";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(
            LABEL,
            Math.round(sessions.stream()
                .mapToLong(s -> Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes())
                .average()
                .orElse(0))
        );
    }
}
