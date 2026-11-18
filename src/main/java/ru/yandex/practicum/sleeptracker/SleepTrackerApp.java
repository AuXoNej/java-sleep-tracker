package ru.yandex.practicum.sleeptracker;

import java.util.Scanner;

public class SleepTrackerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу с данными о сне:");
        var path = scanner.nextLine();
//        var path = "src/main/resources/sleep_log.txt";
//        var path = "src/main/resources/sleep_log_owl_and_lark.txt";
//        var path = "src/main/resources/sleep_log_owl.txt";

        var sleepingSessions = SleepLogLoader.loadSleepingSessions(path);

        SleepingAnalyze sleepingAnalyze = new SleepingAnalyze(sleepingSessions);

        sleepingAnalyze.getFunctions().forEach(
            f -> {
                System.out.print(f.getRight());
                System.out.println(f.getLeft().get());
            }
        );
    }
}