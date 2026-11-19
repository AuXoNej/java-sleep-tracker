package ru.yandex.practicum.sleeptracker.analyze.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyze.CountSleepingSessions;

import java.util.List;

public class CountSleepingSessionsFunction implements CountSleepingSessions {

    @Override
    public Integer getCount(List<SleepingSession> sessions) {
        return sessions.size();
    }
}
