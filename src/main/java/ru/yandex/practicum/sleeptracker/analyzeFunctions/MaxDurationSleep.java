package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxDurationSleep implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Максимальная продолжительность сессии сна (в минутах): ";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getMaxDurationSleep(sessions));
    }


    private Long getMaxDurationSleep(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(s ->
                Duration.between(s.getStartSleeping(), s.getEndSleeping()).toMinutes())
            .max(Long::compareTo)
            .orElseThrow(() -> new RuntimeException("Не найдено ни одной сессии сна"));
    }
}
