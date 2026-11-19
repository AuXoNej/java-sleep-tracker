package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface CountBadQualitySleepSessions extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Количество сессий с плохим качеством сна: ";

    Long getCountBadQualitySleepSessions(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getCountBadQualitySleepSessions(sessions));
    }
}
