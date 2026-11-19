package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface CountSleepingSessions extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Количество сессий сна";

    Integer getCount(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return AnalysisResult.forPeriod(
            LABEL, sessions, getCount(sessions)
        );
    }
}
