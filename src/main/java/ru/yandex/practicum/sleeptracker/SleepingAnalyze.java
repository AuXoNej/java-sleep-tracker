package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analyzeFunctions.BasicSleepMetrics;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.CountSleepingSessions;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.SleeplessNights;
import ru.yandex.practicum.sleeptracker.analyzeFunctions.UserChronotype;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static ru.yandex.practicum.sleeptracker.Constants.FORMATTER_DATE;

public class SleepingAnalyze {

    // List, а не Map, для простого доступа в случае, если нужно вызвать конкретную функцию
    // Даже если сделать Map<String, Supplier<?>>, Описания, которые я тут делаю,
    // мне показались слишком длинными для ключа
    // А что-то лучше я не придумал)))
    private final List<Pair<Supplier<?>, String>> functions;

    private final LocalDateTime startPeriod;
    private final LocalDateTime endPeriod;

    SleepingAnalyze(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            throw new RuntimeException("Список сессий сна пуст");
        }

        this.startPeriod = sleepingSessions.getFirst().getStartSleeping();
        this.endPeriod = sleepingSessions.getLast().getEndSleeping();

        functions = new ArrayList<>();
        functions.add(
            new Pair<>(
                () -> new CountSleepingSessions().getCount(sleepingSessions),
                String.format(
                    "Количество сессий сна за период с %s по %s: ",
                    startPeriod.format(FORMATTER_DATE), endPeriod.format(FORMATTER_DATE)
                )
            )
        );
        functions.add(
            new Pair<>(
                () -> new BasicSleepMetrics().getMinDurationSleep(sleepingSessions),
                "Минимальная продолжительность сессии сна (в минутах): "
            )
        );
        functions.add(
            new Pair<>(
                () -> new BasicSleepMetrics().getMaxDurationSleep(sleepingSessions),
                "Максимальная продолжительность сессии сна (в минутах): "
            )
        );
        functions.add(
            new Pair<>(
                () -> new BasicSleepMetrics().getAverageDurationSleep(sleepingSessions),
                "Средняя продолжительность сессии сна (в минутах): "
            )
        );
        functions.add(
            new Pair<>(
                () -> new BasicSleepMetrics().getCountBadQualitySleepSessions(sleepingSessions),
                "Количество сессий с плохим качеством сна: "
            )
        );
        functions.add(
            new Pair<>(
                () -> new SleeplessNights().countSleeplessNights(
                    sleepingSessions, startPeriod, endPeriod
                ),
                String.format(
                    "Количество бессонных ночей за период с %s по %s: ",
                    startPeriod.format(FORMATTER_DATE), endPeriod.format(FORMATTER_DATE)
                )
            )
        );
        functions.add(
            new Pair<>(
                () -> new UserChronotype().getUserChronotype(sleepingSessions),
                "Хронотип пользователя: "
            )
        );
    }

    public List<Pair<Supplier<?>, String>> getFunctions() {
        return functions;
    }
}
