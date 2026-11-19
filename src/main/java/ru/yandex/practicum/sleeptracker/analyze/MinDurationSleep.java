package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface MinDurationSleep extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Минимальная продолжительность сессии сна (в минутах): ";

    Pair<List<LocalDateTime>, Long> getMinDurationSleep(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getMinDurationSleep(sessions));
    }
}
