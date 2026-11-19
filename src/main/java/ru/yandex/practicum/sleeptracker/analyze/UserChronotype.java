package ru.yandex.practicum.sleeptracker.analyze;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface UserChronotype extends Function<List<SleepingSession>, AnalysisResult> {

    String LABEL = "Хронотип пользователя: ";

    String getUserChronotype(List<SleepingSession> sessions);

    @Override
    default AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getUserChronotype(sessions));
    }
}
