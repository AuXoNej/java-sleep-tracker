package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static ru.yandex.practicum.sleeptracker.Constants.FORMATTER_DATE_TIME;

public class SleepLogLoader {

    public static List<SleepingSession> loadSleepingSessions(String path) {
        try (BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)
        )) {
            return fileReader.lines()
                .filter(line -> !line.isBlank())
                .map(line -> line.split(";"))
                .map(session -> new SleepingSession(
                    LocalDateTime.parse(session[0].trim(), FORMATTER_DATE_TIME),
                    LocalDateTime.parse(session[1].trim(), FORMATTER_DATE_TIME),
                    QualitySleep.valueOf(session[2].trim())
                )).toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл с данными сна не найден.", e);
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка во время чтения файла с данными сна.", e);
        }
    }
}
