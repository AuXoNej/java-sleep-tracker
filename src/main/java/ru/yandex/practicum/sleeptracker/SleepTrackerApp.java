package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analyzeFunctions.*;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, AnalysisResult>> ANALYSIS_FUNCTIONS = List.of(
        new CountSleepingSessions(),
        new MinDurationSleep(),
        new MaxDurationSleep(),
        new AverageDurationSleep(),
        new CountBadQualitySleepSessions(),
        new SleeplessNights(),
        new UserChronotype()
    );


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу с данными о сне:");
        var path = scanner.nextLine();
//        var path = "src/main/resources/sleep_log.txt";
//        var path = "src/main/resources/sleep_log_owl_and_lark.txt";
//        var path = "src/main/resources/sleep_log_owl.txt";

        var sleepingSessions = SleepLogLoader.loadSleepingSessions(path);

        if (sleepingSessions.isEmpty()) {
            throw new RuntimeException("Список сессий сна пуст");
        }

        ANALYSIS_FUNCTIONS.forEach(function ->
            System.out.println(function.apply(sleepingSessions))
        );
    }
}