package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageDurationAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Средняя продолжительность сессии (в минутах)", 0L);
        }

        // Считаем среднее через стрим (он выдает double)
        double averageDouble = sessions.stream()
                .mapToLong(session -> java.time.Duration.between(session.getStartTime(), session.getEndTime()).toMinutes())
                .average()
                .orElse(0.0);

        // Округляем до ближайшего целого числа и приводим к типу Long
        long roundedAverage = Math.round(averageDouble);

        return new SleepAnalysisResult<>("Средняя продолжительность сессии (в минутах)", roundedAverage);
    }
}
