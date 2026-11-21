package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.QualitySleep;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountBadQualitySleepSessions implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Количество сессий с плохим качеством сна: ";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(
            LABEL,
            sessions.stream()
                .map(SleepingSession::getSleepQuality)
                .filter(s -> s.equals(QualitySleep.BAD))
                .count()
        );
    }
}
