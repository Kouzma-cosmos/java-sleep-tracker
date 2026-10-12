package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static void main(String[] args) {
        try {
            List<SleepSession>
                    sessions = Files.lines(Paths.get("src/main/resources/sleep_log.txt"))
                    .map(line -> {
                        String[] parts = line.split(";");
                        LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);
                        SleepQuality quality = SleepQuality.valueOf(parts[2].trim());
                        return new SleepSession(start, end, quality);
                    })
                    .collect(Collectors.toList());

            System.out.println("=== ОТЧЕТ ПО АНАЛИТИКЕ СНА ===");

            new SleepAnalyticsOrchestrator().apply(sessions).forEach(result ->
                    System.out.println(result.getDescription() + ": " + result.getValue())
            );

            System.out.println("==============================");

        } catch (IOException e) {
            System.out.println("Ошибка: не удалось найти или прочитать файл sleep_log.txt. Проверьте путь к файлу.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: структура лога не соответствует ожидаемой. Проверьте формат данных в файле.");
        }
    }
}
