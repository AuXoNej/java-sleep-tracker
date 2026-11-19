package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface MaxDurationSleep extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Максимальная продолжительность сессии сна (в минутах): ";

    Pair<List<LocalDateTime>, Long> getMaxDurationSleep(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getMaxDurationSleep(sessions));
    }
}
