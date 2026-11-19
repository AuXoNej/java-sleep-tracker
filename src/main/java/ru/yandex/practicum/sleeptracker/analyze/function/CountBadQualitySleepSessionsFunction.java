package ru.yandex.practicum.sleeptracker.analyze.function;

import ru.yandex.practicum.sleeptracker.QualitySleep;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyze.CountBadQualitySleepSessions;

import java.util.List;

public class CountBadQualitySleepSessionsFunction implements CountBadQualitySleepSessions {

    @Override
    public Long getCountBadQualitySleepSessions(List<SleepingSession> sessions) {
        return sessions.stream()
            .map(SleepingSession::getSleepQuality)
            .filter(s -> s.equals(QualitySleep.BAD))
            .count();
    }
}
