package ru.yandex.practicum.sleeptracker;

import java.util.List;

import static ru.yandex.practicum.sleeptracker.Constants.FORMATTER_DATE;

public record AnalysisResult(String description, Object value) {

    public static AnalysisResult forPeriod(String label, List<SleepingSession> sessions, Object value) {
        if (sessions == null || sessions.isEmpty()) {
            return new AnalysisResult(label + ": ", value);
        }
        return new AnalysisResult(
            "%s за период с %s по %s: ".formatted(
                label,
                sessions.getFirst().getStartSleeping().format(FORMATTER_DATE),
                sessions.getLast().getEndSleeping().format(FORMATTER_DATE)
            ),
            value
        );
    }

    @Override
    public String toString() {
        return description + value;
    }
}
