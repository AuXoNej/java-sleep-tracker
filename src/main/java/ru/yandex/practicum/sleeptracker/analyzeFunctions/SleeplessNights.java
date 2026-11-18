package ru.yandex.practicum.sleeptracker.analyzeFunctions;

import ru.yandex.practicum.sleeptracker.Pair;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.LongStream;

import static ru.yandex.practicum.sleeptracker.Constants.*;

public class SleeplessNights {

    public Long countSleeplessNights(
        List<SleepingSession> sessions,
        LocalDateTime startPeriod,
        LocalDateTime endPeriod
    ) {

        var start = startPeriod.getHour() <= HOUR_START_DAY
            ? startPeriod.toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC)
            : startPeriod.plusDays(1).toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        var end = endPeriod.toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC) + NIGHT_DURATION_SECONDS;

        List<Pair<Long, Long>> sleeplessNights = LongStream.range(
            0, Math.ceilDiv(end - start, DAY_DURATION_SECONDS))
            .mapToObj(i -> {
                long nightStart = start + i * DAY_DURATION_SECONDS;
                return new Pair<>(nightStart, nightStart + NIGHT_DURATION_SECONDS);
            })
            .toList();

        List<Pair<Long, Long>> sessionIntervals = sessions.stream()
            .map(s -> new Pair<>(
                s.getStartSleeping().toEpochSecond(ZoneOffset.UTC),
                s.getEndSleeping().toEpochSecond(ZoneOffset.UTC)
            ))
            .toList();

        return sleeplessNights.stream()
            .filter(night -> sessionIntervals.stream()
                .noneMatch(
                    session ->
                        night.getLeft() < session.getRight()
                        && session.getLeft() < night.getRight()
                )
            )
            .count();
    }
}
