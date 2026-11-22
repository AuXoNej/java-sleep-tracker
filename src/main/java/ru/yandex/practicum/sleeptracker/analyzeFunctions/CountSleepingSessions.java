package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountSleepingSessions implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Количество сессий сна";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return AnalysisResult.forPeriod(LABEL, List.of(), 0);
        }
        return AnalysisResult.forPeriod(
            LABEL, sessions, sessions.size()
        );
    }
}
