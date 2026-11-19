package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface AverageDurationSleep extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Средняя продолжительность сессии сна (в минутах): ";

    Long getAverageDurationSleep(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getAverageDurationSleep(sessions));
    }
}
