package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface SleeplessNights extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Количество бессонных ночей";

    Long countSleeplessNights(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return AnalysisResult.forPeriod(
            LABEL, sessions, countSleeplessNights(sessions)
        );
    }
}
