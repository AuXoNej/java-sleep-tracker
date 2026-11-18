package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountSleepingSessions {

    public Integer getCount(List<SleepingSession> sessions) {
        return sessions.size();
    }

}
