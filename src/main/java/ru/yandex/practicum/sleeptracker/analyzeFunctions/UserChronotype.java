package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static ru.yandex.practicum.sleeptracker.Constants.*;

public class UserChronotype implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Хронотип пользователя: ";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        return new AnalysisResult(LABEL, getUserChronotype(sessions));
    }

    private String getUserChronotype(List<SleepingSession> sessions) {

        var chronotypes = new HashMap<String, Integer>();
        chronotypes.put(CHRONOTYPE_OWL, 0);
        chronotypes.put(CHRONOTYPE_LARK, 0);
        chronotypes.put(CHRONOTYPE_PIGEON, 0);

        sessions.stream()
            .filter(s -> {
                var start = s.getStartSleeping().toLocalTime();
                return start.isBefore(DAYTIME_SLEEP_END)
                    || s.getEndSleeping().toLocalTime().isBefore(start);
            })
            .map(s ->
                new AbstractMap.SimpleImmutableEntry<>(s.getStartSleeping(), s.getEndSleeping()))
            .forEach(p -> {
                if (
                    p.getKey().toLocalTime().isAfter(NIGHT_START_OWL)
                        && p.getValue().toLocalTime().isAfter(NIGHT_END_OWL)
                ) {
                    chronotypes.merge(CHRONOTYPE_OWL, 1, Integer::sum);
                } else if (
                    p.getKey().toLocalTime().isBefore(NIGHT_START_LARK)
                        && p.getValue().toLocalTime().isBefore(NIGHT_END_LARK)
                ) {
                    chronotypes.merge(CHRONOTYPE_LARK, 1, Integer::sum);
                } else {
                    chronotypes.merge(CHRONOTYPE_PIGEON, 1, Integer::sum);
                }
            });

        var maxCount = chronotypes.values().stream()
            .mapToInt(Integer::intValue)
            .max()
            .orElse(0);

        var tieCount = chronotypes.values().stream()
            .filter(v -> v == maxCount)
            .count();

        if (tieCount > 1) {
            return CHRONOTYPE_PIGEON;
        }

        return chronotypes.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(CHRONOTYPE_PIGEON);
    }
}
