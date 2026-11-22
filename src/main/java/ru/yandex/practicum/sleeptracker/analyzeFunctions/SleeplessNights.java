package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.AnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

import static ru.yandex.practicum.sleeptracker.Constants.HOUR_START_DAY;
import static ru.yandex.practicum.sleeptracker.Constants.NIGHT_DURATION;

public class SleeplessNights implements Function<List<SleepingSession>, AnalysisResult> {

    private static final String LABEL = "Количество бессонных ночей";

    @Override
    public AnalysisResult apply(List<SleepingSession> sessions) {
        return AnalysisResult.forPeriod(
            LABEL, sessions, countSleeplessNights(sessions)
        );
    }


    private Long countSleeplessNights(List<SleepingSession> sessions) {

        var startPeriod = sessions.getFirst().getStartSleeping();
        var endPeriod = sessions.getLast().getEndSleeping();

        /*
            ТЗ: если первая сессия сна в файле началась после 12 дня,
            потенциальной ночью для сна считается следующая ночь, а если до 12 — то предыдущая
        */
        var firstNight = startPeriod.getHour() <= HOUR_START_DAY
            ? startPeriod.toLocalDate()
            : startPeriod.plusDays(1).toLocalDate();
        var lastNight = endPeriod.toLocalDate();

        return firstNight.datesUntil(lastNight.plusDays(1))
            .filter(day -> {
                var nightStart = day.atStartOfDay();
                return sessions.stream()
                    .noneMatch(s ->
                        s.getStartSleeping().isBefore(nightStart.plus(NIGHT_DURATION))
                        && nightStart.isBefore(s.getEndSleeping()));
            })
            .count();
    }
}
